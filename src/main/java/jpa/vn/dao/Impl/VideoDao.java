package jpa.vn.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jpa.vn.config.JPAConfig;
import jpa.vn.entity.Video;

import java.util.List;

public class VideoDao {

    public void insertVideo(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            enma.persist(video);
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(trans.isActive()){
                trans.rollback();
            }
        }
        finally{
            enma.close();
        }
    }

    public void updateVideo(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            enma.merge(video);
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(trans.isActive()){
                trans.rollback();
            }
        }
        finally{
            enma.close();
        }
    }

    public void deleteVideo(int vidId) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try{
            trans.begin();
            Video vid = enma.find(Video.class, vidId);
            if(vid != null){
                enma.remove(vid);
            }
            else {
                throw new RuntimeException("Video not found");
            }
            trans.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            if(trans.isActive()){
                trans.rollback();
            }
        }
        finally{
            enma.close();
        }

    }

    public List<Video> findAll(){
        EntityManager enma = JPAConfig.getEntityManager();

        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

        return query.getResultList();

    }

    public Video findById(int vidId) {
        EntityManager enma = JPAConfig.getEntityManager();

        Video vid = enma.find(Video.class, vidId);

        return vid;
    }

    public int count(){
        EntityManager enma = JPAConfig.getEntityManager();

        String que = "SELECT COUNT(v) FROM Video v";
        Query query = enma.createQuery(que);
        return ((Long)query.getSingleResult()).intValue();
    }

    public List<Video> findByCateId(int cateId) {

        EntityManager enma = JPAConfig.getEntityManager();

        String que = "SELECT v FROM Video v WHERE v.category.categoryid = :cateId";

        Query query = enma.createQuery(que);

        query.setParameter("cateId", cateId);

        return query.getResultList();

    }
}
