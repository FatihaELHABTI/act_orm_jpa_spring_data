# Rapport du Projet : Application de Gestion Hospitalière

## Contexte et Objectifs
Ce projet a été développé dans le cadre de l'apprentissage des technologies **Spring Boot**, **Spring Data JPA**, et **Hibernate** pour la gestion des données persistantes avec une base de données relationnelle (**MariaDB**). L'objectif principal est de créer une application web permettant de gérer les entités hospitalières (patients, médecins, rendez-vous, consultations) ainsi qu'un système d'authentification simple pour les utilisateurs avec des rôles (STUDENT, USER, ADMIN). L'application vise à démontrer :
- La maîtrise des relations JPA (One-to-One, One-to-Many, Many-to-One, Many-to-Many).
- La création d'une API REST fonctionnelle.
- L'utilisation des repositories Spring Data pour interagir avec la base de données.
- L'implémentation d'un système d'authentification basique.

## Description du Projet
L'application **Gestion Hospitalière** est une solution backend qui permet de gérer les opérations hospitalières et les utilisateurs. Elle utilise une architecture modulaire avec des entités, des repositories, des services, et des contrôleurs REST. Les principales fonctionnalités incluent la gestion des patients, médecins, rendez-vous, consultations, et un système de rôles pour les utilisateurs, avec une initialisation automatique de données de test au démarrage.

## Technologies Utilisées
- **Java 17** : Langage principal.
- **Spring Boot 3.x** : Framework pour la création de l'application.
- **Spring Data JPA** : Gestion des accès aux données.
- **Hibernate** : ORM pour la gestion des entités et des relations.
- **MariaDB** : Base de données relationnelle.
- **Lombok** : Réduction du code répétitif (getters, setters, constructeurs).
- **Jackson** : Sérialisation/désérialisation JSON pour l'API REST.
- **Maven** : Gestion des dépendances.
- **MySQL Connector** : Connexion à MariaDB.

## Architecture du Projet
Le projet est organisé en plusieurs packages pour une séparation claire des responsabilités :

### 1. `entities`
Contient les entités JPA représentant les tables de la base de données :
- **Patient** : Stocke les informations des patients (nom, date de naissance, état de santé, score, rendez-vous).
- **Medecin** : Représente les médecins (nom, email, spécialité, rendez-vous).
- **RendezVous** : Gère les rendez-vous (date, statut, patient, médecin, consultation).
- **Consultation** : Contient les détails des consultations (date, rapport, rendez-vous).
- **StatusRDV** : Enumération pour les statuts des rendez-vous (PENDING, CANCELLED, DONE).

### 2. `user.entities`
Contient les entités pour la gestion des utilisateurs :
- **User** : Représente un utilisateur (ID, nom, mot de passe, rôles).
- **Role** : Représente un rôle (ID, nom, description, utilisateurs).

### 3. `repositories`
Contient les interfaces Spring Data JPA pour l'accès aux données :
- **PatientRepository** : Recherche, liste, et suppression des patients.
- **MedecinRepository** : Recherche des médecins par nom.
- **RendezVousRepository** : Recherche des rendez-vous par nom de patient.
- **ConsultationRepository** : Gestion des consultations.
- **UserRepository** : Recherche des utilisateurs par nom.
- **RoleRepository** : Recherche des rôles par nom.

### 4. `service`
Contient les services métiers :
- **IHospitalService** et **IHospitalServiceImpl** : Gestion des patients, médecins, rendez-vous, et consultations.
- **IUserService** et **UserService** : Gestion des utilisateurs, rôles, et authentification.

### 5. `controller`
Contient les contrôleurs REST :
- **PatientController** : Endpoint pour lister les patients (`GET /patients`).
- **UserRestController** : Endpoint pour récupérer un utilisateur par nom (`GET /users/{username}`).

### 6. `ActOrmJpaSpringDataApplication`
Classe principale contenant un `CommandLineRunner` pour initialiser des données de test :
- Ajout de patients, médecins, rendez-vous, et consultations.
- Création d'utilisateurs (e.g., `xavier`, `admin1`) avec attribution de rôles (STUDENT, USER, ADMIN).
- Test de l'authentification.

