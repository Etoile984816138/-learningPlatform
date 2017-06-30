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
<<<<<<< HEAD
=======

    @ManyToOne
    public Chapter chapter;

>>>>>>> b3382aa2780343ebef80e390039dd614e85d1658
    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

<<<<<<< HEAD
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
=======
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
>>>>>>> b3382aa2780343ebef80e390039dd614e85d1658
    }
}
