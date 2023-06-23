package com.example.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class CourseSchedule {
    DayOfWeek dayOfWeek;
    LocalTime startTime;
    LocalTime endTime;

    public int getStartHour() {
        return startTime.getHour();
    }

    public int getStartMinute() {
        return startTime.getMinute();
    }

    public int getEndHour() {
        return endTime.getHour();
    }

    public int getEndMinute() {
        return endTime.getMinute();
    }

}
