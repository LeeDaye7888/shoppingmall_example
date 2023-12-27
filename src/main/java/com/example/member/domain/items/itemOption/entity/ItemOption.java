package com.example.member.domain.items.itemOption.entity;

import com.example.member.domain.BaseEntity;
import com.example.member.domain.items.item.entity.Item;
import com.example.member.domain.member.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import java.util.List;

@Entity
@Table(name = "itemOption")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class ItemOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Convert(converter = JsonConverter.class)
    @Type(type = "jsonb")
    @Column(name = "option_values", columnDefinition = "jsonb")
    private List<com.example.member.domain.option.dto.OptionValuesDTO> optionValues;
}