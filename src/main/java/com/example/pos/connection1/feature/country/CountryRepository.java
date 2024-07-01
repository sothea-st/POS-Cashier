package com.example.pos.connection1.feature.country;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pos.connection1.entity.Country;

public interface CountryRepository extends JpaRepository<Country,Integer> {
    Page<Country> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    Optional<Country> findByUuid(String uuid);

}
