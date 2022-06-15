package com.itmo.kotiki.repository;

import com.itmo.kotiki.entity.HumanEntity;
import com.itmo.kotiki.entity.UserinfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("UserinfoRepository")
public interface UserinfoRepository extends JpaRepository<UserinfoEntity, Long> {
    Optional<UserinfoEntity> findByUsername(String username);
}