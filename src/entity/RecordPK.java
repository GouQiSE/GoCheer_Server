package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Donggu on 2016/12/5.
 */
public class RecordPK implements Serializable {
    private String word;
    private Timestamp datetime;
    private String user;

    @Column(name = "word", nullable = false, length = 255)
    @Id
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Column(name = "datetime", nullable = false)
    @Id
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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

        RecordPK recordPK = (RecordPK) o;

        if (word != null ? !word.equals(recordPK.word) : recordPK.word != null) return false;
        if (datetime != null ? !datetime.equals(recordPK.datetime) : recordPK.datetime != null) return false;
        if (user != null ? !user.equals(recordPK.user) : recordPK.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
