package jpa.vn.services.Impl;

import jpa.vn.dao.IVideoDao;
import jpa.vn.dao.Impl.VideoDao;
import jpa.vn.entity.Video;
import jpa.vn.services.IVideoService;

import java.util.List;

public class VideoService implements IVideoService {

    IVideoDao videoDao = new VideoDao();

    @Override
    public void insertVideo(Video video) {
        videoDao.insertVideo(video);
    }

    @Override
    public void updateVideo(Video video) {
        videoDao.updateVideo(video);
    }

    @Override
    public void deleteVideo(String vidId) {
        videoDao.deleteVideo(vidId);
    }

    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public Video findById(String vidId) {
        return videoDao.findById(vidId);
    }

    @Override
    public int count() {
        return videoDao.count();
    }

    @Override
    public List<Video> findByCateId(int cateId) {
        return videoDao.findByCateId(cateId);
    }
}
