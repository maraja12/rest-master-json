package com.fon.rest_master.repository;

import com.fon.rest_master.domain.Engagement;
import com.fon.rest_master.domain.EngagementId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngagementRepository extends JpaRepository<Engagement, EngagementId> {
}
