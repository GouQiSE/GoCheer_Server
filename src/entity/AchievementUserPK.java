package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Donggu on 2016/12/5.
 */
public class AchievementUserPK implements Serializable {
    private int achievement;
    private String user;

    @Column(name = "achievement", nullable = false)
    @Id
    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    @Column(name = "user", nullable = false, length = 255)
    @Id
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchievementUserPK that = (AchievementUserPK) o;

        if (achievement != that.achievement) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = achievement;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
