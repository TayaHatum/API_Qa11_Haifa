package dto;
//{
//        "code": 0,
//        "details": "string",
//        "message": "string",
//        "timestamp": "2022-04-04T16:15:33.714Z"
//        }

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class ErrorDto {
    int code;
    String details;
    String message;

}
