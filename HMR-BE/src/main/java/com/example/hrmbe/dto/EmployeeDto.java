package com.example.hrmbe.dto;

import com.example.hrmbe.constants.DateTimeFormatConstant;
import lombok.Data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class EmployeeDto {

    private String fullName;

    private String email;

    private Boolean gender;

    private Date dob;

    private String phoneNumber;

    public String getDobStr() {
        DateFormat formatter = new SimpleDateFormat(DateTimeFormatConstant.DD_MM_YYYY);
        return formatter.format(dob);
    }

    public String getGenderStr() {
        return gender ? "Male" : "Female";
    }
}
