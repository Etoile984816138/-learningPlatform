package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 * 课时
 */
@Entity
public class Information extends Model {
    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

    @ManyToOne
    public Chapter chapter;


    @OneToMany(mappedBy = "information")
    public List<History> histories;

    @OneToMany(mappedBy = "information")
    public List<Discuss> discusses;


    public Information(int number, String title, Material material, Chapter chapter
            , List<History> histories, List<Discuss> discusses) {
        this.number = number;
        this.title = title;
        this.material = material;
        this.chapter = chapter;
        this.histories = histories;
        this.discusses = discusses;
    }
}
