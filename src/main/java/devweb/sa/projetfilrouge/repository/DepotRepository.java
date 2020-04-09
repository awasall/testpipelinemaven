package devweb.sa.projetfilrouge.repository;

import devweb.sa.projetfilrouge.model.Compte;
import devweb.sa.projetfilrouge.model.Depot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepotRepository extends JpaRepository<Depot, Integer> {
//    Optional<User> findByUsername(String username);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);

}