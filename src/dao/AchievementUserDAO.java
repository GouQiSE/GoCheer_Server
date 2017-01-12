package dao;

import entity.AchievementUser;
import entity.AchievementUserPK;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Donggu on 2016/12/5.
 */
public class AchievementUserDAO extends BaseDAO<AchievementUser> {
    private static AchievementUserDAO instance = new AchievementUserDAO();
    private AchievementUserDAO(){}

    public static AchievementUserDAO getInstance(){
        return instance;
    }

    public void delete(AchievementUserPK pk) {
        super.delete(AchievementUser.class, pk);
    }

    public AchievementUser findById(AchievementUserPK pk) {
        return super.findById(AchievementUser.class, pk);
    }

    /**
     * get Achievements got by user, id only.
     * @param username username
     * @return ArrayList of Integer.
     */
    public ArrayList<Integer> findByUser(String username){
        Session session = BaseDAO.getSession();
        Query query = session.createQuery("from AchievementUser where user = :username");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.close();

        ArrayList<Integer> result = new ArrayList<>();
        for(Iterator it = list.iterator();it.hasNext();){
            AchievementUser a = (AchievementUser)it.next();
            result.add(a.getAchievement());
        }
        return result;
    }

    /**
     * get all Achievements which the user has got, in time order.
     * @param username username
     * @return ArrayList of AchievementUser, sorted by time desc
     */
    public List getUserAchievements(String username){
        Session session = getSession();
        Query query = session.createQuery("from AchievementUser where user = :username order by time desc");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.close();
        return list;
    }

    /**
     * get all Achievements which the user hasn't got, in type order.
     * @param username username
     * @return ArrayList of AchievementUser, sorted by type
     */
    public List getNotUserAchievements(String username){
        Session session = getSession();
        Query query = session.createQuery("FROM Achievement where id not in ( SELECT achievement FROM AchievementUser WHERE user = :username)order by hidden,type");
        query.setParameter("username", username);
        List list = query.getResultList();
        session.close();
        return list;
    }
}
