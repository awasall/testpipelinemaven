package devweb.sa.projetfilrouge.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "compte", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "numerCompte"
        })
})
@EqualsAndHashCode(exclude = "partenaire")

public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 25)
    private String numerCompte;
    private int solde;

    @JoinColumn(name = "partenaire_id",referencedColumnName ="id" )
    @ManyToOne(fetch =FetchType.LAZY ,optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","compte"})
    private Partenaire partenaire;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,mappedBy = "compte")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","compte"})
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "compte")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler","compte"})
    @JsonIgnore
    private List<Depot> depot;

    public Compte() {
    }

    public Compte(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumerCompte() {
        return numerCompte;
    }

    public void setNumerCompte(String numerCompte) {
        this.numerCompte = numerCompte;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public Partenaire getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(Partenaire partenaire) {
        this.partenaire = partenaire;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Depot> getDepot() {
        return depot;
    }

    public void setDepot(List<Depot> depot) {
        this.depot = depot;
    }
}
