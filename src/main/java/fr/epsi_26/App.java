package fr.epsi_26;

import jakarta.persistence.*;
import java.util.List;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // CREATE
        Livre nouveauLivre = new Livre("Harry Potter à l'école des sorciers", "J.K. Rowling");
        em.persist(nouveauLivre);
        System.out.println(">>> Nouveau livre inséré avec l'ID : " + nouveauLivre.getId());

        // FIND
        Livre livre1 = em.find(Livre.class, 1);
        if (livre1 != null) {
            System.out.println(">>> Livre 1 trouvé : " + livre1.getTitre() + " de " + livre1.getAuteur());
        }

        // UPDATE
        // Modifier le titre du livre 5
        Livre livre5 = em.find(Livre.class, 5);
        if (livre5 != null) {
            livre5.setTitre("Du plaisir dans la cuisine");
            System.out.println(">>> Livre 5 modifié avec succès.");
        } else {
            System.out.println(">>> Livre 5 introuvable !");
        }

        // JPQL - par Titre
        String titreRecherche = "Germinal";
        TypedQuery<Livre> queryTitre = em.createQuery("SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class);
        queryTitre.setParameter("titre", titreRecherche);

        List<Livre> livreParTitre = queryTitre.getResultList();
        System.out.println("Recherche JPQL par titre (" + titreRecherche + ") :");
        System.out.println(" - " + livreParTitre);

        // JPQL - par Auteur
        String auteurRecherche = "Gaston Pouet";
        TypedQuery<Livre> queryAuteur = em.createQuery("SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class);
        queryAuteur.setParameter("auteur", auteurRecherche);

        List<Livre> livresParAuteur = queryAuteur.getResultList();
        System.out.println("Recherche JPQL par auteur (" + auteurRecherche + ") :");
        for (Livre l : livresParAuteur) {
            System.out.println(" - " + l.getTitre());
        }

        // DELETE
        Livre livreASupprimer = em.find(Livre.class, 3);
        if (livreASupprimer != null) {
            em.remove(livreASupprimer);
            System.out.println(">>> Livre 3 supprimé avec succès.");
        } else {
            System.out.println(">>> Livre 3 introuvable, déjà supprimé ?");
        }

        em.getTransaction().commit();

        // Afficher la liste
        TypedQuery<Livre> queryAll = em.createQuery("SELECT l FROM Livre l", Livre.class);
        List<Livre> tousLesLivres = queryAll.getResultList();

        System.out.println("_LISTE DES LIVRES_");
        for (Livre l : tousLesLivres) {
            System.out.println(l.getId() + " : " + l.getTitre() + " - " + l.getAuteur());
        }

        em.close();
        emf.close();
    }
}