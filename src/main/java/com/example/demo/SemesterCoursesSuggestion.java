package com.example.demo;

import com.example.demo.model.Course;
import com.example.demo.model.CourseSchedule;
import com.example.demo.model.Grade;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.example.demo.util.Constants.*;

public class SemesterCoursesSuggestion {


    public static void main(String[] args) {
        SemesterCoursesSuggestion system = new SemesterCoursesSuggestion();
        system.run();
    }

    public void run() {
        List<Grade> grades = grades();
        List<Course> courses = courses();
        List<Course> allowedCourses = findAllowedCourses(courses, grades);
        allowedCourses.sort(Comparator.comparing(Course::getUnits, Comparator.reverseOrder()));
        sortNameUnit(allowedCourses);
        float averageGrade = calculateAverageGrade(grades, courses);
        List<Course> nextTermCourses = suggestedCourses(allowedCourses, averageGrade);
        nextTermCourses.sort(Comparator.comparing(Course::getName));
        printCoursesId(nextTermCourses);
    }

    private List<Grade> grades() {
        return Arrays.asList(Grade.builder().id(1).grade(19.5).build(), Grade.builder().id(2).grade(8.0).build(), Grade.builder().id(3).grade(17.0).build());
    }

    private List<Course> courses() {
        return Arrays.asList(
                Course.builder()
                        .id(1)
                        .name("ICSP")
                        .units(4)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SATURDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.MONDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.THURSDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build()
                                )
                        )
                        .prerequisites(Collections.emptyList())
                        .build(),
                Course.builder()
                        .id(2)
                        .name("Math1")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.TUESDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build()
                                )
                        )
                        .prerequisites(Collections.emptyList())
                        .build(),
                Course.builder()
                        .id(3)
                        .name("Physics1")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.TUESDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build()
                                )
                        )
                        .prerequisites(Collections.emptyList())
                        .build(),
                Course.builder()
                        .id(4)
                        .name("Math2")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.TUESDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build()
                                )
                        )
                        .prerequisites(Collections.singletonList(2))
                        .build(),
                Course.builder()
                        .id(5)
                        .name("DE")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(14, 0)).endTime(LocalTime.of(15, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.TUESDAY).startTime(LocalTime.of(14, 0)).endTime(LocalTime.of(15, 30)).build()
                                )
                        )
                        .prerequisites(Collections.singletonList(3))
                        .build(),
                Course.builder()
                        .id(6)
                        .name("Physics2")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.TUESDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build()
                                )
                        )
                        .prerequisites(Collections.singletonList(3))
                        .build(),
                Course.builder()
                        .id(7)
                        .name("AP")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SATURDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.MONDAY).startTime(LocalTime.of(9, 0)).endTime(LocalTime.of(10, 30)).build()
                                )
                        )
                        .prerequisites(Collections.singletonList(1))
                        .build(),
                Course.builder()
                        .id(8)
                        .name("DM")
                        .units(3)
                        .schedule(
                                Arrays.asList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SATURDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build(),
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.MONDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build()
                                )
                        )
                        .prerequisites(Collections.singletonList(2))
                        .build(),
                Course.builder()
                        .id(9)
                        .name("GENERAL")
                        .units(2)
                        .schedule(
                                Collections.singletonList(
                                        CourseSchedule.builder().dayOfWeek(DayOfWeek.SUNDAY).startTime(LocalTime.of(10, 30)).endTime(LocalTime.of(12, 0)).build()
                                )
                        )
                        .prerequisites(Collections.emptyList())
                        .build()
        );
    }

    List<Course> findAllowedCourses(List<Course> courses, List<Grade> grades) {

        List<Course> allowedCourses = new ArrayList<>();

        for (Course curCourse : courses) {
            boolean checkAllPrerequisites = true;
            if (findCourseGradeById(curCourse.getId(), grades) >= PASS_GRADE)
                continue;

            if (curCourse.getPrerequisites().isEmpty()) {
                allowedCourses.add(curCourse.clone());
                continue;
            }

            for (int prerequisites = 0; prerequisites < curCourse.getPrerequisites().size(); ++prerequisites) {
                checkAllPrerequisites =
                        (checkAllPrerequisites)
                                && (findCourseGradeById(curCourse.getPrerequisites().get(prerequisites), grades) >= PASS_GRADE);

            }
            if (checkAllPrerequisites) {
                allowedCourses.add(curCourse.clone());
            }
        }

        return allowedCourses;
    }

    private Double findCourseGradeById(int id, List<Grade> grades) {

        List<Double> courseGrade = new ArrayList<>();
        for (Grade grade : grades) {
            if (id == grade.getId())
                courseGrade.add(grade.getGrade());
        }

        if (courseGrade.isEmpty())
            return (double) NOT_FOUND;

        courseGrade.sort(Collections.reverseOrder());
        return courseGrade.get(0);
    }

    private Course findCourseById(int id, List<Course> courses) {

        for (Course course : courses) {
            if (course.getId() == id)
                return course;
        }
        return null;
    }

    void sortNameUnit(List<Course> courses) {

        int begin = 0;
        int end = 0;

        for (int index = 0; index < courses.size(); ++index) {

            if (courses.get(begin).getUnits() == courses.get(end).getUnits()) {
                end++;
                if (index != (courses.size() - 1))
                    continue;
            }
            courses.subList(begin, end).sort(Comparator.comparing(Course::getName));
            begin = end;
            end++;
        }
    }

    float calculateAverageGrade(List<Grade> grades, List<Course> courses) {

        float average_grade;
        float sum = 0;
        int weight = 0;
        List<Grade> filtered_grades = filterGrades(grades);

        for (Grade filteredGrade : filtered_grades) {
            sum += ((filteredGrade.getGrade()) * (float) (findCourseById(filteredGrade.getId(), courses)).getUnits());
            weight += findCourseById(filteredGrade.getId(), courses).getUnits();
        }
        average_grade = sum / (float) weight;

        return average_grade;
    }

    List<Grade> filterGrades(List<Grade> grades) {

        Map<Integer, Grade> filteredGrades = grades.stream()
                .collect(Collectors.toMap(Grade::getId, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparing(Grade::getGrade)), TreeMap::new));

        return new ArrayList<>(filteredGrades.values());
    }

    List<Course> suggestedCourses(List<Course> allowedCourses, float averageGrade) {

        List<Course> courses = new ArrayList<>();
        int max_unit;
        if (averageGrade >= HIGH_GRADE)
            max_unit = MAX_UNIT_FOR_UP_17;
        else max_unit = MAX_UNIT_FOR_LESS_17;
        int units = 0;

        for (int index = 0; index < allowedCourses.size(); index++) {

            units += allowedCourses.get(index).getUnits();
            boolean interference = checkInterference(index, allowedCourses);

            if (interference && (units <= max_unit)) {
                courses.add(allowedCourses.get(index).clone());
            } else
                units -= allowedCourses.get(index).getUnits();
        }

        return courses;
    }

    boolean checkInterference(int index, List<Course> courses) {

        for (int iterator = 0; iterator < index; ++iterator) {
            for (int i = 0; i < courses.get(index).getSchedule().size(); ++i) {
                for (int j = 0; j < courses.get(iterator).getSchedule().size(); ++j) {

                    int current_course_start_time = timeConvert(courses.get(index).getSchedule().get(i), true);
                    int current_course_end_time = timeConvert(courses.get(index).getSchedule().get(i), false);
                    int check_course_start_time = timeConvert(courses.get(iterator).getSchedule().get(j), true);
                    int check_course_end_time = timeConvert(courses.get(iterator).getSchedule().get(j), false);

                    if (courses.get(index).getSchedule().get(i).getDayOfWeek() == courses.get(iterator).getSchedule().get(j).getDayOfWeek()) {

                        if (((current_course_start_time < check_course_start_time)
                                && (current_course_end_time > check_course_start_time))
                                || ((current_course_start_time < check_course_end_time)
                                && (current_course_end_time > check_course_end_time))
                                || ((check_course_start_time < current_course_start_time)
                                && (check_course_end_time > current_course_start_time))
                                || ((check_course_start_time < current_course_end_time)
                                && (check_course_end_time > current_course_end_time))
                                || ((current_course_start_time == check_course_start_time)
                                && (current_course_end_time == check_course_end_time)))
                            return false;
                    }
                }
            }
        }

        return true;
    }

    int timeConvert(CourseSchedule classTime, boolean mode) {

        int time_number;

        if (mode) {
            time_number = classTime.getStartMinute();
            time_number += (TIME_CONVERTER) * classTime.getStartHour();
        } else {
            time_number = classTime.getEndMinute();
            time_number += (TIME_CONVERTER) * classTime.getEndHour();
        }

        return time_number;
    }


    void printCoursesId(List<Course> courses) {

        for (Course course : courses) {
            System.out.println(course.getId());
        }
    }

}
