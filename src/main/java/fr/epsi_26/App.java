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
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}