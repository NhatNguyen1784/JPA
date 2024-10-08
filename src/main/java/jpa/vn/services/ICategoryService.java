package jpa.vn.services;

import jpa.vn.entity.Category;

import java.util.List;

public interface ICategoryService {

    Category findById(int id);

    List<Category> findAll();

    List<Category> findByCategoryName(String categoryName);

    List<Category> findAll(int page, int pagesize);

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int cateid);

    int count();
}
