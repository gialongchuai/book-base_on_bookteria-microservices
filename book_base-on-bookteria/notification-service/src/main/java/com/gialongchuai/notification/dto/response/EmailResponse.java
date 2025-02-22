package com.gialongchuai.notification.dto.response;

import com.gialongchuai.notification.dto.request.Recipient;
import com.gialongchuai.notification.dto.request.Sender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailResponse {
    String messageId;
}
