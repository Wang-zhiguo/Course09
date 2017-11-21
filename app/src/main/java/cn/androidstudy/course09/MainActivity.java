package cn.androidstudy.course09;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDbHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new MyDbHelper(this);
        db = helper.getWritableDatabase();
    }
    //作用:创建菜单。在这里，我们将定义好的菜单加载进来。
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //新建的xml文件
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //为菜单项添加事件处理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.baseadapter:
                //Toast.makeText(this, "BaseAdapter Click!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,BaseAdapterActivity.class);
                startActivity(intent);
                break;
            case R.id.arrayadapter:
                //Toast.makeText(this, "ArrayAdapter Click!", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this,ArrayAdapterActivity.class);
                startActivity(intent2);
                break;
            case R.id.simpleadapter:
                //Toast.makeText(this, "SimpleAdapter Click!", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this,SimpleAdapterActivity.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                insert(db);
                break;
            case R.id.button2:
                insert2(db);
                break;
            case R.id.button3:
                delete(db);
                break;
            case R.id.button4:
                update(db);
                break;
            case R.id.button5:
                query(db);
                break;
        }
    }

    private void insert(SQLiteDatabase db) {
        //实例化常量值
        ContentValues cValue = new ContentValues();
        //添加用户名
        cValue.put("sname", "xiaoming");
        //添加密码
        cValue.put("snumber", "01005");
        //调用insert()方法插入数据
        db.insert("usertable", null, cValue);
    }

    private void insert2(SQLiteDatabase db) {
        //插入数据SQL语句
        String sql = "insert into usertable(sname,snumber) values('xiaoming','01005')";
        //执行SQL语句
        db.execSQL(sql);
    }

    private void delete(SQLiteDatabase db) {
        //删除条件
        String whereClause = "_id=?";
        //删除条件参数
        String[] whereArgs = {String.valueOf(2)};
        //执行删除
        db.delete("usertable", whereClause, whereArgs);
    }

    private void delete2(SQLiteDatabase db) {
        //删除SQL语句
        String sql = "delete from usertable where _id = 6";
        //执行SQL语句
        db.execSQL(sql);
    }

    private void update(SQLiteDatabase db) {
        //实例化内容值
        ContentValues values = new ContentValues();
        //在values中添加内容
        values.put("snumber", "101003");
        //修改条件
        String whereClause = "_id=?";
        //修改添加参数
        String[] whereArgs = {String.valueOf(1)};
        //修改
        db.update("usertable", values, whereClause, whereArgs);
    }

    private void update2(SQLiteDatabase db) {
        //修改SQL语句
        String sql = "update usertable set snumber = 654321 where _id = 1";
        //执行SQL
        db.execSQL(sql);
    }

    private void query(SQLiteDatabase db) {
        //查询获得游标
        Cursor cursor = db.query("usertable", null, null, null, null, null, null);

        //判断游标是否为空
        if (cursor.moveToFirst()) {
            //遍历游标
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.move(i);
                //获得ID
                int id = cursor.getInt(0);
                //获得用户名
                String username = cursor.getString(1);
                //获得密码
                String password = cursor.getString(2);
                //输出用户信息
                System.out.println(id + ":" + username + ":" + password);
            }
        }
    }
}
