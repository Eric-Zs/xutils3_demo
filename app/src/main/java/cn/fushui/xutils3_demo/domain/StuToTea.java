package cn.fushui.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jianfeng on 2016/6/10.
 */
@Table(name = "stuToTea", onCreated = "")
public class StuToTea {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "stuId")
    private String stuId;

    @Column(name = "teaId")
    private String teaId;
    public StuToTea() {

    }

    @Override
    public String toString() {
        return "StuToTea{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", teaId='" + teaId + '\'' +
                '}';
    }

    public StuToTea(String stuId, String teaId) {
        this.stuId = stuId;
        this.teaId = teaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }
}
