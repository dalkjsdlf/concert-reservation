package io.hpp.concertreservation.biz.domain.userinfo.infrastructure;

import io.hpp.concertreservation.biz.domain.userinfo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoCoreRepository extends JpaRepository<UserInfo, Long> {
}
