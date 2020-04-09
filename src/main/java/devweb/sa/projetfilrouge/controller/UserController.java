package devweb.sa.projetfilrouge.controller;

import devweb.sa.projetfilrouge.model.Role;
import devweb.sa.projetfilrouge.model.RoleName;
import devweb.sa.projetfilrouge.model.User;
import devweb.sa.projetfilrouge.repository.UserRepository;
import devweb.sa.projetfilrouge.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @PostMapping(value="/register",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','ROLE_AdminPartenaire','ROLE_AdminSimple')")
    public User add(@RequestParam("file") MultipartFile file,@ModelAttribute("u") User u)
    {
        User user= userDetailsService.getUserConnect();
        Role rol= user.getRoles().iterator().next();
        RoleName adminPartenaire=RoleName.ROLE_AdminPartenaire;
        RoleName role_adminSimple=RoleName.ROLE_AdminSimple;
        if(rol.getName()==adminPartenaire || rol.getName()==role_adminSimple){

            u.setPartenaire(user.getPartenaire());

        }

        u.setStatut("actif");
        Role role = new Role();
        Set<Role> roles = new HashSet<>();

        if(u.getRole().equals("USER")){

            role.setId((long) 1);

        }
        if(u.getRole().equals("AdminPartenaire")){
            role.setId((long) 2);

        }
        if(u.getRole().equals("AdminSimple")){
            role.setId((long) 3);

        }
        if(u.getRole().equals("Caissier")) {
            role.setId((long) 4);

        }
        if(u.getRole().equals("superadmin")) {
            role.setId((long) 7);

        }
        roles.add(role);
        u.setRoles(roles);
        u.setPassword(encoder.encode(u.getPassword()));
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        u.setImageName(fileName);
        try {
            u.setImageFile(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/home/awa/")
                .path(fileName).path("/db")
                .toUriString();
        return userRepository.save(u);
    }
}
