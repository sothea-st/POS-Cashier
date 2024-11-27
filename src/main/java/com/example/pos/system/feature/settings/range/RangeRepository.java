package com.example.pos.system.feature.settings.range;

import com.example.pos.system.domain.settings.Ranges;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface RangeRepository extends JpaRepository<Ranges,Integer> {

    // Check if range name kh is already existed?
    // Boolean existsByRangeNameKhAndStatusTrueAndIsDeletedFalseAndRangeNameKhIsNotNull(String rangeNameKh);

    // Check if range name kh is already existed?
    // Boolean existsByRangeNameEnAndStatusTrueAndIsDeletedFalse(String rangeNameEn);

    // List range with page Number and page size
    Page<Ranges> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    // List range with page Number and page size
    List<Ranges> findByStatusTrueAndIsDeletedFalse();

    // Get range by id
    Optional<Ranges> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // Search range by rangeNameEn or rangeNameKh
    @Query(value = """
       select u from Ranges u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.rangeNameEn) like lower(concat('%', :name, '%'))
       or lower(u.rangeNameKh) like lower(concat('%', :name, '%')))
       """)
    Page<Ranges> searchByRangeNameEnOrRangeNameKh(PageRequest pageRequest, @PathVariable("name") String name);

    // Search range by rangeNameEn or rangeNameKh
    @Query(value = """
       select u from Ranges u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.rangeNameEn) like lower(concat('%', :name, '%'))
       or lower(u.rangeNameKh) like lower(concat('%', :name, '%')))
       """)
    List<Ranges> searchByRangeNameEnOrRangeNameKh(@PathVariable("name") String name);
}
