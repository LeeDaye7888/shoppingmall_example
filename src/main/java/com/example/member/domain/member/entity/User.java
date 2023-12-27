package com.example.member.domain.member.entity;

import com.example.member.domain.BaseEntity;
import com.example.member.domain.member.dto.MemberRequest;

import com.example.member.domain.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "platform_id", nullable = false)
    private String platformId;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "consume_price", nullable = false)
    private int consomePrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_vip", nullable = false)
    private com.example.member.domain.user.entity.VipState isVip;

    @Enumerated(EnumType.STRING)
    @Column(name = "is_deleted", nullable = false)
    private com.example.member.domain.user.entity.DeletedState isDeleted;

    @Enumerated(EnumType.STRING)
    @OneToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

//    public User updateMember(MemberRequest request) {
//        this.username = request.username();
//        this.password = request.password();
//        return this;
//    }

}
