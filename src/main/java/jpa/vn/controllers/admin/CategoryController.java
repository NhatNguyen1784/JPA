package jpa.vn.controllers.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jpa.vn.entity.Category;
import jpa.vn.services.ICategoryService;
import jpa.vn.services.Impl.CategoryServiceImpl;
import jpa.vn.utils.Constant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(urlPatterns = {"/admin/categories","/admin/category/add","/admin/category/insert",
        "/admin/category/edit", "/admin/category/update", "/admin/category/delete"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
public class CategoryController extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;

    ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if(url.contains("/admin/categories")){
            List<Category> list = categoryService.findAll();
            req.setAttribute("listcate", list);
            req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

        }
        else if(url.contains("/admin/category/add")){
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/category/edit")){
            int id = Integer.parseInt(req.getParameter("id"));
            Category category = categoryService.findById(id);
            req.setAttribute("cate", category);
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
        }
        else if(url.contains("/admin/category/delete")){
            int id = Integer.parseInt(req.getParameter("id"));
            categoryService.deleteCategory(id);
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        if(url.contains("/admin/category/insert")){

            // lay data tu form
            String categoryName = req.getParameter("categoryname");
            String image = req.getParameter("image");
            int status = Integer.parseInt(req.getParameter("status"));

            // đưa data vao model
            Category category = new Category();
            category.setCategoryname(categoryName);
            category.setStatus(status);
            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File file = new File(uploadPath);
            if(!file.exists()){
                file.mkdirs();
            }
            try{
                Part part = req.getPart("image1");
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
                    category.setImage(fname);
                } else if (image != null) {
                    category.setImage(image);
                }
                else {

                    // neu khong chon file nao thi tai len file mac đinh
                    category.setImage("avatar.png");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // goi phuong thuc insert
            categoryService.insertCategory(category);

            // tra ve view
            resp.sendRedirect(req.getContextPath() + "/admin/categories");
        } else if (url.contains("/admin/category/update")) {
            int id = Integer.parseInt(req.getParameter("categoryid"));
            String categoryName = req.getParameter("categoryname");
            String image = req.getParameter("image");
            int status = Integer.parseInt(req.getParameter("status"));

            // đưa data vao model
            Category category = categoryService.findById(id);
            category.setCategoryname(categoryName);
            category.setStatus(status);
            String fname = "";
            String uploadPath = Constant.UPLOAD_DIRECTORY;
            File file = new File(uploadPath);
            if(!file.exists()){
                file.mkdirs();
            }
            try{
                Part part = req.getPart("image1");
                if(part.getSize() > 0){
                    String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                    // đổi tên file nếu up 1 file trước đó xong up 1 file khác lên
                    int index = fileName.lastIndexOf(".");
                    String ext = fileName.substring(index + 1);
                    fname = System.currentTimeMillis() + "." + ext;

                    // upload file
                    part.write(uploadPath  + fname);
                    category.setImage(fname);
                } else if (image != null) {
                    category.setImage(image);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            // goi phương thức update
            categoryService.updateCategory(category);

            // tra ve view
            resp.sendRedirect(req.getContextPath() + "/admi");
        }
    }
}
