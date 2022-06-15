package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumanEntity;
import com.itmo.kotiki.entity.Role;
import com.itmo.kotiki.entity.UserinfoEntity;
import com.itmo.kotiki.repository.HumanRepository;
import com.itmo.kotiki.repository.UserinfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service("HumanService")
public class HumanServiceImpl implements HumanService {
    private final HumanRepository humanDao;
    private final UserinfoRepository userinfoDao;

    @Autowired
    public HumanServiceImpl(HumanRepository humanDao, UserinfoRepository userinfoRepository) {
        this.humanDao = humanDao;
        this.userinfoDao =userinfoRepository;
    }

    public Long addHuman(String name, Date dateBirthday, String username, String password, Role role) {
        HumanEntity human = new HumanEntity(name, dateBirthday, username);
        humanDao.saveAndFlush(human);
        UserinfoEntity userinfoEntity = new UserinfoEntity(username, password, human.getId(), role);
        userinfoDao.saveAndFlush(userinfoEntity);
        return human.getId();
    }

    @Override
    public String getNameHuman(String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        return human.getName();
    }

    public String getNameHuman(Long humanId) {
        HumanEntity human = humanDao.getById(humanId);
        return human.getName();
    }

    public Date getDateHuman(Long humanId) {
        HumanEntity human = humanDao.getById(humanId);
        return human.getDateBirthday();
    }

    @Override
    public Date getDateHuman(String username) {
        HumanEntity human = humanDao.findByUsername(username).get();
        return human.getDateBirthday();
    }

    public List<String> getHumanCats(Long humanId) {
        HumanEntity human = humanDao.getById(humanId);
        List<String> catsToString = new ArrayList<>();
        for (CatsEntity cats : human.getCatsById()) {
            catsToString.add(cats.toString());
        }
        return catsToString;
    }

    @Override
    public List<String> getHumanCats(String username) {
        HumanEntity human =humanDao.findByUsername(username).get();
        List<String> catsToString = new ArrayList<>();
        for (CatsEntity cats : human.getCatsById()) {
            catsToString.add(cats.toString());
        }
        return catsToString;
    }


    public void setNameHuman(Long humanId, String name) {
        HumanEntity human = humanDao.getById(humanId);
        human.setName(name);
        humanDao.saveAndFlush(human);
    }

    public void setDateHuman(Long humanId, Date date) {
        HumanEntity human = humanDao.getById(humanId);
        human.setDateBirthday(date);
        humanDao.saveAndFlush(human);
    }

    public List<String> getHumans() {
        List<String> allHuman = new ArrayList<>();
        for (HumanEntity human : humanDao.findAll()) {
            allHuman.add(human.toString());
        }
        return allHuman;
    }

    @Override
    public void setNameHuman(String username, String name) {
        HumanEntity human = humanDao.findByUsername(username).get();
        human.setName(name);
        humanDao.saveAndFlush(human);
    }

    @Override
    public void setDateHuman(String username, Date date) {
        HumanEntity human = humanDao.findByUsername(username).get();
        human.setDateBirthday(date);
        humanDao.saveAndFlush(human);
    }

    @Override
    public List<String> getHumans(String username) {
        List<String> allHuman = new ArrayList<>();
        for (HumanEntity human : humanDao.findAll()) {
            allHuman.add(human.toString());
        }
        return allHuman;
    }
}
