package fatiha.elhabti.act_orm_jpa_spring_data.repositories;

import fatiha.elhabti.act_orm_jpa_spring_data.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Medecin findByNom(String nom);
}