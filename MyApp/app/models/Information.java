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

    @OneToMany(mappedBy = "information")
    public List<Discuss> discusses;//所属课时

    @ManyToOne
    public Chapter chapter;

    @OneToMany(mappedBy = "information")
    public List<History> histories;
}
