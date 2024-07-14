package com.hm.alpermulayim.dressrecommenderapi.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hm_customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HmCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private  String name;

    @Column(name = "last_name")
    private String surname;

    @Column(name = "code")
    private String code;
}
