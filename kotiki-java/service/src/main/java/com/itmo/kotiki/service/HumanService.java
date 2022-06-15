package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.Role;

import java.sql.Date;
import java.util.List;

public interface HumanService {
    Long addHuman(String name, Date dateBirthday, String username, String password, Role role);

    String getNameHuman(String username);

    String getNameHuman(Long humanId);

    Date getDateHuman(Long humanId);

    Date getDateHuman(String username);

    List<String> getHumanCats(Long humanId);

    List<String> getHumanCats(String username);

    void setNameHuman(Long humanId, String name);

    void setDateHuman(Long humanId, Date date);

    List<String> getHumans();

    void setNameHuman(String username, String name);

    void setDateHuman(String username, Date date);

    List<String> getHumans(String username);
}
