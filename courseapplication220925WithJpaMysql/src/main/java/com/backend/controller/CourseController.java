package com.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.model.CourseModel;
import com.backend.service.CourseService;

@RestController
@RequestMapping("/courseapi")
@CrossOrigin
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	

	@GetMapping("/getName/{name}")
	public String getName(@PathVariable String name) {
		return name;
	}
	
	@PostMapping("/addName/{name}")
	public String addName(@PathVariable String name) {
		return name+" name posting from course controller";
	}
	
	@GetMapping("/getAllCourses")
	public List<CourseModel> getAllCourses(){
		return courseService.getAllCourse();
	}
	
	@GetMapping("/getCourse/{courseId}")
	public CourseModel getCourse(@PathVariable String courseId) {
		return courseService.getCourse(Integer.parseInt(courseId));
	}
	
	@PostMapping("/addCourse")
	public CourseModel addCourse(@RequestBody CourseModel courseModel) {
		return courseService.addCourse(courseModel);
	}
	
	@PutMapping("/updateCourse/{courseId}")
	public CourseModel addCourse(@RequestBody CourseModel courseModel, @PathVariable String courseId) {
		return courseService.updateCourse(Integer.parseInt(courseId), courseModel);
	}
	
	@DeleteMapping("/deleteCourse/{courseId}")
	public CourseModel deleteCourse(@PathVariable String courseId) {
		return courseService.deleteCourse(Integer.parseInt(courseId));
	}
}
