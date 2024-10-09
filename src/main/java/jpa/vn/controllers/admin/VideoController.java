package jpa.vn.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jpa.vn.entity.Video;
import jpa.vn.services.IVideoService;
import jpa.vn.services.Impl.VideoService;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VideoController extends HttpServlet implements Serializable {
    public static final long serialVersionUID = 1L;

    IVideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        List<Video> videoList = new ArrayList<>();
        if (url.contains("videos")){
            int cateid = Integer.parseInt(req.getParameter("cateid"));
            videoList = videoService.findByCateId(cateid);
            req.setAttribute("videoList", videoList);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
