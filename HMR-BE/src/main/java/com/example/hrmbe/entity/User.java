package com.example.hrmbe.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tbl_user")
@Data
public class User extends BaseEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "roles")
    private String roles;

}
