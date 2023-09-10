package com.example.hrmbe.controller;

import com.example.hrmbe.common.BaseResponse;
import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.constants.RequestMappingConstant;
import com.example.hrmbe.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<BaseResponse> getAllEmployees() {
        try {
            return ResponseEntity.ok(BaseResponse.ok(employeeService.findAll()));
        } catch (Exception e) {
            log.error(RequestMappingConstant.FIND_ALL_EMPLOYEE_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(e.getMessage()));
        }
    }

}
