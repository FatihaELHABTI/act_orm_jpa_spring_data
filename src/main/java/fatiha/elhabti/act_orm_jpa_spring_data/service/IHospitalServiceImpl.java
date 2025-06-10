package fatiha.elhabti.act_orm_jpa_spring_data.service;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.Consultation;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.Medecin;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.Patient;
import fatiha.elhabti.act_orm_jpa_spring_data.entities.RendezVous;
import fatiha.elhabti.act_orm_jpa_spring_data.repositories.ConsultationRepository;
import fatiha.elhabti.act_orm_jpa_spring_data.repositories.MedecinRepository;
import fatiha.elhabti.act_orm_jpa_spring_data.repositories.PatientRepository;
import fatiha.elhabti.act_orm_jpa_spring_data.repositories.RendezVousRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class IHospitalServiceImpl implements IHospitalService {

    PatientRepository patientRepository;
    MedecinRepository medecinRepository;
    RendezVousRepository rendezVousRepository;
    ConsultationRepository consultationRepository;

    public IHospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {

        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {

        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsultation(Consultation consultation) {

        return consultationRepository.save(consultation);
    }

    @Override
    public RendezVous chercherRendezVousParPatient(String nomPatient) {

        return rendezVousRepository.findRendezVousByPatientNom(nomPatient);
    }

    @Override
    public Patient charcherPatientParNom(String nomPatient) {
        return patientRepository.findByNomContains(nomPatient);
    }

    @Override
    public Medecin chercherMedecinParNom(String nomMedecin) {
        return medecinRepository.findByNom(nomMedecin);
    }


    @Override
    public List<Patient> afficherPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }


}
