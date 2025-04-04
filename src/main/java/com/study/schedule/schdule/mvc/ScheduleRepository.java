package com.study.schedule.schdule.mvc;

import com.study.schedule.schdule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    default ScheduleEntity findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 일정글이 없습니다."));
    }
}
