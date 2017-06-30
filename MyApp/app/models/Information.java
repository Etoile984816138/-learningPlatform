package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 * 课时
 */
public class Information extends Model {
    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

    @OneToMany(mappedBy = "information")
    public List<Discuss> discusses;//所属课时

    @ManyToOne
    public Chapter chapter;

    public Information(int number, String title, Material material,
                       List<Discuss> discusses, Chapter chapter) {
        this.number = number;
        this.title = title;
        this.material = material;
        this.discusses = discusses;
        this.chapter = chapter;
    }
}
