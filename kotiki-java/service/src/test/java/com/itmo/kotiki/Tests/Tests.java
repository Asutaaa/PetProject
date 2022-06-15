package com.itmo.kotiki.Tests;

import com.itmo.kotiki.entity.*;
import com.itmo.kotiki.repository.CatsRepository;
import com.itmo.kotiki.repository.FriendshipRepository;
import com.itmo.kotiki.repository.HumanRepository;
import com.itmo.kotiki.repository.UserinfoRepository;
import com.itmo.kotiki.service.CatsService;
import com.itmo.kotiki.service.CatsServiceImpl;
import com.itmo.kotiki.service.HumanService;
import com.itmo.kotiki.service.HumanServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Tests {
    private HumanRepository humanDao;
    private CatsRepository catsDao;
    private FriendshipRepository friendshipDao;
    private UserinfoRepository userDao;

    private CatsService catsService;
    private HumanService humanService;

    @Test
    public void CheckGetNameHuman() {

        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();
        UserinfoEntity user = new UserinfoEntity("asuta", "123", idOne, Role.ROLE_USER);
        Long idTwo = user.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);
        userDao = mock(UserinfoRepository.class);
        when(userDao.getById(idTwo)).thenReturn(user);

        humanService = new HumanServiceImpl(humanDao, userDao);

        String name = humanService.getNameHuman(idOne);
        Assertions.assertEquals("Nastya", name);
    }

    @Test
    public void CheckGetNameCats() {
        CatsEntity cats = new CatsEntity("Tina", new Date(1124511), ColorCat.valueOf("BLACK"), "no");
        Long id = cats.getId();
        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);


        catsDao = mock(CatsRepository.class);
        when(catsDao.getById(id)).thenReturn(cats);

        catsService = new CatsServiceImpl(catsDao, humanDao);

        String name = catsService.getNameCat(id);
        Assertions.assertEquals("Tina", name);
    }

    @Test
    public void CheckSetNameCats() {
        CatsEntity cats = new CatsEntity("Tina", new Date(1124511), ColorCat.valueOf("BLACK"), "no");
        Long id = cats.getId();
        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);

        catsDao = mock(CatsRepository.class);
        when(catsDao.getById(id)).thenReturn(cats);

        catsService = new CatsServiceImpl(catsDao, humanDao);

        catsService.setNameCat(id, "Lyna");
        String name = catsService.getNameCat(id);
        Assertions.assertEquals("Lyna", name);
    }

    @Test
    public void CheckSetColorCats() {
        CatsEntity cats = new CatsEntity("Tina", new Date(1124511), ColorCat.valueOf("BLACK"), "no");
        Long id = cats.getId();
        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);

        catsDao = mock(CatsRepository.class);
        when(catsDao.getById(id)).thenReturn(cats);

        catsService = new CatsServiceImpl(catsDao, humanDao);

        catsService.setColorCat(id, "orange");
        String name = catsService.getColorCat(id);
        Assertions.assertEquals("orange", name);
    }

    @Test
    public void CheckGetBreedCats() {
        CatsEntity cats = new CatsEntity("Tina", new Date(1124511), ColorCat.valueOf("BLACK"), "no");
        Long id = cats.getId();
        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);

        catsDao = mock(CatsRepository.class);
        when(catsDao.getById(id)).thenReturn(cats);

        catsService = new CatsServiceImpl(catsDao, humanDao);

        String color = catsService.getBreedCat(id);
        Assertions.assertEquals("no", color);
    }

    @Test
    public void CheckSetBreedCats() {
        CatsEntity cats = new CatsEntity("Tina", new Date(1124511), ColorCat.valueOf("BLACK"), "no");
        Long id = cats.getId();
        HumanEntity human = new HumanEntity("Nastya", new Date(151154),"nastya");
        Long idOne = human.getId();

        humanDao = mock(HumanRepository.class);
        when(humanDao.getById(idOne)).thenReturn(human);

        catsDao = mock(CatsRepository.class);
        when(catsDao.getById(id)).thenReturn(cats);

        catsService = new CatsServiceImpl(catsDao, humanDao);

        catsService.setBreedCat(id, "yes");
        String name = catsService.getBreedCat(id);
        Assertions.assertEquals("yes", name);
    }


}