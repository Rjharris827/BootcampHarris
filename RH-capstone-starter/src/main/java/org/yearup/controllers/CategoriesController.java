package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController // make this a REST controller
@RequestMapping("/categories") // base endpoint: http://localhost:8080/categories
@CrossOrigin(origins = "*") // allow cross-origin requests from any origin
public class CategoriesController
{
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    // constructor injection
    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    @GetMapping // GET /categories
    public List<Category> getAll()
    {
        return categoryDao.getAllCategories();
    }

    @GetMapping("{id}") // GET /categories/{id}
    public Category getById(@PathVariable int id)
    {
        return categoryDao.getById(id);
    }

    @GetMapping("{categoryId}/products") // GET /categories/{categoryId}/products
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        return productDao.listByCategoryId(categoryId);
    }

    @PostMapping // POST /categories
    @PreAuthorize("hasRole('ADMIN')") // only allow ADMIN
    public Category addCategory(@RequestBody Category category)
    {
        categoryDao.create(category);
        return category;
    }

    @PutMapping("{id}") // PUT /categories/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        categoryDao.update(id, category);
    }

    @DeleteMapping("{id}") // DELETE /categories/{id}
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable int id)
    {
        categoryDao.delete(id);
    }
}