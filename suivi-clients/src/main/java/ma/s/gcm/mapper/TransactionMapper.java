package ma.s.gcm.mapper;

import ma.s.gcm.domain.Transaction;
import ma.s.gcm.dto.TransactionDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class TransactionMapper {
    private TransactionMapper() {}

    public static TransactionDto toDto(Transaction transaction) {
        if(transaction == null) {
            return null;
        }
        return TransactionDto.builder()
                .id(transaction.getId())
                .compte(CompteMapper.toDto(transaction.getCompte()))
                .dateDepot(transaction.getDateDepot())
                .montant(transaction.getMontant())
                .nTrasaction(transaction.getNTrasaction())
                .build();
    }

    public static List<TransactionDto> toDtos(List<Transaction> transactions) {
        return transactions.stream().filter(Objects::nonNull)
                .map(TransactionMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Transaction toEntity(TransactionDto transactionDto) {
        return Transaction.builder()
                .id(transactionDto.getId())
                .compte(CompteMapper.toEntity(transactionDto.getCompte()))
                .dateDepot(transactionDto.getDateDepot())
                .montant(transactionDto.getMontant())
                .nTrasaction(transactionDto.getNTrasaction())
                .build();
    }
}
