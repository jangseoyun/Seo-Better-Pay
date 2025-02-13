package com.yun.membership.adapter.out.persistence;

import com.yun.membership.domain.MembershipRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "t_membership",
        indexes = {
                @Index(columnList = "membershipId"),
                @Index(columnList = "membershipEmail")
        })
public class MembershipEntity {
    @Id
    @Column(name = "membership_id", length = 12)
    private String membershipId;
    @Column(name = "membership_pw", length = 10, nullable = false)
    private String membershipPw;
    @Column(name = "membership_email", length = 100, nullable = false)
    private String membershipEmail;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "is_valid")
    private boolean isValid;
    @Column(name = "is_wallet_available")
    private boolean isMoneyWalletAvailable;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private MembershipRole role;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
    @CreatedDate
    @Column(name = "deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    @Builder
    public MembershipEntity(String membershipId,
                            String membershipPw,
                            String membershipEmail,
                            String name,
                            String address,
                            String refreshToken,
                            boolean isValid,
                            boolean isMoneyWalletAvailable,
                            MembershipRole role,
                            LocalDateTime createdAt,
                            LocalDateTime modifiedAt,
                            LocalDateTime deletedAt) {
        this.membershipId = membershipId;
        this.membershipPw = membershipPw;
        this.membershipEmail = membershipEmail;
        this.name = name;
        this.address = address;
        this.refreshToken = refreshToken;
        this.isValid = isValid;
        this.isMoneyWalletAvailable = isMoneyWalletAvailable;
        this.role = role;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.deletedAt = deletedAt;
    }

    public void updateMembershipInfo(String membershipId, String address) {
        if (membershipId != null || !membershipId.isBlank()) {
            this.membershipId = membershipId;
        }
        if (address != null || !address.isBlank()) {
            this.address = address;
        }
    }

    public void insertRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MembershipEntity that)) return false;
        return Objects.equals(membershipId, that.membershipId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membershipId);
    }
}
