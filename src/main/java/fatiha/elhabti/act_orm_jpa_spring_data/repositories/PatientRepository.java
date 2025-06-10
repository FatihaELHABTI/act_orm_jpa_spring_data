package fatiha.elhabti.act_orm_jpa_spring_data.repositories;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    Patient findByNomContains(String mc);
    List<Patient> findAll();
    void deletePatientByNom(String nom);
}