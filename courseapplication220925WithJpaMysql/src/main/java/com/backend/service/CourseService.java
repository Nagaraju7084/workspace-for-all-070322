package com.backend.service;

import java.util.List;

import com.backend.entities.Course;
import com.backend.model.CourseModel;

public interface CourseService {
	List<CourseModel> getAllCourse();
	CourseModel getCourse(Integer courseId);
	CourseModel addCourse(CourseModel courseModel);
	CourseModel updateCourse(Integer courseId, CourseModel courseModel);
	CourseModel deleteCourse(Integer courseId);
}
