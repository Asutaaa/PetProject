package com.itmo.kotiki.controller.user;

import com.itmo.kotiki.service.HumanService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@Secured("ROLE_USER")
public class HumanUserController {
    HumanService humanService;

    public HumanUserController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/humanName")
    public String getNameHuman() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return humanService.getNameHuman(aut.getName());
    }

    @GetMapping("/humanDate")
    public Date getDateHuman(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return humanService.getDateHuman(id);
    }

    @GetMapping("/humanCats")
    public List<String> getHumanCats(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return humanService.getHumanCats(aut.getName());
    }

    @GetMapping("/humanAll")
    public List<String> getHumans() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return humanService.getHumans(aut.getName());
    }
}
