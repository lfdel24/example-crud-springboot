package com.sbdemo.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 *
 * @author leo
 */
@Entity(name = "students")
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Mail is mandatory")
    @Email(message = "Mail no valid")
    private String mail;

    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Address is mandatory")
    private String address;
}
