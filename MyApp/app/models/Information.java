package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/29.
 * 章节列表
 */
public class Information extends Model {
    public int materials_id; //资料id
    public int chapter_id;//所属章节id

    public int number;//第几节

    public String title;//标题

    public Information(int materials_id, int chapter_id, int number, String title) {
        this.materials_id = materials_id;
        this.chapter_id = chapter_id;
        this.number = number;
        this.title = title;
    }
}
