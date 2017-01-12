package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Donggu on 2016/12/11.
 */
@Entity
@Table(name = "achievement_user", schema = "javaEE", catalog = "")
@IdClass(AchievementUserPK.class)
public class AchievementUser{
    private int achievement;
    private String user;
    private Timestamp time;

    public AchievementUser(){time=Timestamp.valueOf(LocalDateTime.now());}
    public AchievementUser(int achievement, String user){
        this();
        this.achievement=achievement;
        this.user=user;
    }

    @Id
    @Column(name = "achievement", nullable = false)
    public int getAchievement() {
        return achievement;
    }

    public void setAchievement(int achievement) {
        this.achievement = achievement;
    }

    @Id
    @Column(name = "user", nullable = false, length = 255)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AchievementUser that = (AchievementUser) o;

        if (achievement != that.achievement) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = achievement;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }
}
