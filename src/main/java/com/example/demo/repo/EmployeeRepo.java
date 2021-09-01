package com.example.demo.repo;

import com.example.demo.enitity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepo extends JpaRepository<Employees,Long> {

    boolean existsBynic(String nic);

    @Modifying
    @Transactional
    @Query("UPDATE Employees employees SET employees.phoneNumber=:#{#employees.phoneNumber},"+
    "employees.employeeRole=:#{#employees.employeeRole},"+
    "employees.address=:#{#employees.address}" +
    " WHERE employees.employeeId=:#{#employees.employeeId}")
    int updateEmployee(Employees employees);


}
