package com.study.schedule.schdule.mvc;

import com.study.schedule.config.encoder.PasswordEncoder;
import com.study.schedule.schdule.entity.ScheduleEntity;
import com.study.schedule.schdule.dto.ScheduleResponseDto;
import com.study.schedule.user.entity.UserEntity;
import com.study.schedule.user.mvc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final PasswordEncoder passwordEncoder;

    public ScheduleResponseDto registration(String title, String contents, String password, Long userId) {
        UserEntity findUserEntity = userRepository.findByUserIdOrElseThrow(userId);

        // 일정 생성시 로그인 본인이 맞는지 패스워드로 확인.
        passwordEncoder.matches(password,findUserEntity.getPassword());

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

        passwordEncoder.matches(checkPassword,findUserPassword);

        findScheduleEntity.updateTitleAndContents(newTitle, newContents);
    }

    public void delete(Long id, String checkPassword) {
        ScheduleEntity findScheduleEntity = scheduleRepository.findByIdOrElseThrow(id);
        String findUserPassword = userRepository.findByIdOrElseThrow(id).getPassword();

        passwordEncoder.matches(checkPassword,findUserPassword);

        scheduleRepository.delete(findScheduleEntity);
    }
}
