package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * Created by jsh3 on 17-6-30.
 */
@Entity
public class New extends Model implements Comparable<New> {

    public String content;
    public String time;
    int type; // 1 代表好友通知 2代表系统通知
    boolean isRead; //0 没读

    @ManyToOne
    public Employee from;

    @ManyToOne
    public Employee to;


    @Override
    public int compareTo(New o) {
        LocalDate localDate = LocalDate.parse(o.time);
        LocalDate localDate1 = LocalDate.parse(time);
        int result = 0;
        if (localDate1.isBefore(localDate))
            result = 1;
        else if (localDate.isEqual(localDate1))
            result = 0;
        else
            result = -1;
        return result;
    }
}
