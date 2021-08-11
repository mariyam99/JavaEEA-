package com.example.demo.repo;

import com.example.demo.enitity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepo extends JpaRepository<Employees,Long> {
    boolean existsBynic(String nic);


}
