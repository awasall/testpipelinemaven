package devweb.sa.projetfilrouge.repository;

import devweb.sa.projetfilrouge.model.Compte;
import devweb.sa.projetfilrouge.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CompteRepository extends JpaRepository<Compte, Integer> {
 Optional<Compte> findByNumerCompte(String numerCompte);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);

}