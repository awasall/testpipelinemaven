package devweb.sa.projetfilrouge.controller;


import devweb.sa.projetfilrouge.model.Compte;
import devweb.sa.projetfilrouge.model.Partenaire;
import devweb.sa.projetfilrouge.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    @PostMapping(value="/compte",consumes = {MediaType.APPLICATION_JSON_VALUE})
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Compte add(@RequestBody (required = false)Compte c)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("ddMMyyyyHHmmss");
        Date date= new Date();
        String cpt=""+sdf.format(date);
        c.setNumerCompte(cpt);
        c.setSolde(0);
        return  compteRepository.save(c);
    }
    //recherche Compte
    @PostMapping(value="/rechercheCompte")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Optional<Compte> rechercheCompt(@RequestBody (required = false)Compte c) throws  Exception{
        String numerCompte =c.getNumerCompte();
        Optional<Compte> compte=compteRepository.findByNumerCompte(numerCompte);
        if (compteRepository.findByNumerCompte(numerCompte).isEmpty()) {
            throw new Exception("Ce compte n'existe pas");
        }
        return  compteRepository.findByNumerCompte(numerCompte);

    }
}
