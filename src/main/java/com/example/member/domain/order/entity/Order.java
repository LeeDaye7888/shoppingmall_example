package com.example.member.domain.order.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "order")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_state", nullable = false)
    private OrderState orderState;

    @Column(name = "delivery_fee", nullable = false)
    private int deliveryFee;

    @Column(name = "receiver_name", nullable = false)
    private String receiverName;

    @Column(name = "receiver_num", nullable = false)
    private int receiverNum;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @Column(name = "request_message")
    private String requestMessage;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @OneToOne
    @JoinColumn(name = "pay_id", nullable = false)
    private Pay payId;
}