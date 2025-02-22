package com.gialongchuai.notification.service;

import com.gialongchuai.notification.dto.request.EmailRequest;
import com.gialongchuai.notification.dto.request.Recipient;
import com.gialongchuai.notification.dto.request.SendEmailRequest;
import com.gialongchuai.notification.dto.request.Sender;
import com.gialongchuai.notification.dto.response.EmailResponse;
import com.gialongchuai.notification.exception.AppException;
import com.gialongchuai.notification.exception.ErrorCode;
import com.gialongchuai.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailService {
    EmailClient emailClient;

    String api_key = "KEY_API_SEND_EMAIL";

    public EmailResponse sendEmail(SendEmailRequest sendEmailRequest) {
        log.info("Service is here!");
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("gialongchuai demo with u")
                        .email("minhtan0949@gmail.com")
                        .build())
                .to(List.of(sendEmailRequest.getTo()))
                .subject(sendEmailRequest.getSubject())
                .htmlContent(sendEmailRequest.getSubject())
                .build();

        try {
            return emailClient.sendEMail(api_key, emailRequest);
        } catch (FeignException.FeignClientException ex) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
