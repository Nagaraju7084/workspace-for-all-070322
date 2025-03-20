package com.quize.qnans;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.quize.qnans.entities.exam.Category;
import com.quize.qnans.repositories.CategoryRepository;

@SpringBootTest
class QuestionAndAnserTaskApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private CategoryRepository catRepo;
	
	@Test
	public void addCategory() {
		Category category = new Category();
		category.setCid(8l);
		category.setTitle("c language");
		category.setDescription("procedure oriented");
		catRepo.save(category);
		assertNotNull(category.getCid());
	}

}
