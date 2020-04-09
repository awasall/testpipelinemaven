package devweb.sa.projetfilrouge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "partenaire", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "email"
        }),
        @UniqueConstraint(columnNames = {
                "telephone"
        }),
        @UniqueConstraint(columnNames = {
                "ninea"
        })

})
@EqualsAndHashCode(exclude = "compte")

public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 10)
    private String ninea;
    @Column(length = 50)
    private String raisonsociale;
    @Column(length = 50)
    private String adresse;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String prenom;
    @Column(length = 10)
    private String nom;
    @Column(length = 25)
    private String telephone;
    @Column(length = 25)
    private String statut;
    @OneToMany(mappedBy = "partenaire")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","partenaire"})
    @JsonIgnore
    private List<User> users;
    @OneToMany(mappedBy = "partenaire",cascade = {CascadeType.ALL})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","partenaire"})
    @JsonIgnore
    private List<Compte> compte;

    public Partenaire() {
    }

    public Partenaire(String ninea, String raisonsociale, String adresse, String email, String prenom, String nom, String telephone, String statut) {
        this.ninea = ninea;
        this.raisonsociale = raisonsociale;
        this.adresse = adresse;
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.telephone = telephone;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNinea() {
        return ninea;
    }

    public void setNinea(String ninea) {
        this.ninea = ninea;
    }

    public String getRaisonsociale() {
        return raisonsociale;
    }

    public void setRaisonsociale(String raisonsociale) {
        this.raisonsociale = raisonsociale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Compte> getCompte() {
        return compte;
    }

    public void setCompte(List<Compte> compte) {
        this.compte = compte;
    }

}
