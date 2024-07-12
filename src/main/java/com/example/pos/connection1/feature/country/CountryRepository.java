package com.example.pos.connection1.feature.country;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pos.connection1.entity.Country;


public interface CountryRepository extends JpaRepository<Country,Integer> {
    Page<Country> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    Optional<Country> findByUuid(String uuid);

    Optional<Country> findByIdAndStatusTrueAndIsDeletedFalse(int id);
 


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
    Page<Country> findByCountryName(PageRequest pageable, String valueSearch);
}
