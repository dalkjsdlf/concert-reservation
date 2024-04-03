package io.hpp.concertreservation.biz.domain.userinfo.model;

import io.hpp.concertreservation.common.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Getter
@Table
@Entity
public class UserInfo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(length = 50, nullable = true)
    private String userName;

    protected UserInfo(){};

    private UserInfo(String userName) {
        this.userName = userName;
    }

    public static UserInfo of(String userName){
        return new UserInfo(userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
