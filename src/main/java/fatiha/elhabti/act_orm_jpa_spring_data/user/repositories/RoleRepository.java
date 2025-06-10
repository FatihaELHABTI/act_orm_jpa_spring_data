package fatiha.elhabti.act_orm_jpa_spring_data.user.repositories;

import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);
}
