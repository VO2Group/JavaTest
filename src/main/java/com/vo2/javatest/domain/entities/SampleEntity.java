package com.vo2.javatest.domain.entities;

import com.vo2.javatest.domain.dto.SampleDto;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VO2 on 16/01/2017.
 * Sample Entity JPA compliant
 */
@Entity
public class SampleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Transient
    public SampleDto toDto() {
        SampleDto dto = new SampleDto();
        dto.setId(this.getId());
        dto.setMessage(this.getMessage());
        return dto;
    }


}
