package fr.epsi_26;

import jakarta.persistence.*;

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

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}