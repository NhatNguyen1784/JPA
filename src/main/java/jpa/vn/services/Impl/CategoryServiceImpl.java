package jpa.vn.services.Impl;

import jpa.vn.dao.ICategoryDao;
import jpa.vn.dao.Impl.CategoryDao;
import jpa.vn.entity.Category;
import jpa.vn.services.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {

    ICategoryDao cateDao = new CategoryDao();

    @Override
    public Category findById(int id) {
        return cateDao.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return cateDao.findAll();
    }

    @Override
    public List<Category> findByCategoryName(String categoryName) {
        return cateDao.findByCategoryName(categoryName);
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {
        return cateDao.findAll(page, pagesize);
    }

    @Override
    public void insertCategory(Category category) {
        cateDao.insertCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        cateDao.updateCategory(category);
    }

    @Override
    public void deleteCategory(int cateid) {
        cateDao.deleteCategory(cateid);
    }

    @Override
    public int count() {
        return cateDao.count();
    }
}