## Schéma de la Base de Données
La base de données (`PAT_DB`) contient les tables suivantes :
- **patients** : Stocke les informations des patients (id, nom, dateNaissance, malade, score).
- **medecins** : Stocke les informations des médecins (id, nom, email, specialite).
- **rendez_vous** : Stocke les rendez-vous (id, date, status, patient_id, medecin_id).
- **consultations** : Stocke les consultations (id, dateConsultation, rapport, rendezVous_id).
- **users** : Stocke les utilisateurs (userId, userName, password).
- **roles** : Stocke les rôles (roleId, roleName, description).
- **users_roles** : Table de jointure pour la relation Many-to-Many entre utilisateurs et rôles.

### Relations
- **Patient ↔ RendezVous** : One-to-Many (un patient peut avoir plusieurs rendez-vous).
- **Medecin ↔ RendezVous** : One-to-Many (un médecin peut avoir plusieurs rendez-vous).
- **RendezVous ↔ Consultation** : One-to-One (un rendez-vous peut avoir une consultation).
- **User ↔ Role** : Many-to-Many (un utilisateur peut avoir plusieurs rôles, un rôle peut être attribué à plusieurs utilisateurs).

## Installation et Configuration
### Prérequis
- **Java 17** ou supérieur.
- **Maven** pour gérer les dépendances.
- **MariaDB** installé et configuré.
- Un IDE comme IntelliJ IDEA ou Eclipse.

### Étapes
1. **Cloner le projet** :
   ```bash
   git clone <URL_DU_REPOSITORY>
   cd act_orm_jpa_spring_data
   ```

2. **Configurer MariaDB** :
   - Créez une base de données nommée `PAT_DB`.
   - Assurez-vous que MariaDB est accessible sur `localhost:3306`.
   - Vérifiez les informations d'identification dans `application.properties` :
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/PAT_DB?createDatabaseIfNotExist=true
     spring.datasource.username=root
     spring.datasource.password=
     spring.jpa.hibernate.ddl-auto=create
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MariaDBDialect
     spring.jpa.show-sql=true
     server.port=8085
     ```

3. **Installer les dépendances** :
   ```bash
   mvn clean install
   ```

4. **Lancer l'application** :
   Exécutez la classe principale `ActOrmJpaSpringDataApplication` ou utilisez :
   ```bash
   mvn spring-boot:run
   ```

## Utilisation
### Endpoints API
- **Lister tous les patients** :
  ```
  GET http://localhost:8085/patients
  ```
  Retourne une liste de patients au format JSON.

- **Récupérer un utilisateur par nom** :
  ```
  GET http://localhost:8085/users/{username}
  ```
  Exemple : `GET http://localhost:8085/users/xavier`

### Données de Test
Lors du démarrage, le `CommandLineRunner` insère :
- **Patients** : Hssayn, Hmad, Moloud, Rkiya.
- **Médecins** : Mostapha, Ibtissame, Soufiane (spécialités : Cardio ou Dentiste).
- **Rendez-vous** : Associés à des patients et médecins, avec statut PENDING.
- **Consultations** : Liées à des rendez-vous, avec rapports comme "bon santé" ou "malade".
- **Utilisateurs** : `xavier` (rôles : STUDENT, USER), `admin1` (rôles : ADMIN, USER).

### Exemple de Résultat
Lors de l'exécution, la console affiche :
- Les patients créés avec leurs détails.
- Les rôles attribués aux utilisateurs.
- Le résultat de l'authentification pour l'utilisateur `xavier`.

## Résultats et Validation
- **Fonctionnalités testées** :
  - Création et persistance des entités dans la base de données.
  - Recherche des entités par nom (patients, médecins, rendez-vous, utilisateurs, rôles).
  - Gestion des relations entre entités (e.g., Many-to-Many entre User et Role).
  - Authentification simple des utilisateurs.
  - Exposition des données via API REST.
- **Validation** :
  - Les tables sont correctement créées dans MariaDB grâce à `spring.jpa.hibernate.ddl-auto=create`.
  - Les endpoints REST fonctionnent comme prévu (testés avec Postman ou un navigateur).
  - Les relations entre entités sont respectées (vérifiées via les données de test).

