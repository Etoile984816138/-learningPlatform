package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/26.
 */
@Entity
public class Style extends Model {

    public int g_id;
    public String name;

    public Style(int g_id, String name) {
        this.g_id = g_id;
        this.name = name;
    }
}
