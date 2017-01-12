package webAPP;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by orient on 2016/12/8.
 */
@WebServlet(name = "SignupServlet")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender=request.getParameter("gender");
        String alias=request.getParameter("alias");
        String email=request.getParameter("email");

        User user = UserDAO.getInstance().findById(username);
        if(user != null){
            response.sendRedirect("/error.jsp");
        }
        else{

           // user=UserDAO.getInstance().save(new User(username,password,));
            boolean gender_bool;
            if(Integer.parseInt(gender)==1)
                gender_bool=true;
            else gender_bool=false;

            User user1=new User(username,password,gender_bool,alias,email);
            UserDAO.getInstance().save(user1);

            if(UserDAO.getInstance().findById(username)==null)
                response.sendRedirect("/error.jsp");
            else {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user1.getUsername());
                Cookie cookie = new Cookie("sessionId", httpSession.getId());
                response.addCookie(cookie);
                request.getRequestDispatcher("/login").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/signup.jsp");
    }

}
