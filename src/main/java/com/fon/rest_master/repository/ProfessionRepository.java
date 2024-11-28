package com.fon.rest_master.repository;

import com.fon.rest_master.domain.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

    Optional<Profession> findByName(String name);
}
