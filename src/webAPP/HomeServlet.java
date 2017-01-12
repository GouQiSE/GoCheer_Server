package webAPP;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by Donggu on 2016/12/17.
 */
@WebServlet(name = "HomeServlet")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if(uri.equals("/home"))uri="/home/";
        String targetUsername = URLDecoder.decode(uri,"utf-8").substring(6);
        String currentUsername = (String)request.getSession().getAttribute("user");
        if(targetUsername.isEmpty()){
            response.sendRedirect("../error.jsp");
            return;
        }
        User targetUser = UserDAO.getInstance().findById(targetUsername);
        if(targetUser==null){
            response.sendRedirect("../error.jsp");
        }
        else{
            request.setAttribute("targetUser", targetUser);
            request.setAttribute("isSelf",(currentUsername!=null&&currentUsername.equals(targetUsername)));
            request.getRequestDispatcher("/page/home.jsp").forward(request,response);
        }
    }
}
