package com.gialongchuai.notification.controller;

import com.gialongchuai.event.dto.NotificationEvent;
import com.gialongchuai.notification.dto.request.Recipient;
import com.gialongchuai.notification.dto.request.SendEmailRequest;
import com.gialongchuai.notification.dto.response.ApiResponse;
import com.gialongchuai.notification.dto.response.EmailResponse;
import com.gialongchuai.notification.repository.httpclient.EmailClient;
import com.gialongchuai.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationController {
    EmailService emailService;

    @KafkaListener(topics = "onboard-successful")
    public void message(NotificationEvent event) {
        try {
            log.info("Message received: {}", event);

            SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                    .to(Recipient.builder()
                            .email(event.getRecipient())
                            .build())
                    .htmlContent(event.getBody()) // Phai co khong thi quang loi fix 2 3 tieng
                    .subject(event.getSubject()) // Phai co khong thi quang loi fix 2 3 tieng
                    .build();

            emailService.sendEmail(sendEmailRequest);
        } catch (Exception e) {
            log.warn("================ ERORR: " + e);
        }
    }
//    }
}
