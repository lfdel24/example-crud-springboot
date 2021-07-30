package com.sbdemo.example.controller;

import com.sbdemo.example.model.Student;
import com.sbdemo.example.service.StudentService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leo Method PATCH update massive resources
 */
@RestController
@CrossOrigin(value = "http://localhost:8080")
@RequestMapping(path = StudentController.BASIC)
public class StudentController {

    public static final String BASIC = "/students";
    public static final String ID = "/{id}";

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Student student) throws Exception {
        this.service.save(student);
        return new ResponseEntity<>("===>>> Created: " + student, HttpStatus.CREATED);
    }

    @GetMapping(path = ID)
    public ResponseEntity<?> read(@PathVariable(value = "id") Long id) {
        Optional opt = this.service.get(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("===>>> Student : " + id + " no exists", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("===>>> Read: " + opt.get(), HttpStatus.OK);
    }

    @PutMapping(path = ID)
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Student student) throws Exception {
        student.setId(id);
        this.service.save(student);
        return new ResponseEntity<>("===>>> Updated: " + student, HttpStatus.CREATED);
    }

    @DeleteMapping(path = ID)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {
        this.service.delete(id);
        return new ResponseEntity<>("===>>> Delete: " + id, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> search() throws Exception {
        List list = this.service.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>("===>>> Students empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("===>>> Students : " + list, HttpStatus.OK);
    }

}
