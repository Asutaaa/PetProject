package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.FriendshipEntity;
import com.itmo.kotiki.repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("FriendshipService")
public class FriendshipServiceImpl implements FriendshipService {
    private final FriendshipRepository friendshipDao;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipDao) {
        this.friendshipDao = friendshipDao;
    }

    public void addFriendShip(int catIdOne, int catIdTwo) {
        FriendshipEntity friendship = new FriendshipEntity(catIdOne, catIdTwo);
        friendshipDao.saveAndFlush(friendship);
    }

    public List<String> getCats() {
        List<String> FriendShipAll = new ArrayList<>();
        for (FriendshipEntity friendship : friendshipDao.findAll()) {
            FriendShipAll.add(friendship.toString());
        }
        return FriendShipAll;
    }
}
