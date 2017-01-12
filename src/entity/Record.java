package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Donggu on 2016/12/5.
 */
@Entity
@IdClass(RecordPK.class)
public class Record {
    private String word;
    private Timestamp datetime;
    private String user;

    public Record(){
        datetime=Timestamp.valueOf(LocalDateTime.now());
    }
    public Record(String word, String user){
        this();
        this.word=word;
        this.user=user;
    }

    @Id
    @Column(name = "word", nullable = false, length = 255)
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Id
    @Column(name = "datetime", nullable = false)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Id
    @Column(name = "user", nullable = false, length = 255)
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

        Record record = (Record) o;

        if (word != null ? !word.equals(record.word) : record.word != null) return false;
        if (datetime != null ? !datetime.equals(record.datetime) : record.datetime != null) return false;
        if (user != null ? !user.equals(record.user) : record.user != null) return false;

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
