package fatiha.elhabti.act_orm_jpa_spring_data;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.*;
import fatiha.elhabti.act_orm_jpa_spring_data.repositories.PatientRepository;
import fatiha.elhabti.act_orm_jpa_spring_data.service.IHospitalServiceImpl;
import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.Role;
import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.User;
import fatiha.elhabti.act_orm_jpa_spring_data.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class ActOrmJpaSpringDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActOrmJpaSpringDataApplication.class, args);
    }


//    @Bean
    CommandLineRunner start(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient(null, "Hssayn", new Date(), false, 12, null));
            Patient patient = new Patient();
            patient.setNom("Ahmed");
            patient.setDateNaissance(new Date());
            patient.setMalade(false);
            patient.setScore(25);
            patientRepository.save(patient);

        };
    }

    @Bean
    CommandLineRunner commandLineRunner(IHospitalServiceImpl service, UserService userService) {
        return args -> {

            // Ajouter des patients
            service.savePatient(new Patient(null, "Hssayn", new Date(), false, 12, null));
            service.savePatient(new Patient(null, "Hmad", new Date(), true, 34, null));
            service.savePatient(new Patient(null, "Moloud", new Date(), true, 65, null));
            service.savePatient(new Patient(null, "Rkiya", new Date(), false, 78, null));

            System.out.println("***********************************");

            // Ajouter des Medecins
            Stream.of("Mostapha", "Ibtissame", "Soufiane").forEach(name -> {

                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name+"123@gmail.com");
                medecin.setSpecialite(Math.random()>0.5?"Cardio": "Dentiste");
                service.saveMedecin(medecin);
            });

            System.out.println("***********************************");

            // Ajouter des Rendez-Vous

            Patient patient = service.charcherPatientParNom("Hssayn");
            Medecin medecin = service.chercherMedecinParNom("Ibtissame");
            service.saveRDV(new RendezVous(null, new Date(), StatusRDV.PENDING, patient, medecin, null));

            System.out.println("***********************************");
            // Ajouter des Consultations

            RendezVous rdvP1 = service.chercherRendezVousParPatient("Hmad");
            RendezVous rdvP2 = service.chercherRendezVousParPatient("Moloud");
            service.saveConsultation(new Consultation(null, new Date(), "bon santé", rdvP1));
            service.saveConsultation(new Consultation(null, new Date(), "malade", rdvP2));

            System.out.println("***********************************");



            //Consulter tous les patients

            List<Patient> patients = service.afficherPatients();
            patients.forEach( p -> {
                System.out.println(p.getId());
                System.out.println(p.getNom());
                System.out.println(p.getDateNaissance());
                System.out.println(p.isMalade());
                System.out.println(p.getScore());
                System.out.println("***********************************");
            });


            // the block of @ManyToMany

            User user1 = new User();
            user1.setUserName("xavier");
            user1.setPassword("X12345");
            userService.addNewUser(user1);

            User user2 = new User();
            user2.setUserName("admin1");
            user2.setPassword("V45678");
            userService.addNewUser(user2);

            Stream.of("STUDENT", "ADMIN", "USER").forEach(name -> {
                Role role = new Role();
                role.setRoleName(name);
                userService.addNewRole(role);
            });


            userService.addRoleToUser("xavier","STUDENT");
            userService.addRoleToUser("xavier","USER");
            userService.addRoleToUser("admin1","ADMIN");
            userService.addRoleToUser("admin1","USER");

            try {
                User user = userService.authenticate("xavier","X12345");
                System.out.println(user.getUserId());
                System.out.println(user.getUserName());
                System.out.println("Roles => ");
                Stream.of(user.getRoles()).forEach(role -> {
                    System.out.println(role);
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


          };
    }
}
