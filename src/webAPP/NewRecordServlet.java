package webAPP;

import dao.AchievementDAO;
import dao.AchievementUserDAO;
import dao.RecordDAO;
import dao.UserDAO;
import entity.Achievement;
import entity.AchievementUser;
import entity.Record;
import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Donggu on 2016/12/11.
 */
@WebServlet(name = "NewRecordServlet")
public class NewRecordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String username = (String) request.getSession().getAttribute("user");
        if (username == null) {
            JSONObject error = new JSONObject();
            error.put("error",1);
            error.put("message","Haven't log in");
            response.getWriter().append(error.toJSONString());
            return;
        }

        User user = UserDAO.getInstance().findById(username);
        String word = request.getParameter("word");
        word = word.toLowerCase();
        Pattern exp = Pattern.compile("([a-z]+)");
        Matcher matcher = exp.matcher(word);
        if (!matcher.find()) {
            JSONObject error = new JSONObject();
            error.put("error",2);
            error.put("message","No avaliable words.");
            response.getWriter().append(error.toJSONString());
            return;
        }

        // 查了一个单词才加入记录
        Record record = null;
        if (matcher.groupCount() == 1) {
            record = new Record(matcher.group(0), user.getUsername());
            RecordDAO.getInstance().save(record);
        }

        // update user info
        user.setScore(user.getScore() + 1);
        user.setWordsum(user.getWordsum() + 1);
        UserDAO.getInstance().update(user);

        // check for new Achievements
        ArrayList<Integer> newAchievement = user.checkAchievement(record);
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        if(newAchievement.isEmpty()){
           json.put("achievement",null);
        }
        else{
            for(Integer i : newAchievement){
                AchievementUserDAO.getInstance().save(new AchievementUser(i,user.getUsername()));
                Achievement a = AchievementDAO.getInstance().findById(i);
                array.add(a.JSONInfo());
                user.setScore(user.getScore()+a.getScore());
                // 发送邮件
                if(user.getEmail()!=null && !user.getEmail().isEmpty()){
                    SendMail.SendCongratulations(user, a);
                }
            }
            json.put("achievement",array);
            user.setScoresum(user.getScoresum()+newAchievement.size());
            UserDAO.getInstance().update(user);
        }

        response.getWriter().append(json.toJSONString());
    }
}
