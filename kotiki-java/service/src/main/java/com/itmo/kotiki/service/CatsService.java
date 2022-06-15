package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.ColorCat;

import java.sql.Date;
import java.util.List;

public interface CatsService {
    Long addCats(String name, Date dateBirthday, ColorCat color, String breed);

    Long addCats(String name, Date dateBirthday, ColorCat color, String breed, String username);

    String getNameCat(Long catId);

    String getNameCat(Long catId, String username);

    String getColorCat(Long catId);

    String getColorCat(Long catIdLong, String username);

    String getBreedCat(Long catId);

    String getBreedCat(Long catId, String username);

    String getCatHuman(Long catsId);

    String getCatHuman(Long catsId, String username);

    void setNameCat(Long catId, String name);

    void setNameCat(Long catId, String name, String username);

    void setColorCat(Long catId, String color);

    void setColorCat(Long catId, String color, String username);

    void setBreedCat(Long catId, String breed);

    void setBreedCat(Long catId, String breed, String username);

    List<String> getCats();

    List<String> getCats( String username);
}
