package com.example.pos.system.feature.country;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pos.system.domain.settings.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

    boolean existsByCountryNameAndStatusTrueAndIsDeletedFalse(String countryName);

    // List with pagination
    Page<Country> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    // List without pagination
    List<Country> findByStatusTrueAndIsDeletedFalse();

    Optional<Country> findByUuid(String uuid);

    Optional<Country> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // Query for search
    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tc.id ,\r\n" + //
            "\tc.country_name,\r\n" + //
            "\tc.create_by,\r\n" + //
            "\tc.create_date,\r\n" + //
            "\tc.is_deleted,\r\n" + //
            "\tc.status,\r\n" + //
            "\tc.uuid\r\n" + //
            "from\r\n" + //
            "\tpos_countries c\r\n" + //
            "where\r\n" + //
            "\tc.status = true\r\n" + //
            "\tand c.is_deleted = false\r\n" + //
            "\tand c.country_name ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tc.id desc\r\n" + //
            "")

    // search with pagination
    Page<Country> findByCountryName(PageRequest pageable, String searchValue);

    // Query for search
    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tc.id ,\r\n" + //
            "\tc.country_name,\r\n" + //
            "\tc.create_by,\r\n" + //
            "\tc.create_date,\r\n" + //
            "\tc.is_deleted,\r\n" + //
            "\tc.status,\r\n" + //
            "\tc.uuid\r\n" + //
            "from\r\n" + //
            "\tpos_countries c\r\n" + //
            "where\r\n" + //
            "\tc.status = true\r\n" + //
            "\tand c.is_deleted = false\r\n" + //
            "\tand c.country_name ilike %?% \r\n" + //
            "order by\r\n" + //
            "\tc.id desc\r\n" + //
            "")

    // search without pagination
    List<Country> findByCountryName(String searchValue);
}
