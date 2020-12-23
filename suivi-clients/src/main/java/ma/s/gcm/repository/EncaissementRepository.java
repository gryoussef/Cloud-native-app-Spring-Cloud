package ma.s.gcm.repository;

import ma.s.gcm.domain.Encaissement;
import ma.s.gcm.type.ModeEncaissement;
import ma.s.gcm.type.StatutEncaissement;
import ma.s.gcm.type.TypeEncaissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;




public interface EncaissementRepository extends JpaRepository<Encaissement,Long> {
    @Query("SELECT e FROM Encaissement e left join Transaction t on e.transaction = t WHERE e.client.entreprise.id= :entrepriseId" +
            " AND (:clientId is null OR e.client.id= :clientId)"+
            " AND (:mode is null OR e.mode= :mode)" +
            " AND (:type is null OR e.type= :type)" +
            " AND (:montantMin is null OR e.montant>= :montantMin)" +
            " AND (:montantMax is null OR e.montant<= :montantMax)" +
            " AND (:dateReceptionMin is null OR e.dateReception>= :dateReceptionMin)" +
            " AND (:dateReceptionMax is null OR e.dateReception<= :dateReceptionMax)" +
            " AND (:dateDepotMin is null OR (t is not null AND t.dateDepot>= :dateDepotMin))" +
            " AND (:dateDepotMax is null OR (t is not null AND t.dateDepot<= :dateDepotMax))" +
            " AND (:dateReleveBancaireMin is null OR e.dateReleveBancaire>= :dateReleveBancaireMin)" +
            " AND (:dateReleveBancaireMax is null OR e.dateReleveBancaire<= :dateReleveBancaireMax)" +
            " AND (:statut is null OR e.statut= :statut)" +
            " AND (:banque is null OR e.banque like %:banque%)" +
            " AND (:nCheque is null OR e.nCheque like %:nCheque%)" +
            " AND (:dateDCheque is null OR e.dateCheque>= :dateDCheque)" +
            " AND (:dateFCheque is null OR e.dateCheque<= :dateFCheque)" +
            " AND (:nEffet is null OR e.nEffet like %:nEffet%)" +
            " AND (:dateDEffet is null OR e.dateEffet>= :dateDEffet)" +
            " AND (:dateFEffet is null OR e.dateEffet<= :dateFEffet)" +
            " AND (:nVirement is null OR e.nVirement like %:nVirement%)" +
            " AND (:nCompteVirement is null OR e.nCompteVirement like %:nCompteVirement%)" +
            " AND (:motif is null OR e.motif like %:motif%)" +
            " AND (:nTransaction is null OR (t is not null  AND t.nTrasaction like %:nTransaction%))" +
            " AND (:compteId is null OR (t is not null  AND t.compte.id= :compteId))"
    )
    List<Encaissement> searchByCriteria(
            @Param("entrepriseId") Long entrepriseId,
            @Param("clientId") Long clientId,
            @Param("mode") ModeEncaissement mode,
            @Param("type") TypeEncaissement type,
            @Param("montantMin") BigDecimal montantMin,
            @Param("montantMax") BigDecimal montantMax,
            @Param("dateReceptionMin") Date dateReceptionMin,
            @Param("dateReceptionMax") Date dateReceptionMax,
            @Param("dateDepotMin") Date dateDepotMin,
            @Param("dateDepotMax") Date dateDepotMax,
            @Param("dateReleveBancaireMin") Date dateReleveBancaireMin,
            @Param("dateReleveBancaireMax") Date dateReleveBancaireMax,
            @Param("statut") StatutEncaissement statut,
            @Param("banque") String banque,
            @Param("nCheque") String nCheque,
            @Param("dateDCheque") Date dateDCheque,
            @Param("dateFCheque") Date dateFCheque,
            @Param("nEffet") String nEffet,
            @Param("dateDEffet") Date dateDEffet,
            @Param("dateFEffet") Date dateFEffet,
            @Param("nVirement") String nVirement,
            @Param("nCompteVirement") String nCompteVirement,
            @Param("motif") String motif,
            @Param("nTransaction") String nTransaction,
            @Param("compteId") Long compteId
    );
}
