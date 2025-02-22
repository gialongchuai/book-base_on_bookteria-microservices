package com.gialongchuai.identity.enums;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum SuccessCode {
    SUCCESS_CODE(1000, "Successfully!");

    int code;
    String message;
}
