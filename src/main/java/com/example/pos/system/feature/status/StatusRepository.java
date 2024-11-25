package com.example.pos.system.feature.status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.pos.system.domain.Status;

import java.util.List;
import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    boolean existsByStatusName(String statusName);

    Optional<Status> findById(Integer id);

    Optional<Status> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    Optional<Status> findByStatusName(String statusName);

    //Get List with pagination
    Page<Status> findByStatusTrueAndIsDeletedFalse(PageRequest pageable);

    //Get List without pgination
    List<Status> findByStatusTrueAndIsDeletedFalse();

    //query for search
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
    //search with pagination
    Page<Status> searchByStatusName(PageRequest pageable, String valueSearch);

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

    //search without pagination
    List<Status> searchByStatusName(String valueSearch);
}
