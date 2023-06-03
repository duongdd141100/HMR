package com.example.hrmbe.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "tbl_user")
@Data

@SqlResultSetMapping(
        name = "EmployeeDtoResult",
        classes = @ConstructorResult(
                targetClass = com.example.hrmbe.dto.EmployeeDto.class,
                columns = {
                        @ColumnResult(name = "fullName", type = String.class),
                        @ColumnResult(name = "email", type = String.class),
                        @ColumnResult(name = "gender", type = Boolean.class),
                        @ColumnResult(name = "dob", type = Date.class),
                        @ColumnResult(name = "phoneNumber", type = String.class),
                }
        )
)
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
