package vn.edu.hcmute._9.graphql_api.dto;

import lombok.Data;

@Data // Annotation của Lombok để tự tạo getter, setter, constructor...
public class UserInput {
    private String fullname;
    private String email;
    private String password;
    private String phone;
}