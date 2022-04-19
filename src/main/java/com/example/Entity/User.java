package com.example.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "W_USER")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {
    @Id
    @Column(nullable = false,length = 32)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private String userId;
    @Column(nullable = false,length = 8)
    private String userName;
    @Column(length = 20)
    private String userData;
    @Column(length = 20)
    private String ct;
    @Column(nullable = false,length = 20)
    private String passWord;
    private String token;
}
