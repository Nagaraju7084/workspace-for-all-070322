package com.shopme.admin.brand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopme.admin.AmazonS3Util;
import com.shopme.admin.category.CategoryService;
import com.shopme.admin.paging.PagingAndSortingHelper;
import com.shopme.admin.paging.PagingAndSortingParam;
import com.shopme.common.entity.Brand;
import com.shopme.common.entity.Category;

@RestController
public class BrandRestController {
	
	private String defaultRedirectURL = "redirect:/brands/page/1?sortField=name&sortDir=asc";
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private BrandService service;
	
	@PostMapping("/brands/check_unique")
	public String checkUnique(Integer id, String name) {
		return service.checkUnique(id, name);
	}
	
	@GetMapping("/brands/{id}/categories")
	public List<CategoryDTO> listCategoriesByBrand(@PathVariable(name = "id") Integer brandId) throws BrandNotFoundRestException {
		List<CategoryDTO> listCategories = new ArrayList<>(); 
		
		try {
			Brand brand = service.get(brandId);
			Set<Category> categories = brand.getCategories();
			
			for (Category category : categories) {
				CategoryDTO dto = new CategoryDTO(category.getId(), category.getName());
				listCategories.add(dto);
			}
			
			return listCategories;
		} catch (BrandNotFoundException e) {
			throw new BrandNotFoundRestException();
		}
	}
	
	@GetMapping("/brands")
	public String listFirstPage() {
		return defaultRedirectURL;
	}
	
	@GetMapping("/brands/page/{pageNum}")
	public String listByPage(
			@PagingAndSortingParam(listName = "listBrands", moduleURL = "/brands") PagingAndSortingHelper helper,
			@PathVariable(name = "pageNum") int pageNum
			) {
		service.listByPage(pageNum, helper);
		//return "brands/brands";
		return null;
	}
	
	@GetMapping("/brands/new")
	public List<Category> newBrand() {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();
		
		return listCategories;		
	}
	
	@PostMapping("/brands/save")
	public Brand saveBrand(@RequestPart String brandData, @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
		Brand brand = null;
		if (!multipartFile.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			brand = mapper.convertValue(brandData, Brand.class);
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			brand.setLogo(fileName);
			
			Brand savedBrand = service.save(brand);
			String uploadDir = "brand-logos/" + savedBrand.getId();
			
			//AmazonS3Util.removeFolder(uploadDir);
			//AmazonS3Util.uploadFile(uploadDir, fileName, multipartFile.getInputStream());
		} else {
			service.save(brand);
		}
		
		//ra.addFlashAttribute("message", "The brand has been saved successfully.");
		return brand;		
	}
	
	@GetMapping("/brands/edit/{id}")
	public String editBrand(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes ra) {
		try {
			Brand brand = service.get(id);
			List<Category> listCategories = categoryService.listCategoriesUsedInForm();
			
			model.addAttribute("brand", brand);
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("pageTitle", "Edit Brand (ID: " + id + ")");
			
			return "brands/brand_form";			
		} catch (BrandNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return defaultRedirectURL;
		}
	}
	
	@GetMapping("/brands/delete/{id}")
	public String deleteBrand(@PathVariable(name = "id") Integer id, 
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			service.delete(id);
			String brandDir = "brand-logos/" + id;
			AmazonS3Util.removeFolder(brandDir);
			
			redirectAttributes.addFlashAttribute("message", 
					"The brand ID " + id + " has been deleted successfully");
		} catch (BrandNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}
}
