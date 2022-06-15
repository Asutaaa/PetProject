package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumanEntity;
import com.itmo.kotiki.repository.CatsRepository;
import com.itmo.kotiki.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class UserServiceImpl implements UserService {

    private final CatsRepository catsDao;
    private final HumanRepository humanDao;

    @Autowired
    public UserServiceImpl(CatsRepository catsDao, HumanRepository humanDao) {

        this.catsDao = catsDao;
        this.humanDao = humanDao;
    }

    @Override
    public void setHumanCats(Long idHuman, Long idCats) {
        HumanEntity human = humanDao.getById(idHuman);
        CatsEntity cats = catsDao.getById(idCats);
        cats.setHumanEntity(human);
        human.addCat(cats);
    }
}
