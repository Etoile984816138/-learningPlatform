package models;

import play.db.jpa.Model;

import javax.persistence.*;

/**
 * Created by C_C on 2017/6/29.
 */
@Entity
public class Material extends Model {
    public String url;//链接
    public String time;//创建时间

    public int type;//类型（视频：0；音频：1；PPT：2）

    public String subtitle;//字幕（若为音频）

    public int lenth;//时间长度（若为视频）
    public int page;//页数（若为PPT）

    public Material(String url, String time, int type, String subtitle, int lenth, int page) {
        this.url = url;
        this.time = time;
        this.type = type;
        this.subtitle = subtitle;
        this.lenth = lenth;
        this.page = page;
    }
}
