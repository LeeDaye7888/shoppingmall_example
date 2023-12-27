package com.example.member.domain.items.item.entity;


import com.example.member.domain.BaseEntity;
import com.example.member.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.member.domain.option.entity.ItemOption;
import javax.persistence.*;

@Entity
@Table(name = "item")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "item_price", nullable = false)
    private int itemPrice;

    @Column(name = "item_detail", nullable = false)
    private String itemDetail;

    @Column(name = "count", nullable = false)
    private int count;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_sold_out", nullable = false)
    private SoldOutState isSoldOut;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private com.example.member.domain.option.entity.ItemOption optionId;

}