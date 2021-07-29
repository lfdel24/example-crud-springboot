package com.sbdemo.example.repository;

import com.sbdemo.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author leo
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
