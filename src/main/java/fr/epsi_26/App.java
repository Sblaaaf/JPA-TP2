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

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}