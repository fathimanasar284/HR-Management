package com.hrmanagement.hrmanagement.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hrmanagement.hrmanagement.model.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
    Optional<Attendance> findByEmployee_IdAndWorkDate(Integer employeeId, LocalDate workDate);
    List<Attendance> findByEmployee_Id(Integer employeeId);
}
