package fatiha.elhabti.act_orm_jpa_spring_data.user.repositories;

import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUserName(String username);
}