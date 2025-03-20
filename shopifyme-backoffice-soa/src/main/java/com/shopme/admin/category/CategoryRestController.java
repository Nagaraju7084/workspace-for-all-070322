package com.shopme.admin.category;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopme.admin.AmazonS3Util;
import com.shopme.common.entity.Category;
import com.shopme.common.exception.CategoryNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

	@Autowired
	private CategoryService service;
	
	@PostMapping("/categories/check_unique")
	public String checkUnique(@Param("id") Integer id, @Param("name") String name,
			@Param("alias") String alias) {
		return service.checkUnique(id, name, alias);
	}
	
	@GetMapping("/categories")
	public List<Category> listFirstPage(String sortDir) {
		return listByPage(1, sortDir, null);
	}
	
	@GetMapping("/categories/page/{pageNum}") 
	public List<Category> listByPage(@PathVariable(name = "pageNum") int pageNum, 
			String sortDir,	String keyword) {
		if (sortDir ==  null || sortDir.isEmpty()) {
			sortDir = "asc";
		}
		
		CategoryPageInfo pageInfo = new CategoryPageInfo();
		List<Category> listCategories = service.listByPage(pageInfo, pageNum, sortDir, keyword);
		
		long startCount = (pageNum - 1) * CategoryService.ROOT_CATEGORIES_PER_PAGE + 1;
		long endCount = startCount + CategoryService.ROOT_CATEGORIES_PER_PAGE - 1;
		if (endCount > pageInfo.getTotalElements()) {
			endCount = pageInfo.getTotalElements();
		}
		
		String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
		
		/*
		 * model.addAttribute("totalPages", pageInfo.getTotalPages());
		 * model.addAttribute("totalItems", pageInfo.getTotalElements());
		 * model.addAttribute("currentPage", pageNum); model.addAttribute("sortField",
		 * "name"); model.addAttribute("sortDir", sortDir);
		 * model.addAttribute("keyword", keyword); model.addAttribute("startCount",
		 * startCount); model.addAttribute("endCount", endCount);
		 * 
		 * model.addAttribute("listCategories", listCategories);
		 * model.addAttribute("reverseSortDir", reverseSortDir);
		 * model.addAttribute("moduleURL", "/categories");
		 */
		
		return listCategories;		
	}
	
	@GetMapping("/categories/new")
	public List<Category> newCategory() {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		
		/*
		 * model.addAttribute("category", new Category());
		 * model.addAttribute("listCategories", listCategories);
		 * model.addAttribute("pageTitle", "Create New Category");
		 */
		
		return listCategories;
	}
	
	@PostMapping(name = "/categories", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CategoryBean saveCategory(@RequestBody CategoryBean category) throws IOException {
		Category categoryEntity = new Category();
		if (!category.getFileImage().isEmpty()) {
			String fileName = StringUtils.cleanPath(category.getFileImage().getOriginalFilename());
			BeanUtils.copyProperties(category, categoryEntity);
			categoryEntity.setImage(fileName);

			Category savedCategory = service.save(categoryEntity);
			String uploadDir = "category-images/" + savedCategory.getId();
			
			//AmazonS3Util.removeFolder(uploadDir);
			//AmazonS3Util.uploadFile(uploadDir, fileName, multipartFile.getInputStream());
		} else {
			BeanUtils.copyProperties(category, categoryEntity);
			service.save(categoryEntity);
		}
		
		//ra.addFlashAttribute("message", "The category has been saved successfully.");
		BeanUtils.copyProperties(categoryEntity, category);
		return category;
	}
	
	@PutMapping("/categories/{id}")
	public List<Category> editCategory(@PathVariable(name = "id") Integer id) {
		try {
			Category category = service.get(id);
			List<Category> listCategories = service.listCategoriesUsedInForm();
			
			/*
			 * model.addAttribute("category", category);
			 * model.addAttribute("listCategories", listCategories);
			 * model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");
			 */
			
			return listCategories;			
		} catch (CategoryNotFoundException ex) {
			//ra.addFlashAttribute("message", ex.getMessage());
			//return "redirect:/categories";
		}
		return null;
	}
	
	@GetMapping("/categories/{id}/enabled/{status}")
	public List<Category> updateCategoryEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled) {
		service.updateCategoryEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The category ID " + id + " has been " + status;
		//redirectAttributes.addFlashAttribute("message", message);
		List<Category> listCategories = service.listCategoriesUsedInForm();
		
		return listCategories;
	}
	
	@DeleteMapping("/categories/{id}")
	public List<Category> deleteCategory(@PathVariable(name = "id") Integer id) {
		try {
			service.delete(id);
			String categoryDir = "category-images/" + id;
			AmazonS3Util.removeFolder(categoryDir);
			
			/*
			 * redirectAttributes.addFlashAttribute("message", "The category ID " + id +
			 * " has been deleted successfully");
			 */
			List<Category> listCategories = service.listCategoriesUsedInForm();
			return listCategories;
		} catch (CategoryNotFoundException ex) {
			//redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		
		return null;
	}
	
	@GetMapping("/categories/export/csv")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<Category> listCategories = service.listCategoriesUsedInForm();
		CategoryCsvExporter exporter = new CategoryCsvExporter();
		exporter.export(listCategories, response);
	}
}
