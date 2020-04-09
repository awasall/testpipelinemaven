package devweb.sa.projetfilrouge.repository;

import devweb.sa.projetfilrouge.model.Partenaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PartenaireRepository extends JpaRepository<Partenaire, Integer> {
  Optional<Partenaire> findByNinea(String ninea);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);

}