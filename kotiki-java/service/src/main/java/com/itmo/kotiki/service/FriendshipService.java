package com.itmo.kotiki.service;

import java.util.List;

public interface FriendshipService {

    void addFriendShip(int catIdOne, int catIdTwo);

    List<String> getCats();
}
