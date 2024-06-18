package com.example.pos.connection1.repository.shiftRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.pos.connection1.entity.CloseShift;

@Repository
public interface CloseShiftRepository extends JpaRepository<CloseShift, Integer> {
    // @Query(nativeQuery = true , value = "select * from pos_close_shift pcs where
    // status = true and is_deleted = false and user_id = ? and close_date =?")
    @Query(nativeQuery = true, value = "select\r\n" + //
            "\t*\r\n" + //
            "from\r\n" + //
            "\tpos_close_shift pos\r\n" + //
            "where\r\n" + //
            "\tuser_code = ?\r\n" + //
            "\tand close_date = ?\r\n" + //
            "\tand active = 'closed'\r\n" + //
            "\tand pos_id =?\r\n" + //
            "order by\r\n" + //
            "\tid desc\r\n" + //
            "limit 1")
    CloseShift getCloseShift(String userId, String date, String posId);

    @Query(nativeQuery = true, value = "select\r\n" + //
            "\tcount(*)\r\n" + //
            "from\r\n" + //
            "\tpos_close_shift\r\n" + //
            "where\r\n" + //
            "\tuser_id = ?\r\n" + //
            "\tand close_date =?")
    int countCloseShift(int userId, String date);

}
