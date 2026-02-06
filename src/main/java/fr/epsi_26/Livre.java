package fr.epsi_26;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "LIVRE")
public class Livre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITRE", nullable = false)
    private String titre;

    @Column(name = "AUTEUR", nullable = false, length = 50)
    private String auteur;

    // Lien vers les emprunts - livre
    @ManyToMany(mappedBy = "livres")
    private Set<Emprunt> emprunts;

    public Livre() {
        this.emprunts = new HashSet<>();
    }

    public Livre(String titre, String auteur) {
        this.titre = titre;
        this.auteur = auteur;
        this.emprunts = new HashSet<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    // Getter et Setter pour emprunts
    public Set<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Set<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    @Override
    public String toString() {
        return "Livre{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                '}';
    }
}