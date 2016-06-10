package cn.fushui.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jianfeng on 2016/6/10.
 */
//声明表名,onCreated在表创建时被调用,可写入sql语句
@Table(name = "student_info", onCreated = "")
public class StudentInfo {
    //声明主键
    @Column(name = "id", isId = true, autoGen = true, property = "NOT NULL")
    private int id;
    //声明普通列名
    private @Column(name = "name")
    String name;
    private @Column(name = "age")
    String age;


    //必须有个空参数的构造方法
    public StudentInfo() {

    }
    public StudentInfo(String name, String age) {
        this.name = name;
        this.age = age;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
