package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {
    // Rest API Rule 2 : DTO에 민감한 정보 빼기 (Filtering), 날짜는 String 처리 (날짜 공부할 때까지)
    @Data
    public static class DTO {
        private Integer id;
        private String username;
        // 보안을 위한 데이터 필터링이 무조건 선 작업되어야 함
        // private String password;
        private String email;
        // 날짜는 String으로 처리할 것임
        private String createdAt;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt().toString();
        }
    }

}
