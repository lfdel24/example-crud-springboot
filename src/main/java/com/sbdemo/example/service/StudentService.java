package com.sbdemo.example.service;

import com.sbdemo.example.model.Student;
import com.sbdemo.example.repository.StudentRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leo Bussines logic
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public Student save(Student s) {
        return this.repository.save(s);
    }

    public Optional get(Long id) {
        return this.repository.findById(id);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public List<Student> findAll() {
        return this.repository.findAll();
    }

}
