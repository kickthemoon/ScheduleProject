package com.study.schedule.service;

import com.study.schedule.dto.scheduleDto.ScheduleResponseDto;
import com.study.schedule.entity.Schedule;
import com.study.schedule.entity.User;
import com.study.schedule.repository.ScheduleRepository;
import com.study.schedule.repository.UserRepository;
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
        User findUser = userRepository.findUserByUserIdOrElseThrow(userId);

        Schedule schedule = new Schedule(title, contents);
        schedule.setUseriD(findUser);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedSchedule.getId(),savedSchedule.getTitle(),savedSchedule.getContents(),savedSchedule.getUserId());
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findSchedule.getId(), findSchedule.getTitle(), findSchedule.getContents(),findSchedule.getUserId());
    }

    @Transactional
    public void updateTitleAndContents(Long id, String newTitle, String newContents, String checkPassword) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        String findUserPassword = userRepository.findByIdOrElseThrow(id).getPassword();

        if(!findUserPassword.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
        }

        findSchedule.updateTitleAndContents(newTitle, newContents);
    }

    public void delete(Long id, String checkPassword) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);
        String findUserPassword = userRepository.findByIdOrElseThrow(id).getPassword();

        if(!findUserPassword.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다");
        }

        scheduleRepository.delete(findSchedule);
    }
}
