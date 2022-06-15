package com.itmo.kotiki.controller.user;

import com.itmo.kotiki.entity.ColorCat;
import com.itmo.kotiki.service.CatsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@Secured("ROLE_USER")
public class CatsUserController {
    private CatsService catsService;

    public CatsUserController(CatsService catsService) {

        this.catsService = catsService;
    }

    @GetMapping("/catName")
    public String getCat(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return catsService.getNameCat(id, aut.getName());
    }

    @GetMapping("/catColor")
    public String getColorCat(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return catsService.getColorCat(id, aut.getName());
    }

    @GetMapping("/catBreed")
    public String getBreedCat(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return catsService.getBreedCat(id, aut.getName());
    }

    @GetMapping("/catHuman")
    public String getCatHuman(@RequestParam Long id) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return catsService.getCatHuman(id, aut.getName());
    }

    @GetMapping("/catAll")
    public List<String> getCats() {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        return catsService.getCats(aut.getName());
    }

    @PostMapping("/catSignUp")
    public void CatSignUp(@RequestParam String name, int date, ColorCat color, String breed){
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        catsService.addCats(name, new Date(date), color, breed, aut.getName());
    }
}
