package devweb.sa.projetfilrouge.controller;
import devweb.sa.projetfilrouge.model.Compte;
import devweb.sa.projetfilrouge.model.Partenaire;
import devweb.sa.projetfilrouge.model.Role;
import devweb.sa.projetfilrouge.model.User;
import devweb.sa.projetfilrouge.repository.CompteRepository;
import devweb.sa.projetfilrouge.repository.PartenaireRepository;
import devweb.sa.projetfilrouge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class PartenaireController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private PartenaireRepository  partenaireRepository;
    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private CompteRepository compteRepository;
    @GetMapping(value="/listepartenaire")
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")
    public List<Partenaire> Liste(){
        return  partenaireRepository.findAll();
    }
    @PostMapping(value="/partenaire",consumes = {MediaType.APPLICATION_JSON_VALUE})
    @PreAuthorize("hasAuthority('ROLE_SUPERADMIN')")

    public Partenaire add(@RequestBody (required = false)Partenaire p){
        p.setStatut("actif");
        partenaireRepository.save(p);
        Compte compte=new Compte();
        SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date= new Date();
        String cpt=""+sdf.format(date);
        compte.setNumerCompte(cpt);
        compte.setSolde(0);
        compte.setPartenaire(p);
        compteRepository.save(compte);
        User user=new User();
        user.setUsername(p.getEmail());
        user.setPassword(encoder.encode("passer"));
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        role.setId((long) 2);
        roles.add(role);
        user.setRoles(roles);
        user.setPrenom(p.getPrenom());
        user.setNom(p.getNom());
        user.setPartenaire(p);
        user.setAdresse(p.getAdresse());
        user.setTelephone(p.getTelephone());
        user.setStatut(p.getStatut());
        user.setCompte(compte);
        userRepository.save(user);
        return  p;
    }
//recherche partenaire
@PostMapping(value="/rechercheparte")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public Optional<Partenaire> recherchePart(@RequestBody (required = false)Partenaire p) throws  Exception{
        String ninea =p.getNinea();
        Optional<Partenaire> partenaire=partenaireRepository.findByNinea(ninea);
    if (partenaireRepository.findByNinea(ninea).isEmpty()) {
        throw new Exception("Ce partenaire n'existe pas");
    }
    return  partenaireRepository.findByNinea(ninea);

}

}
