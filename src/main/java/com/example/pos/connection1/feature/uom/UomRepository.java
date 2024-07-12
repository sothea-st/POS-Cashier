package com.example.pos.connection1.feature.uom;

import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.connection1.entity.Uom;

@Repository
public interface UomRepository extends JpaRepository<Uom,Integer>{

    Optional<Uom> findById (Integer id); 

    Optional<Uom> findByIdAndStatusTrueAndIsDeletedFalse (Integer id);
 


    Page<Uom> findByStatusTrueAndIsDeletedFalse (PageRequest pageable);

    @Query(nativeQuery = true, value = "select\r\n" + //
        "\tu.id ,\r\n" + //
        "\tu.name_en,\r\n" + //
        "\tu.name_kh,\r\n" + //
        "\tu.is_deleted,\r\n" + //
        "\tu.status\r\n" + //
        "from\r\n" + //
        "\tpos_uoms u\r\n" + //
        "where\r\n" + //
        "\tu.status = true\r\n" + //
        "\tand u.is_deleted = false\r\n" + //
        "\tand u.name_en ilike %?% \r\n" + //
        "order by\r\n" + //
        "\tu.id desc\r\n" + //
        "")
    Page<Uom> findByNameEn (PageRequest pageable, String valueSearch);

}
