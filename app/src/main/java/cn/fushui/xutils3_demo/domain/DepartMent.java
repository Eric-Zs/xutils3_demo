package cn.fushui.xutils3_demo.domain;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by jianfeng on 2016/6/10.
 */
@Table(name = "dept", onCreated = "")
public class DepartMent {
    @Column(name = "id", isId = true)
    private int id;

    @Column(name = "depId")
    private String DepId;

    @Column(name = "name")
    private String name;

    public DepartMent() {

    }

    public DepartMent(String depId, String name) {
        DepId = depId;
        this.name = name;
    }

    public String getDepId() {
        return DepId;
    }

    public void setDepId(String depId) {
        DepId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
