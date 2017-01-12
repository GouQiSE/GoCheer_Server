package dao;

import entity.Achievement;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Donggu on 2016/12/5.
 */
public class AchievementDAO extends BaseDAO<Achievement> {
    private static AchievementDAO instance = new AchievementDAO();
    private AchievementDAO(){}

    public static AchievementDAO getInstance(){
        return instance;
    }

    public void delete(int id) {
        super.delete(Achievement.class, id);
    }

    public Achievement findById(int id) {
        return super.findById(Achievement.class, id);
    }

    /**
     * get all achievements.
     * @return List of all achievements.
     */
    public List getAllAchievements(){
        Session session = getSession();
        List result = session.createQuery("from Achievement order by hidden,type").list();
        session.close();
        return result;
    }
}
