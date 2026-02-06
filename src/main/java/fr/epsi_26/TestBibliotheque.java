package fr.epsi_26;

import jakarta.persistence.*;
import java.util.Set;

public class TestBibliotheque {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();

        // Extraire emprunt / livre
        int idEmprunt = 1; // ex ID 1
        Emprunt emprunt = em.find(Emprunt.class, idEmprunt);

        if (emprunt != null) {
            System.out.println("Emprunt ID " + idEmprunt);
            System.out.println("Date début : " + emprunt.getDateDebut());
            System.out.println("Délai : " + emprunt.getDelai() + " jours");

            // Client (ManyToOne)
            System.out.println("Client : " + emprunt.getClient().getNom() + " " + emprunt.getClient().getPrenom());

            // Livres (ManyToMany)
            System.out.println("Livres associés :");
            Set<Livre> livres = emprunt.getLivres();
            if (livres.isEmpty()) {
                System.out.println("  Aucun livre associé.");
            } else {
                for (Livre livre : livres) {
                    System.out.println("  - " + livre.getTitre());
                }
            }
        } else {
            System.out.println("Emprunt ID " + idEmprunt + " non trouvé.");
        }

        // Extraire tous les emprunts client
        int idClient = 1;
        Client client = em.find(Client.class, idClient);

        if (client != null) {
            System.out.println("Emprunts du Client : " + client.getNom() + " " + client.getPrenom());

            Set<Emprunt> empruntsDuClient = client.getEmprunts();

            if (empruntsDuClient.isEmpty()) {
                System.out.println("Ce client n'a effectué aucun emprunt.");
            } else {
                for (Emprunt emp : empruntsDuClient) {
                    System.out.println("ID Emprunt : " + emp.getId());
                    System.out.println("  Date : " + emp.getDateDebut());
                    System.out.println("  Nombre de livres : " + emp.getLivres().size());
                }
            }
        } else {
            System.out.println("Client ID " + idClient + " non trouvé.");
        }

        em.close();
        emf.close();
    }
}