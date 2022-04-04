package dto;
//{
//        "token": "string"
//        }

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class ResponseAuthDto {
    String token;
}
