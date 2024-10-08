package jpa.vn.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jpa.vn.config.JPAConfig;
import jpa.vn.entity.Category;

import java.util.List;

public class CategoryDao implements jpa.vn.dao.ICategoryDao {
    @Override
    public void insertCategory(Category category) {

        EntityManager enma = JPAConfig.getEntityManager();

        EntityTransaction trans = enma.getTransaction();

        try {

            trans.begin();
            enma.persist(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public void updateCategory(Category category) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            enma.merge(category);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {

                // nếu có lỗi thì rollback
                trans.rollback();
            }
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public void deleteCategory(int cateid) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            trans.begin();
            Category category = enma.find(Category.class, cateid);
            trans.commit();
            if(category != null) {
                enma.merge(category);
                enma.remove(category);
            }
            else {
                throw new RuntimeException("Category not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            throw e;
        } finally {
            // Đóng EntityManager
            if (enma.isOpen()) {
                enma.close();
            }
        }
    }

    @Override
    public Category findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        Category category = enma.find(Category.class,id);
        return category;
    }

    @Override
    public List<Category> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
        return query.getResultList();
    }

    @Override
    public List<Category> findByCategoryName(String categoryName) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT c FROM Category c WHERE c.categoryname = :categoryName";
        TypedQuery<Category> query = enma.createQuery(jpql, Category.class);
        query.setParameter("categoryName", "%" + categoryName + "%");
        return query.getResultList();
    }

    @Override
    public List<Category> findAll(int page, int pagesize) {

        // Phân trang
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Category> query= enma.createNamedQuery("Category.findAll", Category.class);
        query.setFirstResult(page*pagesize);
        query.setMaxResults(pagesize);
        return query.getResultList();
    }

    @Override
    public int count() {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT count(c) FROM Category c";
        Query query = enma.createQuery(jpql);
        return ((Long)query.getSingleResult()).intValue();
    }
}
