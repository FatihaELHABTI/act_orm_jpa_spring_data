package fatiha.elhabti.act_orm_jpa_spring_data.controller;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.Patient;
import fatiha.elhabti.act_orm_jpa_spring_data.service.IHospitalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    @Autowired
    private IHospitalServiceImpl hospitalServiceImpl;

    @GetMapping("/patients")
    public List<Patient> patientList(){
        return hospitalServiceImpl.afficherPatients();
    }
}
