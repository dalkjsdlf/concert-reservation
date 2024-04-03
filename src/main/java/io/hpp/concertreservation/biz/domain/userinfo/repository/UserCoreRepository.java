package io.hpp.concertreservation.biz.domain.userinfo.repository;

import io.hpp.concertreservation.biz.domain.userinfo.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCoreRepository extends JpaRepository<UserInfo, Long> {
}
