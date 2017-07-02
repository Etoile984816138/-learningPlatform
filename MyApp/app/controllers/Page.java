package controllers;

/**
 * Created by jsh3 on 17-7-2.
 */
public class Page {

    public int pageNum;
    public int pageSize;

    public Page(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int caculateOffset() {
        return (pageNum - 1) * pageSize;
    }
}
