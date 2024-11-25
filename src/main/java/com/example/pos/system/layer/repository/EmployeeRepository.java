package com.example.pos.system.layer.repository;

// @Repository
// public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//     boolean existsByContact(String contact);

//     Optional<Employee> findByIdAndStatusTrueAndIsDeletedFalse(int id);

//     @Query(nativeQuery = true, value = "select\r\n" + //
//                         "\t*\r\n" + //
//                         "from\r\n" + //
//                         "\tpos_employee p\r\n" + //
//                         "where\r\n" + //
//                         "\tp.contact = ?\r\n" + //
//                         "\tand p.status = true\r\n" + //
//                         "\tand p.is_deleted = false\r\n" + //
//                         "order by\r\n" + //
//                         "\tp.id\r\n" + //
//                         "limit 1")
//     Optional<Employee> checkPhoneNumber(String contact);

//     @Query(nativeQuery = true, value = "select\r\n" + //
//                         "\t*\r\n" + //
//                         "from\r\n" + //
//                         "\tpos_employee pe\r\n" + //
//                         "where\r\n" + //
//                         "\tstatus = true\r\n" + //
//                         "\tand is_deleted = false\r\n" + //
//                         "order by\r\n" + //
//                         "\tid desc")
//     List<Employee> getEmployee();

//     @Query(nativeQuery = true, value = "select\r\n" + //
//                         "\t*\r\n" + //
//                         "from\r\n" + //
//                         "\tpos_employee\r\n" + //
//                         "where\r\n" + //
//                         "\tstatus = true\r\n" + //
//                         "\tand is_deleted = false\r\n" + //
//                         "\tand id = ?")
//     Employee getEmployeeById(int id);

//     // @Query(nativeQuery = true , value = "select ")
//     // String getEmpName(int id);

//     @Query(nativeQuery = true, value = "select\r\n" + //
//                         "\tu.user_code ,\r\n" + //
//                         "\tu.full_name,\r\n" + //
//                         "\tu.id,\r\n" + //
//                         "\tu.emp_id\r\n" + //
//                         "from\r\n" + //
//                         "\tpos_user u\r\n" + //
//                         "where\r\n" + //
//                         "\tu.status = true\r\n" + //
//                         "\tand u.is_deleted = false\r\n" + //
//                         "order by\r\n" + //
//                         "\tu.id desc")
//     List<AccountUserProjection> getAccountUserProjections();

//     @Query(nativeQuery = true, value = "select\r\n" + //
//             "\tu.user_code ,\r\n" + //
//             "\tu.full_name,\r\n" + //
//             "\tu.id,\r\n" + //
//             "\tu.emp_id\r\n" + //
//             "from\r\n" + //
//             "\tpos_user u\r\n" + //
//             "where\r\n" + //
//             "\tu.status = true\r\n" + //
//             "\tand u.is_deleted = false\r\n" + //
//             "\tand u.user_code ilike %?% \r\n" + //
//             "order by\r\n" + //
//             "\tu.id desc\r\n" + //
//             "")
//     List<AccountUserProjection> getAccountUserProjectionsByUserCode(String userCode);

//     @Query(nativeQuery = true, value = "select\r\n" + //
//             "\tu.user_code ,\r\n" + //
//             "\tu.full_name,\r\n" + //
//             "\tu.id,\r\n" + //
//             "\tu.emp_id\r\n" + //
//             "from\r\n" + //
//             "\tpos_user u\r\n" + //
//             "where\r\n" + //
//             "\tu.status = true\r\n" + //
//             "\tand u.is_deleted = false\r\n" + //
//             "\tand u.full_name ilike %?% \r\n" + //
//             "order by\r\n" + //
//             "\tu.id desc\r\n" + //
//             "")
//     List<AccountUserProjection> getAccountUserProjectionsByUserName(String userName);

//     @Query(nativeQuery = true, value = "select\r\n" + //
//                         "\t*\r\n" + //
//                         "from\r\n" + //
//                         "\tpos_employee p\r\n" + //
//                         "where\r\n" + //
//                         "\tp.name_en ilike %?%\r\n" + //
//                         "\tand p.status = true\r\n" + //
//                         "\tand p.is_deleted = false")
//     List<Employee> findByNameEn(String nameEn);

// }
