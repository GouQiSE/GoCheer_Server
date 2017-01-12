package entity;

import dao.AchievementUserDAO;
import dao.RecordDAO;
import org.json.simple.JSONObject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Donggu on 2016/12/10.
 */
@Entity
public class User implements JSONable {
    private String username;
    private String password;
    private boolean gender;
    private String alias;
    private Timestamp registertime;
    private String email;
    private int score;
    private int wordsum;
    private int scoresum;

    public User(){registertime = Timestamp.valueOf(LocalDateTime.now());}

    public User(String username, String password, boolean gender, String alias, String email) {
        this();
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.alias = alias;
        this.email = email;
    }

    /**
     * generate JSONObject for user info
     * @return user infomation (no password)
     */
    public JSONObject JSONInfo(){
        JSONObject json = new JSONObject();
        json.put("username", username);
        json.put("gender",gender);
        json.put("alias",alias);
        json.put("email",email);
        json.put("registerTime",registertime.toString());
        json.put("score",score);
        json.put("wordsum",wordsum);
        json.put("scoresum",scoresum);
        return json;
    }

    private boolean repeatCertainTime(List history, int standard, int duration, Record newRecord, int cond2){
        if (history.size() >= standard)
        {
            int count = 0;
            LocalDateTime t = newRecord.getDatetime().toLocalDateTime().minusHours((long)duration);
            for (Iterator i = history.iterator(); i.hasNext(); )
            {
                Record r = (Record) i.next();
                if (r.getDatetime().toLocalDateTime().isAfter(t))
                    count++;
                if (count == cond2)
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * check whether user gets new achievements
     * @param newRecord new record of searching word (null maybe)
     * @return arraylist of new achievements
     */
    public ArrayList<Integer> checkAchievement(Record newRecord){
        ArrayList<Integer> newAchievements = new ArrayList<>();
        List notUserAchievements = AchievementUserDAO.getInstance().getNotUserAchievements(this.username);

        // 对于某些同类互斥的成就，使用flag来跳过不需要的检测
        boolean specificWordDone=false;
        boolean dayDone=false;
        boolean dateDone=false;
        boolean timeDone=false;

        // 对于重复的查询和转化进行缓存以提高效率
        String dayOfWeek=null;
        int month=-1, day=-1;
        int hour=-1;
        List userHistoryByWord=null;
        List userHistory=null;
        int repeatCount=-1;

        // 遍历查看
        for(Iterator it = notUserAchievements.iterator(); it.hasNext();){
            Achievement a = (Achievement)it.next();
            // 根据成就类型进行读取
            switch (a.getType()){
                // 查询次数达到指定数量
                case "wordsum":
                    if(this.wordsum >= Integer.parseInt(a.getCondition())){
                        newAchievements.add(a.getId());
                    }
                    break;
                // 查询某些特定单词
                case "specific word":
                    if(!specificWordDone&&newRecord.getWord().equals(a.getCondition())){
                        newAchievements.add(a.getId());
                        specificWordDone=true;
                    }
                    break;
                //在特定的星期几刷词获得成就
                case "day":
                    if(!dayDone)
                    {
                        if(dayOfWeek==null){
                            dayOfWeek = LocalDate.now().getDayOfWeek().toString();
                        }
                        if(dayOfWeek.equals(a.getCondition())){
                            newAchievements.add(a.getId());
                            dayDone=true;
                        }
                    }
                    break;
                //在特定的日期刷词获得成就，一月一号在record的condition中存为0101
                case "date":
                    if(!dateDone)
                    {
                        if(month==-1) month=LocalDate.now().getMonthValue();
                        if(day==-1) day=LocalDate.now().getDayOfMonth();
                        int m=Integer.parseInt(a.getCondition().substring(0,2));
                        int d=Integer.parseInt(a.getCondition().substring(2,4));
                        if(month==m&&day==d){
                            newAchievements.add(a.getId());
                            dateDone=true;
                        }
                    }
                    break;
                //在特定的时间点刷词获得成就
                case "time":
                    if(!timeDone)
                    {
                        if(hour==-1) hour = LocalTime.now().getHour();
                        if( hour==Integer.parseInt(a.getCondition())) {
                            newAchievements.add(a.getId());
                            timeDone = true;
                        }
                    }
                    break;
                //在某个时间段重复查一个单词获得成就
                case "duration & repeat count":
                    if(newRecord!=null)
                    {
                        String specific_word=newRecord.getWord();
                        if(userHistoryByWord==null)
                            userHistoryByWord = RecordDAO.getInstance().getUserHistory(username,specific_word);
                        int repeat_count = Integer.parseInt(a.getCondition2());
                        int duration=Integer.parseInt(a.getCondition());
                        if(repeatCertainTime(userHistoryByWord,repeat_count,duration,newRecord,Integer.parseInt(a.getCondition2()))){
                            newAchievements.add(a.getId());
                            break;
                        }
                    }
                    break;
                // 在指定时间内查够了一定量的单词获得成就
                case "duration & wordsum":
                    if(newRecord!=null)
                    {
                        if(userHistory==null) userHistory=RecordDAO.getInstance().getUserHistory(username);
                        int duration=Integer.parseInt(a.getCondition());
                        int wordsum=Integer.parseInt(a.getCondition2());
                        if(repeatCertainTime(userHistory,wordsum,duration,newRecord,Integer.parseInt(a.getCondition2()))){
                            newAchievements.add(a.getId());
                            break;
                        }
                    }
                    break;
                //重复刷某个词累计达到一个数值获得成就
                case "repeat count":
                    if(newRecord!=null)
                    {
                        if(repeatCount==-1) repeatCount=RecordDAO.getInstance().getRepeatCount(username,newRecord.getWord());
                        if(repeatCount == Integer.parseInt(a.getCondition()))
                            newAchievements.add(a.getId());
                    }
                    break;
                // 在特定的星期几的某个时刻划词获得成就
                case "day & time":
                        if(dayOfWeek==null){
                            dayOfWeek=LocalDate.now().getDayOfWeek().toString();
                        }
                        if(hour==-1) hour=LocalTime.now().getHour();

                        if(dayOfWeek.equals(a.getCondition()) && hour==Integer.parseInt(a.getCondition2()))
                            newAchievements.add(a.getId());
                    break;
                // 在特定的日期划到了一定量的词获得成就
                case "date & wordsum":
                        if(month==-1) LocalDate.now().getMonthValue();
                        if(day==-1) day=LocalDate.now().getDayOfMonth();
                        int m=Integer.parseInt(a.getCondition().substring(0,2));
                        int d=Integer.parseInt(a.getCondition().substring(2,4));
                        int sum=Integer.parseInt(a.getCondition2());
                        if(month==m&&day==d&&this.wordsum >=sum)
                            newAchievements.add(a.getId());
                    break;
//                //在某个时刻划到了一定量的词获得成就
                case "time & wordsum":
                        if(hour==-1) hour=LocalTime.now().getHour();
                        int summ=Integer.parseInt(a.getCondition2());
                        if(hour==Integer.parseInt(a.getCondition())&&this.wordsum >=summ)
                        {
                            newAchievements.add(a.getId());
                        }
                    break;
//                //在特定的日期时间划词获得了成就
                case "date & time":
                        if(month==-1) month=LocalDate.now().getMonthValue();
                        if(day==-1) day=LocalDate.now().getDayOfMonth();
                        int m1=Integer.parseInt(a.getCondition().substring(0,2));
                        int d1=Integer.parseInt(a.getCondition().substring(2,4));
                        if(hour==-1) hour=newRecord.getDatetime().toLocalDateTime().getHour();
                        if(month==m1&&day==d1&&hour==Integer.parseInt(a.getCondition2()))
                            newAchievements.add(a.getId());
                    break;
//                //在特定的日子刷到了特定的词语获得成就
                case "date & specific word":
                    if(newRecord!=null)
                    {
                        if(month==-1) month=newRecord.getDatetime().toLocalDateTime().getMonthValue();
                        if(day==-1) day=newRecord.getDatetime().toLocalDateTime().getDayOfMonth();
                        int m2=Integer.parseInt(a.getCondition().substring(0,2));
                        int d2=Integer.parseInt(a.getCondition().substring(2,4));

                        if(month==m2&&day==d2&&newRecord.getWord().equals(a.getCondition2()))
                            newAchievements.add(a.getId());
                    }
                    break;

                default:
            }
        }
        return newAchievements;
    }

    @Id
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "gender", nullable = false)
    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "alias", nullable = false, length = 255)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Basic
    @Column(name = "registertime", nullable = false)
    public Timestamp getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Timestamp registertime) {
        this.registertime = registertime;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Column(name = "wordsum", nullable = false)
    public int getWordsum() {
        return wordsum;
    }

    public void setWordsum(int wordsum) {
        this.wordsum = wordsum;
    }

    @Basic
    @Column(name = "scoresum", nullable = false)
    public int getScoresum() {
        return scoresum;
    }

    public void setScoresum(int scoresum) {
        this.scoresum = scoresum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (gender != user.gender) return false;
        if (score != user.score) return false;
        if (wordsum != user.wordsum) return false;
        if (scoresum != user.scoresum) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (alias != null ? !alias.equals(user.alias) : user.alias != null) return false;
        if (registertime != null ? !registertime.equals(user.registertime) : user.registertime != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (gender ? 1 : 0);
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (registertime != null ? registertime.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + score;
        result = 31 * result + wordsum;
        result = 31 * result + scoresum;
        return result;
    }
}
