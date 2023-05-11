package com.example.hrmbe.service.impl;

import com.example.hrmbe.custom_repository.EntryRepositoryCustom;
import com.example.hrmbe.dto.EntryDto;
import com.example.hrmbe.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryRepositoryCustom entryRepo;

    @Override
    public List<EntryDto> getHeader() {
        return entryRepo.getHeader();
    }
}
