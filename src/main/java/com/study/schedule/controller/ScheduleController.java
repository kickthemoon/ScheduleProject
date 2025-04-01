package com.study.schedule.controller;

import com.study.schedule.dto.scheduleDto.ScheduleRequestDto;
import com.study.schedule.dto.scheduleDto.ScheduleResponseDto;
import com.study.schedule.dto.updateDto.UpdateTitleAndContentsDto;
import com.study.schedule.dto.deleteDto.DeleteCheckPassword;
import com.study.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/registration")
    public ResponseEntity<ScheduleResponseDto> registration(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto scheduleResponseDto =
                scheduleService.registration(
                        requestDto.getTitle(), requestDto.getContents(), requestDto.getUserId()
                );

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateTitleAndContents(
            @PathVariable Long id,
            @RequestBody UpdateTitleAndContentsDto requestDto
    ) {
        scheduleService.updateTitleAndContents(id, requestDto.getNewTitle(),requestDto.getNewContents(),requestDto.getCheckPassword());
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestBody DeleteCheckPassword requestDto) {
        scheduleService.delete(id,requestDto.getCheckPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
