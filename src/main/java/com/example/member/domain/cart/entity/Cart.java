package com.example.member.domain.cart.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item itemId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;
}