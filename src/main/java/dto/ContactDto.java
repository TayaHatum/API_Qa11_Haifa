package dto;
//          "address": "string",
//         "description": "string",
//         "email": "string",
//         "id": 0,
//         "lastName": "string",
//         "name": "string",
//         "phone": "string"

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
public class ContactDto {

    String address;
    String description;
    String email;
    int id;
    String lastName;
    String name;
    String phone;



}
