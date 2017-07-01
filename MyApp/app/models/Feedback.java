package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by jsh3 on 17-7-1.
 */
@Entity
public class Feedback extends Model {

    public int type;
    public String content;
    public String img1;
    public String img2;
    public String img3;
    public String img4;
    public String img5;
    public String url;

    @ManyToOne
    public Employee employee;
}
