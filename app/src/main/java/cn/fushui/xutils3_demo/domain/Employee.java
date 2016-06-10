package cn.fushui.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jianfeng on 2016/6/10.
 */
@Table(name = "employee", onCreated = "")
public class Employee {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "empId")
    private String empId;

    @Column(name = "name")
    private String name;

    @Column(name = "depId")
    private String depId;

    public Employee() {

    }

    public Employee(String empId, String name, String depId) {
        this.empId = empId;
        this.name = name;
        this.depId = depId;
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

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
}
