package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Generic extends Model {

    public int d_id;
    public String name;


}
