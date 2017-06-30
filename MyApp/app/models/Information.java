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

    @ManyToOne
    public Chapter chapter;

    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

    @OneToOne(mappedBy = "information")
    public History history;

    @OneToMany(mappedBy = "information_id")
    public List<Discuss> discusses;


    public Information(Chapter chapter, int number, String title, Material material, History history, List<Discuss> discusses) {
        this.chapter = chapter;
        this.number = number;
        this.title = title;
        this.material = material;
        this.history = history;
        this.discusses = discusses;
    }
}
