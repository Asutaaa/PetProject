package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.ColorCat;
import com.itmo.kotiki.entity.HumanEntity;
import com.itmo.kotiki.repository.CatsRepository;
import com.itmo.kotiki.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("CatsService")
public class CatsServiceImpl implements CatsService {
    private final CatsRepository catsDao;
    private final HumanRepository humanDao;

    @Autowired
    public CatsServiceImpl(CatsRepository catsDao, HumanRepository humanRepository) {

        this.catsDao = catsDao;
        this.humanDao = humanRepository;
    }

    public Long addCats(String name, Date dateBirthday, ColorCat color, String breed) {
        CatsEntity cats = new CatsEntity(name, dateBirthday, color, breed);
        catsDao.saveAndFlush(cats);
        return cats.getId();
    }

    @Override
    public Long addCats(String name, Date dateBirthday, ColorCat color, String breed, String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        CatsEntity cats = new CatsEntity(name, dateBirthday, color, breed);
        catsDao.saveAndFlush(cats);
        cats.setHumanEntity(human);
        human.addCat(cats);
        return cats.getId();
    }


    public String getNameCat(Long catId) {
        CatsEntity cats = catsDao.getById(catId);
        return cats.getName();
    }

    @Override
    public String getNameCat(Long catId, String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        CatsEntity cats = catsDao.getById(catId);
        if (human.getId() != cats.getHumanEntity().getId()){
            return null;
        }
        return cats.getName();
    }

    public String getColorCat(Long catId) {
        CatsEntity cats = catsDao.getById(catId);
        return cats.getColor();
    }

    @Override
    public String getColorCat(Long catIdLong, String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        CatsEntity cats = catsDao.getById(catIdLong);
        if (human.getId() != cats.getHumanEntity().getId()){
            return null;
        }
        return cats.getColor();
    }

    public String getBreedCat(Long catId) {
        CatsEntity cats = catsDao.getById(catId);
        return cats.getBreed();
    }

    @Override
    public String getBreedCat(Long catId, String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        CatsEntity cats = catsDao.getById(catId);
        if (human.getId() != cats.getHumanEntity().getId()){
            return null;
        }
        return cats.getBreed();
    }

    public String getCatHuman(Long catsId) {
        CatsEntity cats = catsDao.getById(catsId);
        return cats.getHumanEntity().toString();
    }

    @Override
    public String getCatHuman(Long catsId, String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        return human.getName();
    }


    public void setNameCat(Long catId, String name) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setName(name);
        catsDao.saveAndFlush(cats);
    }

    @Override
    public void setNameCat(Long catId, String name, String username) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setName(name);
        catsDao.saveAndFlush(cats);
        HumanEntity human = humanDao.findByUsername(username).get();
        if (human.getId() == cats.getHumanEntity().getId()){
            catsDao.saveAndFlush(cats);
        }
    }

    public void setColorCat(Long catId, String color) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setColor(color);
        catsDao.saveAndFlush(cats);
    }

    @Override
    public void setColorCat(Long catId, String color, String username) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setColor(color);
        HumanEntity human = humanDao.findByUsername(username).get();
        if (human.getId() == cats.getHumanEntity().getId()){
            catsDao.saveAndFlush(cats);
        }
    }

    public void setBreedCat(Long catId, String breed) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setBreed(breed);
        catsDao.saveAndFlush(cats);
    }

    @Override
    public void setBreedCat(Long catId, String breed, String username) {
        CatsEntity cats = catsDao.getById(catId);
        cats.setBreed(breed);
        HumanEntity human = humanDao.findByUsername(username).get();
        if (human.getId() == cats.getHumanEntity().getId()){
            catsDao.saveAndFlush(cats);
        }
    }

    public List<String> getCats() {
        List<String> allCats = new ArrayList<>();
        for (CatsEntity cat : catsDao.findAll()) {
            allCats.add(cat.toString());
        }
        return allCats;
    }

    @Override
    public List<String> getCats(String username) {
        List<String> allCats = new ArrayList<>();
        HumanEntity human = humanDao.findByUsername(username).get();
        for (CatsEntity cat: human.getCatsById()){
            allCats.add(cat.toString());
        }
        return allCats;
    }
}
