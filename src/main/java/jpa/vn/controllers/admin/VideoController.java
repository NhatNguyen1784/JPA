package jpa.vn.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jpa.vn.entity.Category;
import jpa.vn.entity.Video;
import jpa.vn.services.ICategoryService;
import jpa.vn.services.IVideoService;
import jpa.vn.services.Impl.CategoryServiceImpl;
import jpa.vn.services.Impl.VideoService;
import jpa.vn.utils.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/videos", "/admin/video/add", "/admin/video/insert",
"/admin/video/edit", "/admin/video/update", "/admin/video/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class VideoController extends HttpServlet implements Serializable {
    public static final long serialVersionUID = 1L;

    IVideoService videoService = new VideoService();
    ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        List<Video> videoList = new ArrayList<>();
        if (url.contains("videos")){
            videoList = videoService.findAll();
            req.setAttribute("videoList", videoList);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
        else if (url.contains("add")){

            List<Category> categoryList = categoryService.findAll();
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        }
        else if (url.contains("edit")){
            String videoId = req.getParameter("videoId");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            Video video = videoService.findById(videoId);
            List<Category> categoryList = categoryService.findAll();

            req.setAttribute("vid", video);
            req.setAttribute("cateSelected", categoryId);
            req.setAttribute("categoryList", categoryList);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        }
        else if (url.contains("delete")){
            String videoId = req.getParameter("videoId");
            videoService.deleteVideo(videoId);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();

        if (url.contains("insert")){

            // lay du lieu tu form
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String poster = req.getParameter("poster");
            int views = Integer.parseInt(req.getParameter("view"));
            int active = Integer.parseInt(req.getParameter("active"));

            // Lấy giá trị của 'categoryid'
            int cateid = Integer.parseInt(req.getParameter("categories"));


            Category category = categoryService.findById(cateid);

            Video video = new Video();

            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setViews(views);
            video.setActive(active);
            video.setCategory(category);

            // set poster
            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File file = new File(uploadPath);
            if(!file.exists()){
                file.mkdirs();
            }
            try{
                Part part = req.getPart("poster");
                if(part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    System.out.println(fileName);
                    // đổi tên file nếu up 1 file trước đó xong up 1 file khác lên
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;

                    // upload file
                    part.write(uploadPath + "/" + fname);

                    // ghi tên file vào data
                    video.setPoster(fname);
                } else if (poster != null) {
                    video.setPoster(poster);
                }
                else {

                    // neu khong chon file nao thi tai len file mac đinh
                    video.setPoster("avatar.png");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // goi insert
            videoService.insertVideo(video);

            // tra ve view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (url.contains("update")) {

            // lay du lieu tu form
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            String poster = req.getParameter("posterold");
            int views = Integer.parseInt(req.getParameter("view"));
            int active = Integer.parseInt(req.getParameter("active"));

            // Lấy giá trị của 'categoryid'
            int cateid = Integer.parseInt(req.getParameter("categories"));


            Category category = categoryService.findById(cateid);

            Video video = new Video();

            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setViews(views);
            video.setActive(active);
            video.setCategory(category);

            // set poster
            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File file = new File(uploadPath);
            if(!file.exists()){
                file.mkdirs();
            }
            try{
                Part part = req.getPart("posternew");
                if(part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                    System.out.println(fileName);
                    // đổi tên file nếu up 1 file trước đó xong up 1 file khác lên
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;

                    // upload file
                    part.write(uploadPath + "/" + fname);

                    // ghi tên file vào data
                    video.setPoster(fname);
                } else if (poster != null) {
                    video.setPoster(poster);
                }
                else {

                    // neu khong chon file nao thi tai len file mac đinh
                    video.setPoster("dodg.jpg");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // goi insert
            videoService.updateVideo(video);

            // tra ve view
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
