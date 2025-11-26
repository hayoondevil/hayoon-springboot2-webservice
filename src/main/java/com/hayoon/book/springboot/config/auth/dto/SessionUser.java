package com.hayoon.book.springboot.config.auth.dto;


import com.hayoon.book.springboot.domain.user.User;
import lombok.Getter;


import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        if (user != null) { // 🚨 NPE 방지!
            this.name = user.getName();
            this.email = user.getEmail();
            this.picture = user.getPicture();
        } else {
            // Null 처리 또는 기본값 설정 (선택 사항)
            this.name = null;
            this.email = null;
            this.picture = null;
        }
    }
}
