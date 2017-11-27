package cn.androidstudy.course09;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/11/15.
 */

public class MyDbHelper extends SQLiteOpenHelper {

    /**
     * 构造函数
     * @param context：上下文环境
     * @param name  数据库名
     * @param factory   游标工厂类，一般情况下为空
     * @param version   数据库版本
     */
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public MyDbHelper(Context context) {
        super(context, "user.db", null, 1);
    }
    //第一次运行时执行，主要用来创建数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表SQL语句
        String stu_table="create table usertable(_id integer " +
                "primary key autoincrement,sname text," +
                "snumber text);";
        //执行SQL语句
        db.execSQL(stu_table);
    }
    //升级时执行，重建数据库或者对现有的数据库进行修改
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
