package webAPP;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Donggu on 2016/12/3.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String extension = request.getParameter("extension");
        User user = UserDAO.getInstance().findById(username,password);

        if(extension!=null && extension.equals("true")){
            response.setContentType("application/json");
            if(user==null){
                request.getSession().removeAttribute("user");
                response.getWriter().append("false");
            }
            else{
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user",user.getUsername());
                response.getWriter().append("true");
            }
            return;
        }

        if(user == null){
            request.getSession().removeAttribute("user");
            request.setAttribute("wrong", "");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
//            response.sendRedirect("/error.jsp");
        }
        else{
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user",user.getUsername());
            response.sendRedirect("/home/"+URLEncoder.encode(username,"utf-8"));
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("user");
        if(username!=null){
            User user = UserDAO.getInstance().findById(username);
            response.sendRedirect("/home/" + URLEncoder.encode(user.getUsername(),"utf-8"));
        }
        else{
            response.sendRedirect("/login.jsp");
        }
    }
}
