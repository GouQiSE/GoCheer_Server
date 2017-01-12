package webAPP;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Donggu on 2016/12/20.
 */
@WebServlet(name = "UpdateInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("user");
        if(username==null){
            response.sendRedirect("../login.jsp");
            return;
        }
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String alias = request.getParameter("alias");
        User user = UserDAO.getInstance().findById(username);
        if(password!=null && !password.isEmpty()){
            user.setPassword(password);
        }
        if(email!=null && !email.isEmpty()){
            user.setEmail(email);
        }
        if(alias!=null&&!alias.isEmpty()){
            user.setAlias(alias);
        }
        if(gender!=null){
            user.setGender(Boolean.parseBoolean(gender));
        }
        UserDAO.getInstance().update(user);
        username = URLEncoder.encode(username, "utf-8");
        response.sendRedirect("home/"+username);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
