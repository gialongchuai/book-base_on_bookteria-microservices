package com.gialongchuai.notification.controller;

import com.gialongchuai.notification.dto.request.EmailRequest;
import com.gialongchuai.notification.dto.request.SendEmailRequest;
import com.gialongchuai.notification.dto.response.ApiResponse;
import com.gialongchuai.notification.dto.response.EmailResponse;
import com.gialongchuai.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailController {
    EmailService emailService;

    @PostMapping("/email/send")
    ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) {
        log.info("Controller is here!");
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(sendEmailRequest))
                .build();
    }
}
