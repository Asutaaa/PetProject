package com.itmo.kotiki.controller.admin;

import com.itmo.kotiki.entity.ColorCat;
import com.itmo.kotiki.service.CatsService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;


@RestController
@Secured("ROLE_ADMIN")
public class CatsAdminController {
    private CatsService catsService;

    public CatsAdminController(CatsService catsService) {
        this.catsService = catsService;
    }

    @GetMapping("/catNameAdmin")
    public String getCatAdmin(@RequestParam Long id) {
        return catsService.getNameCat(id);
    }

    @GetMapping("/catColorAdmin")
    public String getColorCatAdmin(@RequestParam Long id) {
        return catsService.getColorCat(id);
    }

    @GetMapping("/catBreedAdmin")
    public String getBreedCatAdmin(@RequestParam Long id) {
        return catsService.getBreedCat(id);
    }

    @GetMapping("/catHumanAdmin")
    public String getCatHumanAdmin(@RequestParam Long id) {
        return catsService.getCatHuman(id);
    }

    @GetMapping("/catAllAdmin")
    public List<String> getCatsAdmin() {
        return catsService.getCats();
    }

    @PostMapping("/catSignUpAdmin")
    public void CatSignUpAdmin(@RequestParam String name, int date, ColorCat color, String breed){
        catsService.addCats(name, new Date(date), color, breed);
    }
}
