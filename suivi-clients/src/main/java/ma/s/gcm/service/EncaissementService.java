package ma.s.gcm.service;

import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Compte;
import ma.s.gcm.domain.Encaissement;
import ma.s.gcm.domain.Transaction;
import ma.s.gcm.dto.EncaissementDto;
import ma.s.gcm.dto.EncaissementSearchCriteriaDto;
import ma.s.gcm.dto.TransactionDto;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.mapper.EncaissementMapper;
import ma.s.gcm.repository.EncaissementRepository;
import ma.s.gcm.repository.TransactionRepository;
import ma.s.gcm.type.StatutEncaissement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class EncaissementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncaissementService.class);

    private EncaissementRepository encaissementRepository;
    private TransactionRepository transactionRepository;

    public EncaissementService(EncaissementRepository encaissementRepository, TransactionRepository transactionRepository) {
        this.encaissementRepository = encaissementRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<EncaissementDto> searchByCriteria(Long entrepriseId, EncaissementSearchCriteriaDto criteriaDto) throws SuiviClientsException {
        LOGGER.debug("START SERVICE find all");

        return Optional.ofNullable(encaissementRepository.searchByCriteria(entrepriseId, criteriaDto.getClientId()
                , criteriaDto.getMode()
                , criteriaDto.getType()
                , criteriaDto.getMontantMin()
                , criteriaDto.getMontantMax()
                , criteriaDto.getDateDReception()
                , criteriaDto.getDateFReception()
                , criteriaDto.getDateDDepot()
                , criteriaDto.getDateFDepot()
                , criteriaDto.getDateDReleveBancaire()
                , criteriaDto.getDateFReleveBancaire()
                , criteriaDto.getStatut()
                , criteriaDto.getBanque()
                , criteriaDto.getNCheque()
                , criteriaDto.getDateDCheque()
                , criteriaDto.getDateFCheque()
                , criteriaDto.getNEffet()
                , criteriaDto.getDateDEffet()
                , criteriaDto.getDateFEffet()
                , criteriaDto.getNVirement()
                , criteriaDto.getNCompteVirement()
                , criteriaDto.getMotif()
                , criteriaDto.getNTransaction()
                , criteriaDto.getCompteId()
        ))
                .map(EncaissementMapper::toDtos)
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Collections not found"));
    }

    public void receive(EncaissementDto encaissementDto) {
        LOGGER.debug("START SERVICE save collection by id {}, mode: {}", encaissementDto.getId(), encaissementDto.getMode());
        Encaissement encaissement = Encaissement.builder()
                .client(Client.builder().id(encaissementDto.getClient().getId()).build())
                .montant(encaissementDto.getMontant())
                .dateReception(encaissementDto.getDateReception())
                .mode(encaissementDto.getMode())
                .build();
        encaissement.setStatut(StatutEncaissement.RECU);
        switch (encaissement.getMode()) {
            case CHEQUE:
                encaissement.setNCheque(encaissementDto.getNCheque());
                encaissement.setDateCheque(encaissementDto.getDateCheque());
                encaissement.setCheqAuNomDe(encaissementDto.getCheqAuNomDe());
                break;
            case EFFET:
                encaissement.setNEffet(encaissementDto.getNEffet());
                encaissement.setDateEffet(encaissementDto.getDateEffet());
                encaissement.setCheqAuNomDe(encaissementDto.getCheqAuNomDe());
                break;
            case VIREMENT:
                encaissement.setNVirement(encaissementDto.getNVirement());
                encaissement.setNCompteVirement(encaissementDto.getNCompteVirement());
                encaissement.setStatut(StatutEncaissement.PAYE);
                break;
        }
        encaissementRepository.save(encaissement);
        LOGGER.debug("START SERVICE save collection by id {}, mode: {}", encaissementDto.getId(), encaissementDto.getMode());
    }

    public void deposite(TransactionDto transactionDto) {
        LOGGER.debug("START SERVICE depose collection by date {}, amount: {}", transactionDto.getDateDepot(), transactionDto.getMontant());
        transactionDto.getEncaissements().stream().forEach(e -> {
            Encaissement encaissement= encaissementRepository.getOne(e.getId());
            if (StatutEncaissement.RECU.equals(e.getStatut())) {
                encaissement.setStatut(StatutEncaissement.DEPOSE);
            }
        });

        Transaction transaction = Transaction.builder()
                .compte(Compte.builder().id(transactionDto.getCompte().getId()).build())
                .dateDepot(transactionDto.getDateDepot())
                .nTrasaction(transactionDto.getNTrasaction())
                .montant(transactionDto.getMontant())
                .build();
        transactionRepository.save(transaction);
        LOGGER.debug("START SERVICE save collection by date {}, amount: {}", transactionDto.getDateDepot(), transactionDto.getDateDepot());
    }

    public void update(List<EncaissementDto> encaissements) {
        LOGGER.debug("START SERVICE bank statement collection size: {}", encaissements.size());
        encaissements.stream().forEach(e -> {
            Encaissement encaissement= encaissementRepository.getOne(e.getId());
            if (Arrays.asList(StatutEncaissement.PAYE, StatutEncaissement.IMPAYE).contains(e.getStatut())) {
                encaissement.setStatut(e.getStatut());
            }
        });
        LOGGER.debug("START SERVICE bank statement collection size: {}", encaissements.size());
    }


}
