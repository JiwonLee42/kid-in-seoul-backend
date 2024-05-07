package app.kidsInSeoul.member.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordchangeRequest {

    private String password;
}
