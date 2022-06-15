package com.itmo.kotiki.repository;

import com.itmo.kotiki.entity.FriendshipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FriendshipRepository")
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Long> {
}
