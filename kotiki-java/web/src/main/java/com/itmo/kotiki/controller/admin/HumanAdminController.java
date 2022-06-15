package com.itmo.kotiki.controller.admin;

import com.itmo.kotiki.entity.Role;
import com.itmo.kotiki.service.HumanService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
public class HumanAdminController {
    private HumanService humanService;

    public HumanAdminController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/humanNameAdmin")
    public String getNameHumanAdmin(@RequestParam Long id) {
        return humanService.getNameHuman(id);
    }

    @GetMapping("/humanDateAdmin")
    public Date getDateHumanAdmin(@RequestParam Long id) {
        return humanService.getDateHuman(id);
    }

    @GetMapping("/humanCatsAdmin")
    public List<String> getHumanCatsAdmin(@RequestParam Long id) {
        return humanService.getHumanCats(id);
    }

    @GetMapping("/humanAllAdmin")
    public List<String> getHumansAdmin() {
        return humanService.getHumans();
    }

    @PostMapping("/humanSignUpAdmin")
    public void humanSignUpAdmin(@RequestParam String name, Long data, String userName, String password, Role role) {
        humanService.addHuman(name, new java.sql.Date(data), userName, password, role);
    }
}
