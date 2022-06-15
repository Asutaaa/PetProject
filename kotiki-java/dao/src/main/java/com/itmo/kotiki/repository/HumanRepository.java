package com.itmo.kotiki.repository;

import com.itmo.kotiki.entity.HumanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("HumanRepository")
public interface HumanRepository extends JpaRepository<HumanEntity, Long> {
    Optional<HumanEntity> findByUsername(String username);
}
