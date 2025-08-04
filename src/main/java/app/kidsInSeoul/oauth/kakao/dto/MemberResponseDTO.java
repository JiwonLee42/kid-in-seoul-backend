package app.kidsInSeoul.oauth.kakao.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private String email;
    private String name;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class JoinResultDTO {
        private String email;
        private String name;
        private String token;
    }
}
