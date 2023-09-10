package com.example.hrmbe.service.impl;

import com.example.hrmbe.custom_repository.EmployeeRepositoryCustom;
import com.example.hrmbe.dto.EmployeeDto;
import com.example.hrmbe.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepositoryCustom employeeRepo;

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepo.findAll();
    }

}
