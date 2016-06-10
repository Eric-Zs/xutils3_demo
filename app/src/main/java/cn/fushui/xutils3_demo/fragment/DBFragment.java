package cn.fushui.xutils3_demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.DbManager;
import org.xutils.db.sqlite.SqlInfo;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.db.table.DbModel;
import org.xutils.db.table.TableEntity;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cn.fushui.xutils3_demo.R;
import cn.fushui.xutils3_demo.domain.DepartMent;
import cn.fushui.xutils3_demo.domain.Employee;
import cn.fushui.xutils3_demo.domain.Stu;
import cn.fushui.xutils3_demo.domain.StuToTea;
import cn.fushui.xutils3_demo.domain.StudentInfo;
import cn.fushui.xutils3_demo.domain.Teacher;

/**
 * Created by jianfeng on 2016/6/8.
 */
@ContentView(R.layout.db_fragment)
public class DBFragment extends Fragment {

    DbManager.DaoConfig config = new DbManager.DaoConfig()
            .setDbName("info,db")            //设置数据库名
            //设置成功创建表的监听
            .setTableCreateListener(new DbManager.TableCreateListener() {
                @Override
                public void onTableCreated(DbManager db, TableEntity<?> table) {
                    Log.i("feiyu", "onTableCreated >> " + table.getName());
                }
            })
            //设置更新的监听
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {

                }
            })
            //设置数据库打开的监听
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    db.getDatabase().enableWriteAheadLogging(); //开启多线程操作
                }
            });

    DbManager db = x.getDb(config);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /*
    * 1.创建，删除数据库
    * 2.创建，删除表
    * 3.增删查改数据
    * */

    //创建表
    @Event(value = R.id.id_bt_create_db)
    private void create(View v) throws DbException {

        List<StudentInfo> infos = new ArrayList<StudentInfo>();
        for (int i = 18; i < 28; i++) {
            StudentInfo info = new StudentInfo("liu" + i, i + "");
            infos.add(info);
        }

        //save方法可插入集合或单个对象
        db.save(infos);
    }

    //删除数据库
    @Event(value = R.id.id_bt_drop_db)
    private void drop_db(View v) throws DbException {
        db.dropDb();
    }

    //删除表
    @Event(value = R.id.id_bt_drop_tb)
    private void drop_tb(View v) throws DbException {
//        WhereBuilder builder = WhereBuilder.b();
//        builder.expr("id = '1'");
//        db.delete(StudentInfo.class,builder);
        db.dropTable(StudentInfo.class);
    }

    //    查询数据
    @Event(value = R.id.id_bt_query_db)
    private void query(View v) throws DbException {
        //查询第一条数据
//        StudentInfo info = db.findFirst(StudentInfo.class);
//        Log.i("feiyu", "firstData >>" + info.toString());

        WhereBuilder b = WhereBuilder.b();
        b.and("age", ">", 20);
        b.and("age", "<", 25);
        //1.    List<StudentInfo> infos = db.selector(StudentInfo.class).where(b).findAll();
        List<StudentInfo> infos = db.selector(StudentInfo.class).where("age", ">", 20).and("age", "<", 25).findAll();

        for (StudentInfo info : infos) {
            Log.i("feiyu", "Data >>" + info.toString());
        }
    }

    @Event(R.id.id_bt_alert_db)
    private void alert(View v) throws DbException {
        StudentInfo first = db.findFirst(StudentInfo.class);

//  1.      first.setAge("25");
//        first.setName("liu");
//        db.update(first, "name", "age");
//  2.      WhereBuilder b = WhereBuilder.b();
//        b.and("id", "=", first.getId());
//        KeyValue age = new KeyValue("age", "18");
//        KeyValue name = new KeyValue("name", "liu1");
//        db.update(StudentInfo.class,b,age,name);
        first.setAge("28");
        first.setName("liu");
        db.saveOrUpdate(first);
//        验证是否修改成功
        StudentInfo info = db.findFirst(StudentInfo.class);
        Log.i("feiyu", "firstData >>" + info.toString());
    }

    @Event(R.id.id_bt_delete_data)
    private void delete(View v) throws DbException{
            WhereBuilder b = WhereBuilder.b();
            //b.and("age", "=", 23);
            b.expr("age = '24'");
            db.delete(StudentInfo.class, b);
    }

    //一对多保存
    @Event(R.id.id_bt_oneToMany_db)
    private void saveOneToMany(View v) throws DbException{
        //随机生成id
        String depId = "abc";

        DepartMent dep = new DepartMent(depId,"研发部");

        Employee employee1 = new Employee(UUID.randomUUID() + "","雇员1",depId);
        Employee employee2 = new Employee(UUID.randomUUID() + "","雇员2",depId);
        Employee employee3 = new Employee(UUID.randomUUID() + "","雇员3",depId);


        //保存
        db.save(dep);
        db.save(employee1);
        db.save(employee2);
        db.save(employee3);


    }

    @Event(R.id.id_bt_manyToMany_db)
    private void saveManyToMany(View v) throws DbException {

        String java1 = UUID.randomUUID() + "";
        String java2 = UUID.randomUUID() + "";
        String android1 = UUID.randomUUID() + "";
        String android2 = UUID.randomUUID() + "";

        Stu stu_java1 = new Stu("stu_java1", "20", java1);
        Stu stu_java2 = new Stu("stu_java2", "22", java2);
        Stu stu_android1 = new Stu("stu_android1", "18", android1);
        Stu stu_android2 = new Stu("stu_android2", "25", android2);


        String t_java = UUID.randomUUID() + "";
        String t_android = UUID.randomUUID() + "";

        Teacher teacher_java = new Teacher("tea_java",t_java);
        Teacher teacher_android = new Teacher("tea_android", t_android);

        StuToTea stuToTea1 = new StuToTea(java1,t_java);
        StuToTea stuToTea2 = new StuToTea(java2, t_java);

        StuToTea stuToTea3 = new StuToTea(android1, t_android);
        StuToTea stuToTea4 = new StuToTea(android2, t_android);

        StuToTea stuToTea5 = new StuToTea(android1, t_java);
        StuToTea stuToTea6 = new StuToTea(android2, t_java);

        db.save(stu_android1);
        db.save(stu_android2);
        db.save(stu_java1);
        db.save(stu_java2);

        db.save(teacher_android);
        db.save(teacher_java);

        db.save(stuToTea1);
        db.save(stuToTea2);
        db.save(stuToTea3);
        db.save(stuToTea4);
        db.save(stuToTea5);
        db.save(stuToTea6);

    }

    //返回dbodule的查询
    @Event(R.id.id_bt_oneToMany_select)
    private void select_oneToMany(View v) throws DbException{
        //查询部门所有成员
        String sql = "select id, name, empId from employee where depId = 'abc'";
        List<DbModel> dbModelList = db.findDbModelAll(new SqlInfo(sql));
        for(DbModel model: dbModelList){
            int id = model.getInt("id");
            String name = model.getString("name");
            String empId = model.getString("empId");
            Log.i("feiyu", "id >> " + id + " name >> " +name + " empId >> " + empId );

        }
    }
    //返回dbodule的查询
    @Event(R.id.id_bt_manyToMany_select)
    private void select_manyToMany(View v) throws DbException{
        //查询某个老师的所有学生名字

        //先查询老师的id
        WhereBuilder b = WhereBuilder.b();
        b.and("name", "=", "tea_java");
        Teacher teacher = db.selector(Teacher.class).where(b).findFirst();
        Log.i("feiyu",teacher.toString());


         //化为一对多
        String sql = "select stuId from stuToTea where teaId = \'" + teacher.getTeaId()+"\'";
        List<DbModel> dbModelList = db.findDbModelAll(new SqlInfo(sql));
        for(DbModel model: dbModelList){
            //关系表中找出学生id
            String stuId = model.getString("stuId");

            Log.i("feiyu",stuId);
            //学生表中查询学生信息

            WhereBuilder builder = WhereBuilder.b();
            builder.and("StuId", "=", stuId);
            Stu stu = db.selector(Stu.class).where(builder).findFirst();
            Log.i("feiyu",stu.toString());
        }
    }
}
