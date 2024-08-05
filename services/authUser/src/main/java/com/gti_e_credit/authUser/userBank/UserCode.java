package com.gti_e_credit.authUser.userBank;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserCode {
    @Id
    @GeneratedValue
    private Integer id;
    private  String token;
    private LocalDateTime createdAt;
    private  LocalDateTime expiredAt;
    private LocalDateTime validatedAt;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
