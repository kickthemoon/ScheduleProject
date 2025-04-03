package com.study.schedule.schdule.mvc;

import com.study.schedule.schdule.entity.ScheduleEntity;
import com.study.schedule.schdule.dto.ScheduleResponseDto;
import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.mvc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto registration(String title, String contents, Long userId) {
        UserEntity findUserEntity = userRepository.findByUserIdOrElseThrow(userId);

        ScheduleEntity scheduleEntity = new ScheduleEntity(title, contents);
        scheduleEntity.setUseriD(findUserEntity);

        ScheduleEntity savedScheduleEntity = scheduleRepository.save(scheduleEntity);

        return new ScheduleResponseDto(savedScheduleEntity.getId(), savedScheduleEntity.getTitle(), savedScheduleEntity.getContents(), savedScheduleEntity.getUserEntityId());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    public ScheduleResponseDto findById(Long id) {
        ScheduleEntity findScheduleEntity = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findScheduleEntity.getId(), findScheduleEntity.getTitle(), findScheduleEntity.getContents(), findScheduleEntity.getUserEntityId());
    }

    @Transactional
    public void updateTitleAndContents(Long id, String newTitle, String newContents, String checkPassword) {
        ScheduleEntity findScheduleEntity = scheduleRepository.findByIdOrElseThrow(id);
        String findUserPassword = userRepository.findByIdOrElseThrow(id).getPassword();

        if(!findUserPassword.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
        }

        findScheduleEntity.updateTitleAndContents(newTitle, newContents);
    }

    public void delete(Long id, String checkPassword) {
        ScheduleEntity findScheduleEntity = scheduleRepository.findByIdOrElseThrow(id);
        String findUserPassword = userRepository.findByIdOrElseThrow(id).getPassword();

        if(!findUserPassword.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
        }

        scheduleRepository.delete(findScheduleEntity);
    }
}
