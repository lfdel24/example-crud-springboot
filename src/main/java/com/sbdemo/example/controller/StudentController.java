package com.sbdemo.example.controller;

import com.sbdemo.example.model.Student;
import com.sbdemo.example.service.StudentService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leo
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
    public ResponseEntity<?> create(@Valid @RequestBody Student student) {
        this.service.save(student);
        return new ResponseEntity<>("===>>> Created: " + student, HttpStatus.CREATED);
    }

    @GetMapping(path = ID)
    public ResponseEntity<?> read(@PathVariable Long id) {
        Optional opt = this.service.get(id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>("===>>> Student : " + id + " no exists", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("===>>> Read: " + opt.get().toString(), HttpStatus.OK);
    }

    //TODO: Ojo: no se si hay que buscar el objeto y setear los valores
    @PostMapping
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Student student) {
        student.setId(id);
        this.service.save(student);
        return new ResponseEntity<>("===>>> Created: " + student, HttpStatus.CREATED);
    }

    @PutMapping(path = ID)
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        this.service.delete(id);
        return new ResponseEntity<>("===>>> Delete: " + id, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> search() {
        List list = this.service.findAll();
        if (list.isEmpty()) {
            return new ResponseEntity<>("===>>> Students empty", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("===>>> Students : " + list.toString(), HttpStatus.OK);
    }

    //TODO: Mover a su propia clase ensucia el controlador
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
