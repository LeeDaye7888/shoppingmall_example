package com.example.member.domain.itemImage.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "itemImage")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemImage extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "item_image_id")
    private Long itemImageId;

    @Column(name = "item_url", nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item itemId;

}