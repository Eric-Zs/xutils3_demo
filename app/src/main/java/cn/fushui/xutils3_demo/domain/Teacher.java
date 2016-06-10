package cn.fushui.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jianfeng on 2016/6/10.
 */
@Table(name = "teacher", onCreated = "")

public class Teacher {

    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "teaId")
    private String teaId;

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teaId='" + teaId + '\'' +
                '}';
    }

    public Teacher() {

    }
    public Teacher(String name, String teaId) {
        this.name = name;
        this.teaId = teaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }
}
