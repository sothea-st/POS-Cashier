package com.example.pos.connection1.feature.settings.slot;

import com.example.pos.connection1.entity.settings.Slot;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface SlotRepository extends JpaRepository<Slot,Integer> {

    // Check if slot name kh is already existed?
    Boolean existsBySlotNameKhAndStatusTrueAndIsDeletedFalseAndSlotNameKhIsNotNull(String slotNameKh);

    // Check if slot name kh is already existed?
    Boolean existsBySlotNameEnAndStatusTrueAndIsDeletedFalse(String slotNameEn);

    // List status with page Number and page size
    Page<Slot> findByStatusTrueAndIsDeletedFalse(PageRequest pageRequest);

    // List status with page Number and page size
    List<Slot> findByStatusTrueAndIsDeletedFalse();

    // Get slot by id
    Optional<Slot> findByIdAndStatusTrueAndIsDeletedFalse(Integer id);

    // Search slot
    @Query(value = """
       select u from Slot u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.slotNameEn) like lower(concat('%', :name, '%'))
       or lower(u.slotNameKh) like lower(concat('%', :name, '%')))
       """)
    Page<Slot> searchBySlotNameEnOrSlotNameKh(PageRequest pageRequest, @PathVariable("name") String name);

    // Search slot
    @Query(value = """
       select u from Slot u
       where u.status = true
       and u.isDeleted = false
       and (lower(u.slotNameEn) like lower(concat('%', :name, '%'))
       or lower(u.slotNameKh) like lower(concat('%', :name, '%')))
       """)
    List<Slot> searchBySlotNameEnOrSlotNameKh(@PathVariable("name") String name);
}
