package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;
    // 마우스 우클릭 후 Generate -> Constructor 클릭하면 아래의 코드가 나옴.
/*
    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }
    // 마우스 우클릭 후 Generate -> toString() 클릭하면 아래의 코드가 나옴.
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
*/

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
