package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 * 章节
 */

@Entity
public class Chapter extends Model {

    public int course_id;//所属课程id
    public int number;//第几章节

    public String title;//标题
    @OneToMany(mappedBy = "chapter_id")
    public List<Information> informations;

    public Chapter(int course_id, int number, String title, List<Information> informations) {
        this.course_id = course_id;
        this.number = number;
        this.title = title;
        this.informations = informations;
    }
}
