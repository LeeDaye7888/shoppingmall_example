package com.example.member.domain.orderItem.entity;

import com.example.member.domain.BaseEntity;
import com.example.member.domain.items.item.entity.Item;
import com.example.member.domain.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_item_name", nullable = false)
    private String orderItemName;

    @Column(name = "order_item_price", nullable = false)
    private int orderItemPrice;

    @Column(name = "order_item_count", nullable = false)
    private int orderItemCount;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order orderId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item itemId;
}