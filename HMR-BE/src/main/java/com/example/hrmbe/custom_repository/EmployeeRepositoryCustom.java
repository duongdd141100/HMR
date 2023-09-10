package com.example.hrmbe.custom_repository;

import com.example.hrmbe.dto.EmployeeDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    public List<EmployeeDto> findAll() {
        String sql = "SELECT" +
                " full_name AS fullName," +
                " email AS email," +
                " gender AS gender," +
                " dob AS dob," +
                " phone_number AS phoneNumber" +
                " FROM tbl_user";
        Query query = entityManager.createNativeQuery(sql, "EmployeeDtoResult");
        return query.getResultList();
    }
}
