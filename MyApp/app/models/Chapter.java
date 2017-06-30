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
    public int number;//第几章节

    public String title;//标题
    @OneToMany(mappedBy = "chapter")
    public List<Information> informations;

    @ManyToOne
    public Course course;


    public Chapter(int number, String title, List<Information> informations, Course course) {
        this.number = number;
        this.title = title;
        this.informations = informations;
        this.course = course;
    }
}
