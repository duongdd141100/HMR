package com.example.hrmbe.custom_repository;

import com.example.hrmbe.dto.EntryDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EntryRepositoryCustom {
    @Autowired
    private EntityManager entityManager;

    public List<EntryDto> getHeader() {
        String sql = "SELECT slug, label, role_id AS roleId FROM tbl_entry";
        Query query = entityManager.createNativeQuery(sql, "EntryDtoResult");
        return query.getResultList();
    }
}
