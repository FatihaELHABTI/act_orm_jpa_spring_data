package fatiha.elhabti.act_orm_jpa_spring_data.user.service;

import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.Role;
import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.User;
import fatiha.elhabti.act_orm_jpa_spring_data.user.repositories.RoleRepository;
import fatiha.elhabti.act_orm_jpa_spring_data.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = this.findUserByUserName(userName);
        Role role = this.findRoleByRoleName(roleName);
        if(user.getRoles() != null) {
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
    }

    @Override
    public User authenticate(String username, String password) {
        User user = this.findUserByUserName(username);
        if(user == null) throw new RuntimeException("Bad credentials");
        if(user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Invalid password for user "+user.getUserName());
    }
}
