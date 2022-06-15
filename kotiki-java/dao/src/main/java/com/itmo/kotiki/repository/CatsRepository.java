package com.itmo.kotiki.repository;

import com.itmo.kotiki.entity.CatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("CatsRepository")
public interface CatsRepository extends JpaRepository<CatsEntity, Long> {
}
