package fatiha.elhabti.act_orm_jpa_spring_data.repositories;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    RendezVous findRendezVousByPatientNom(String patientName);

}
