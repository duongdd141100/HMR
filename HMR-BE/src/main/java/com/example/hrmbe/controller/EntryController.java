package com.example.hrmbe.controller;

import com.example.hrmbe.common.BaseResponse;
import com.example.hrmbe.common.ErrorMessageEnum;
import com.example.hrmbe.constants.Constants;
import com.example.hrmbe.dto.EntryDto;
import com.example.hrmbe.service.EntryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entries")
@Slf4j
@CrossOrigin
public class EntryController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/header")
    public ResponseEntity<BaseResponse<EntryDto>> getHeader() {
        try {
            return ResponseEntity.ok(BaseResponse.ok(entryService.getHeader()));
        } catch (Exception e) {
            log.error(Constants.SIGN_UP_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(ErrorMessageEnum.typeOf(e.getMessage()).getMessage()));
        }
    }

}
