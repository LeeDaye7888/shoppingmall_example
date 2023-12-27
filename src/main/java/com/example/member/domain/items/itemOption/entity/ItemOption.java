package com.example.member.domain.option.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

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
    private Item itemId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @Convert(converter = JsonConverter.class)
    @Type(type = "jsonb")
    @Column(name = "option_values", columnDefinition = "jsonb")
    private List<OptionValuesDTO> optionValues;
}