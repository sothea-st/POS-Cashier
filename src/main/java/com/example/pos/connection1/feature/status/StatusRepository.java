package com.example.pos.connection1.feature.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pos.connection1.entity.Status;
import com.example.pos.connection1.entity.Uom;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Integer>{
    Optional<Status> findById(Integer id);

    Optional<Status> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Page<Status> findByStatusTrueAndIsDeletedFalse (PageRequest pageable);

    @Query(nativeQuery = true, value = "select\r\n" + //
        "\ts.id ,\r\n" + //
        "\ts.status_name,\r\n" + //
        "\ts.is_deleted,\r\n" + //
        "\ts.status\r\n" + //
        "from\r\n" + //
        "\tpos_status s\r\n" + //
        "where\r\n" + //
        "\ts.status = true\r\n" + //
        "\tand s.is_deleted = false\r\n" + //
        "\tand s.status_name ilike %?% \r\n" + //
        "order by\r\n" + //
        "\ts.id desc\r\n" + //
        "")
    Page<Status> findByStatusName (PageRequest pageable, String valueSearch);
}
