package devweb.sa.projetfilrouge.controller;


import devweb.sa.projetfilrouge.model.Compte;
import devweb.sa.projetfilrouge.model.Depot;
import devweb.sa.projetfilrouge.model.User;
import devweb.sa.projetfilrouge.repository.CompteRepository;
import devweb.sa.projetfilrouge.repository.DepotRepository;
import devweb.sa.projetfilrouge.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class DepotController {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    private DepotRepository depotRepository;
    @Autowired
    private CompteRepository compteRepository;
    @PostMapping(value="/depot",consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Depot add(@RequestBody (required = false) Depot d)
    {
        Date date= new Date();
        d.setDateDepot(date);
        User user= userDetailsService.getUserConnect();
        d.setUser(user);
         depotRepository.save(d);
         Optional<Compte> compte=compteRepository.findById(d.getCompte().getId());
         Compte compte1=compte.get();
         int rec=compte1.getSolde()+d.getMontant();
         compte1.setSolde(rec);
         compteRepository.save(compte1);
        return d;
    }
}
