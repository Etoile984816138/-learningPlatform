package models;

import play.db.jpa.*;

import javax.persistence.*;
import java.util.List;

/**
 * Created by C_C on 2017/6/27.
 * 权限组
 */
@Entity
public class Authority extends Model {

    public String team;

    @OneToMany(mappedBy = "authority_id")
    public List<Power> powers;

    public Authority(String team, List<Power> powers) {
        this.team = team;
        this.powers = powers;
    }
}
