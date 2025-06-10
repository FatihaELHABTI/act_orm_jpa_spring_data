package fatiha.elhabti.act_orm_jpa_spring_data.service;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.Consultation;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.Medecin;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.Patient;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.RendezVous;

import java.util.List;

public interface IHospitalService {

    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsultation(Consultation consultation);

    RendezVous chercherRendezVousParPatient(String nomPatient);

    Patient charcherPatientParNom(String nomPatient);

    Medecin chercherMedecinParNom(String medecinPatient);

    List<Patient> afficherPatients();


}
