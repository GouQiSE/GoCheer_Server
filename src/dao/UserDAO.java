package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by Donggu on 2016/12/5.
 */
public class UserDAO extends BaseDAO<User> {
    private static UserDAO instance = new UserDAO();
    private UserDAO(){}

    public static UserDAO getInstance(){
        return instance;
    }

    public User findById(String username) {
        return super.findById(User.class, username);
    }

    public User findById(String username, String password){
        User user = findById(username);
        if(user!=null&&user.getPassword().equals(password)) return user;
        return null;
    }

    public void delete(String username) {
        super.delete(User.class, username);
    }

    /**
     * get User list ordered by score.
     * @return list of User in descend score order.
     */
    public List getLeaderboard(){
        Session session = getSession();
        List result = session.createQuery("from User order by score desc, alias").list();
        session.close();
        return result;
    }
}
