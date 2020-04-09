package devweb.sa.projetfilrouge.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "depot")
public class Depot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int montant;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date dateDepot;

    @JoinColumn(name = "compte_id",referencedColumnName ="id")
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JsonIgnoreProperties("depot")
    private Compte compte;

    @JoinColumn(name = "user_id",referencedColumnName ="id")
    @ManyToOne(fetch=FetchType.LAZY,optional = false)
    @JsonIgnoreProperties("depot")
    private User user;

    public Depot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Date getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Date dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
