package cn.androidstudy.course09;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends AppCompatActivity {
    MyDbHelper helper;
    SQLiteDatabase db;
    private ListView list;
    private EditText et_name;
    private EditText et_number;
    private List<User> users = new ArrayList<>();
    private long user_id;
    private int m_pos;
    private AllAdapter allAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        helper = new MyDbHelper(this);
        db = helper.getWritableDatabase();
        initView();
    }

    private void initView() {
        et_name = (EditText)findViewById(R.id.editText);
        et_number = (EditText)findViewById(R.id.editText2);
        list = (ListView)findViewById(R.id.listview);
        allAdapter = new AllAdapter();
        list.setAdapter(allAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = users.get(position);
                m_pos = position;
                user_id = user.getId();
                et_name.setText(user.getName());
                et_number.setText(user.getNumber());
            }
        });
    }

    public void btnClick(View view){
        String name = et_name.getText().toString();
        String number = et_number.getText().toString();

        switch (view.getId()){
            case R.id.btn_insert:
                //实例化常量值  Key（表中的列名）-Value
                ContentValues cValue = new ContentValues();
                //添加用户名
                cValue.put("sname", name);
                //添加密码
                cValue.put("snumber", number);
                //调用insert()方法插入数据
                long id = db.insert("usertable", null, cValue);
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setNumber(number);
                users.add(user);
                break;
            case R.id.btn_delete:
                //删除条件
                String whereClause = "_id=?";
                //删除条件参数
                String[] whereArgs = {String.valueOf(user_id)};
                //执行删除
                db.delete("usertable", whereClause, whereArgs);
                users.remove(m_pos);
                break;
            case R.id.btn_update:
                //实例化内容值
                ContentValues values = new ContentValues();
                //在values中添加内容
                values.put("sname",name);
                values.put("snumber", number);
                //修改条件
                String whereClause2 = "_id=?";
                //修改添加参数
                String[] whereArgs2 = {String.valueOf(user_id)};
                //修改
                db.update("usertable", values, whereClause2, whereArgs2);

                User user1 = users.get(m_pos);
                user1.setName(name);
                user1.setNumber(number);
                break;
            case R.id.btn_select:
                //查询获得游标
                Cursor cursor = db.query("usertable",
                        null,
                        null, null,
                        null, null, null);

                users.clear();

                //判断游标是否为空
                while (cursor.moveToNext()){
                    //获得ID
                    int id2 = cursor.getInt(0);
                    //获得用户名
                    String username = cursor.getString(1);
                    //获得密码
                    String password = cursor.getString(2);
                    //输出用户信息
                    System.out.println( id2+":" + username + ":" + password);
                    User user2 = new User();
                    user2.setId(id2);
                    user2.setName(username);
                    user2.setNumber(password);
                    users.add(user2);
                }
                break;
        }
        allAdapter.notifyDataSetChanged();
    }

    class AllAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return users==null?0:users.size();
        }

        @Override
        public Object getItem(int position) {
            return users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){//如果为空，则加载
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_all,null);
                holder.tv_id = (TextView)convertView.findViewById(R.id.tv_id);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.tv_desc = (TextView)convertView.findViewById(R.id.tv_number);
                convertView.setTag(holder);//保存ViewHolder对象
            }else {//如果非空，直接读取保存的
                holder = (ViewHolder) convertView.getTag();
            }
            User user = users.get(position);
            holder.tv_id.setText(user.getId()+"");
            holder.tv_name.setText(user.getName());
            holder.tv_desc.setText(user.getNumber());
            return convertView;
        }
    }
    class ViewHolder{
        public TextView tv_id;
        public TextView tv_name;
        public TextView tv_desc;
    }
}
