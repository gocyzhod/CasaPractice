package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "USERINFO")
public class LoginUserEntity {

    @Id
    @GeneratedValue
    @Column(name = "uid")
    private int uid;

    @Column(name ="id")
    private String id;

    @Column(name ="passwd")
    private String passwd;


}
