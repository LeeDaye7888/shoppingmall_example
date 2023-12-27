package com.example.member.domain.review.entity;

import com.example.member.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "review")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "review_title", nullable = false)
    private String reviewTitle;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "star", nullable = false)
    private int star;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userId;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item itemId;
}