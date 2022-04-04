package dto;


//        {
//        "email": "string",
//        "password": "string"
//        }

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class AuthDto {
            String email;
            String password;
}
