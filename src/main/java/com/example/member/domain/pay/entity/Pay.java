package com.example.member.domain.pay.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pay")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pay extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_id")
    private Long payId;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "card_num", nullable = false)
    private String cardNum;

    @Column(name = "pay_price", nullable = false)
    private int payPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_state", nullable = false)
    private PayState payState;
}
