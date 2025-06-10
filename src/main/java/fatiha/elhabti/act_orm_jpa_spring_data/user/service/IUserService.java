package fatiha.elhabti.act_orm_jpa_spring_data.user.service;


import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.Role;
import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.User;

public interface IUserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String userName, String roleName);
    User authenticate(String username, String password);

}