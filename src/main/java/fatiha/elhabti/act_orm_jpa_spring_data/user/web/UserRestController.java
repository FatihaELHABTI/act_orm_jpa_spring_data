package fatiha.elhabti.act_orm_jpa_spring_data.user.web;

import fatiha.elhabti.act_orm_jpa_spring_data.user.entities.User;
import fatiha.elhabti.act_orm_jpa_spring_data.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable String username) {
        return userService.findUserByUserName(username);
    }
}