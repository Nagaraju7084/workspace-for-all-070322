package com.backend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.entities.Course;
import com.backend.model.CourseModel;
import com.backend.repositories.CourseRepository;
import com.backend.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public List<CourseModel> getAllCourse() {
		return courseRepository.findAll().stream().map(
				course -> entityToModel(course)
				).collect(Collectors.toList());
	}

	@Override
	public CourseModel getCourse(Integer courseId) {
		Course courseInDb = courseRepository.getReferenceById(courseId);
		return entityToModel(courseInDb);
	}

	@Override
	public CourseModel addCourse(CourseModel courseModel) {
		return entityToModel(courseRepository.save(modelToEntity(courseModel)));
	}

	@Override
	public CourseModel updateCourse(Integer courseId, CourseModel courseModel) {
		List<Course> courseListInDb = courseRepository.findAll();
		AtomicReference<Course> updatedCourseRef = new AtomicReference<>();
		courseListInDb.forEach(ce ->{
			if(ce.getId() == courseId) {
				ce.setTitle(courseModel.getTitle());
				ce.setDescription(courseModel.getDescription());
				updatedCourseRef.set(ce);
			}
		});
		return entityToModel(courseRepository.save(updatedCourseRef.get()));
	}

	@Override
	public CourseModel deleteCourse(Integer courseId) {
		Course deletingCourse = courseRepository.findAll().stream().filter(course -> course.getId() == courseId).findAny().get();
		//courseRepository.findAll().removeIf(course -> course.getId() == deletingCourse.getId());
		courseRepository.delete(deletingCourse);
		return entityToModel(deletingCourse);
	}
	
	private Course modelToEntity(CourseModel courseModel) {
		Course course = new Course();
		BeanUtils.copyProperties(courseModel, course);
		return course;
	}
	
	private CourseModel entityToModel(Course course) {
		CourseModel courseModel = new CourseModel();
		BeanUtils.copyProperties(course, courseModel);
		return courseModel;
	}
}
