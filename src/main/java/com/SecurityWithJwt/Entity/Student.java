package com.SecurityWithJwt.Entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Student {
    private int id;
    private String name;
    private int marks;

}
