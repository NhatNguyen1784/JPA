package jpa.vn.dao;

import jpa.vn.entity.Category;

import java.util.List;

public interface ICategoryDao {
    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int cateid);

    Category findById(int id);

    List<Category> findAll();

    List<Category> findByCategoryName(String categoryName);

    List<Category> findAll(int page, int pagesize);

    int count();
}
