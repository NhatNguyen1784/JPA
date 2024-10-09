package jpa.vn.services;

import jpa.vn.entity.Video;

import java.util.List;

public interface IVideoService {
    void insertVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(String vidId);

    List<Video> findAll();

    Video findById(String vidId);

    int count();

    List<Video> findByCateId(int cateId);
}
