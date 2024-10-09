package jpa.vn.dao;

import jpa.vn.entity.Video;

import java.util.List;

public interface IVideoDao {
    void insertVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(int vidId);

    List<Video> findAll();

    Video findById(int vidId);

    int count();

    List<Video> findByCateId(int cateId);
}
