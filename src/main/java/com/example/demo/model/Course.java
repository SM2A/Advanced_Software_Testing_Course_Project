package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Course implements Cloneable {

    private int id;
    private String name;
    private int units;
    private List<CourseSchedule> schedule;
    private List<Integer> prerequisites;

    @Override
    public Course clone() {
        try {
            return (Course) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Course(this.id, this.name, this.units, this.schedule, this.prerequisites);
        }
    }
}
