package iset.master.spring.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import iset.master.spring.model.Produit;
import jakarta.transaction.Transactional;
public interface ProduitRepository extends JpaRepository<Produit, Long> {
@Query ("select prod from Produit prod  where prod.designation like %:x% ")
public List<Produit> findByDesignation(@Param ("x") String mc);
// Method to find products with designation containing "mc" and price greater than "prix"
List<Produit> findByDesignationContainingAndPrixGreaterThan(String mc, double prix);
@Query("update Produit p set p.designation =:designation where p.id = :id")
@Modifying
@Transactional
public int mettreAJourDesignation(
@Param("designation")String designation,
@Param("id") Long idProduit);
List<Produit> findByPrixGreaterThan(double prixMin);
 }