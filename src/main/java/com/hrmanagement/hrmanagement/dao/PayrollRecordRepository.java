package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.util.Optional;
import java.util.List;

public interface PayrollRecordRepository extends JpaRepository<PayrollRecord,Integer> {
    Optional<PayrollRecord> findByEmployee_IdAndYearAndMonth(Integer employeeId, int year, int month);
    List<PayrollRecord> findByEmployee_Id(Integer employeeId);
}
