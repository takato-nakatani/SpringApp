package com.spring.tktapp.domain.repositories;

import com.spring.tktapp.application.entity.MsgData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgDataRepository extends JpaRepository<MsgData, Long> {
}
