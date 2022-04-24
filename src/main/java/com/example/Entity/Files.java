package com.example.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "W_FILES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class Files {
    @Id
    @Column(nullable = false,length = 32)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "ID")
    private Long fileId;
    @Column(nullable = false,length = 50,unique=true)
    private String fileName;
    @Column(nullable = false)
    private String fileUrl;
    @Column(nullable = false)
    private Date ct;
    @Column(nullable = false,length = 20,columnDefinition = "N")
    private String status;
    @Column(nullable = false,length = 50)
    private String userId;
}
