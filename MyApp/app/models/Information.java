package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/29.
 * 课时
 */
public class Information extends Model {
    public int chapter_id;//所属课时id

    public int number;//第几课时

    public String title;//标题

    @OneToOne
    public Material material;

    @OneToMany(mappedBy = "information_id")
    public List<Discuss> discusses;

    public Information(int chapter_id, int number, String title, Material material) {
        this.chapter_id = chapter_id;
        this.number = number;
        this.title = title;
        this.material = material;
    }


}
