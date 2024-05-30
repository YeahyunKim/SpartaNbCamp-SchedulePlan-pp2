package com.sparta.spartanbcampscheduleplanpp2.service;

import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleRequestDto;
import com.sparta.spartanbcampscheduleplanpp2.dto.ScheduleResponseDto;
import com.sparta.spartanbcampscheduleplanpp2.entity.Schedule;
import com.sparta.spartanbcampscheduleplanpp2.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }


    // =====[Create]===== 1단계 일정 작성
    public ScheduleResponseDto createMemo(ScheduleRequestDto requestDto) {
        //  1. schedule 객체를 생성하고, 해당 객체에 requestDto에 담긴 값들을 저장
        Schedule schedule = new Schedule(requestDto);

        //  2. requestDto의 값이 담김 schedule을 scheduleRepository에 save()메서드를 통해 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //  3. 반환할 ResponseDto 객체를 생성하고, 해당 객체에 schedule 데이터를 저장
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }


    // =====[Read]===== 2단계 선택한 일정 조회
    public Schedule findSchedule(Long id) {
        return getScheduleById(id);
    }


    // =====[Read]===== 3단계 전체 일정 조회
    public List<ScheduleResponseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }


    // =====[Update]===== 4단계 선택한 일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, String password) {
        //  1. 해당 메모가 DB에 존재하는지 확인하고, password 확인
        Schedule schedule = checkPasswordWithId(id, password);

        //  2. 문제 없으면 schedule 업데이트 처리
        schedule.update(requestDto);

        //  3. 업데이트 된 schedule을 ResponsDto에 담고 return
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;

    }


    // =====[Delete]===== 5단계 선택한 일정 삭제
    public Long deleteSchedule(Long id, String password) {
        //  1. 해당 메모가 DB에 존재하는지 확인하고, password 확인
        Schedule schedule = checkPasswordWithId(id, password);

        //  2. 문제 없으면 schedule 삭제
        scheduleRepository.delete(schedule);

        //  3. 삭제된 schedule id return
        return id;
    }


    // 아이디 조회 로직
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 스케쥴을 존재하지 않습니다.")
        );
    }

    // 비밀번호 확인 로직
    public Schedule checkPasswordWithId(Long id, String password) {
        Schedule schedule = getScheduleById(id);

        if (schedule.getPassword().equals(password)) {
            return schedule;
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
