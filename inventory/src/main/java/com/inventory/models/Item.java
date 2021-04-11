package com.inventory.models;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String serialNumber;
    private String picture;
    private String information;
    private int age;
    private Integer price;
    @CreationTimestamp
    private Timestamp addedOn;
    private Integer levelId;
    private Integer userId;
}
