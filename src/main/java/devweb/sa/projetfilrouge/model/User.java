package devweb.sa.projetfilrouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "telephone"
        })

})
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank
    @Size(min=3, max = 50)
    private String username;


    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    @NotBlank
    @Size(min=3, max = 50)
    private String prenom;

    @NotBlank
    @Size(max = 50)
    private String nom;

    @NotBlank
    @Size(min=3, max = 50)
    private String telephone;

    @NotBlank
    @Size(min=3, max = 50)
    private String statut;

    @NotBlank
    @Size(min=3, max = 50)
    private String adresse;
    private String role;

    @Column
    private String imageName;

    @Column
    @Lob
    private byte[] imageFile;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @JoinColumn(name = "partenaire_id",referencedColumnName ="id" )
    @ManyToOne(fetch =FetchType.LAZY ,optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","partenaire"})
    private Partenaire partenaire;

    @ManyToOne(optional = false,cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","compte"})
    private Compte compte;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","user"})
    //@JsonIgnore
    private List<Depot> depot;
    public User() {}

    public User( String username, String password, String prenom, String nom,  String telephone,  String statut, String adresse, String role) {
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.statut = statut;
        this.adresse = adresse;
        this.role = role;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public List<Depot> getDepot() {
        return depot;
    }

    public void setDepot(List<Depot> depot) {
        this.depot = depot;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public byte[] getImageFile() {
        return imageFile;
    }

    public void setImageFile(byte[] imageFile) {
        this.imageFile = imageFile;
    }
}