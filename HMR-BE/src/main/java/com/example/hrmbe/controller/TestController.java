package com.example.hrmbe.controller;

import com.example.hrmbe.common.BaseResponse;
import com.example.hrmbe.common.ErrorMessageEnum;
import com.example.hrmbe.config.AppProperty;
import com.example.hrmbe.constants.RequestMappingConstant;
import com.example.hrmbe.dto.EntryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Slf4j
public class TestController {

    @Autowired
    private AppProperty appProperty;

    @GetMapping("/email")
    public ResponseEntity<BaseResponse<String>> getHeader() {
        try {
            return ResponseEntity.ok(BaseResponse.ok(appProperty.getEmail()));
        } catch (Exception e) {
            log.error(RequestMappingConstant.SIGN_UP_API + e);
            return ResponseEntity.badRequest().body(BaseResponse.fail(ErrorMessageEnum.typeOf(e.getMessage()).getMessage()));
        }
    }
}
