# TP JPA 02 & 03 - Gestion de Bibliothèque

Ce projet est un exercice pratique de mise en place de **JPA (Java Persistence API)** avec **Hibernate** et une base de données **MariaDB**.

Il regroupe les travaux des TP 2 et 3, simulant une gestion simplifiée de bibliothèque.

## Technologies
* Java 21
* Maven
* Hibernate 7.2.3
* MariaDB Connector 3.5.7
* Base de données : MariaDB (Port 3306)

## Configuration Requise
Pour lancer le projet, il faut une base de données MariaDB locale configurée comme suit (voir `persistence.xml`) :
* **URL** : `jdbc:mariadb://localhost:3306/demo`
* **User** : `root`
* **Password** : `root`

> *Note : Les scripts SQL de création des tables et d'insertion des données (fournis dans les énoncés des TP) doivent être exécutés avant de lancer l'application.*

---

## TP 2 : Premiers pas et CRUD
**Objectif** : Mapper une entité simple et effectuer des opérations de base.

* **Entité** : `Livre` (ID, Titre, Auteur).
* **Fonctionnalités implémentées** :
    * Connexion à la base de données via `EntityManager`.
    * **C**reate : Insertion d'un livre.
    * **R**ead : Recherche par ID (`find`) et requêtes JPQL (par Titre et Auteur).
    * **U**pdate : Modification d'un livre.
    * **D**elete : Suppression d'un livre.
* **Point d'entrée** : Classe `fr.epsi_26.App`

---

## TP 3 : Les Relations JPA
**Objectif** : Gérer les relations entre plusieurs entités (@OneToMany, @ManyToOne, @ManyToMany).

* **Nouvelles Entités** :
    * `Client` (Nom, Prénom, Liste d'emprunts).
    * `Emprunt` (Date, Délai, Client associé, Liste de livres).
* **Relations implémentées** :
    * **Client ↔ Emprunt** : Relation bidirectionnelle (`@OneToMany` / `@ManyToOne`).
    * **Emprunt ↔ Livre** : Relation ManyToMany (`@ManyToMany`) via la table de jointure `COMPO`.
* **Fonctionnalités implémentées** :
    * Extraction d'un emprunt et affichage de ses livres associés.
    * Extraction d'un client et affichage de tous ses emprunts.
* **Point d'entrée** : Classe `fr.epsi_26.TestBibliotheque`

---

LOURGOUILLOUX Renaud - EPSI B3 DEVIA - 2026