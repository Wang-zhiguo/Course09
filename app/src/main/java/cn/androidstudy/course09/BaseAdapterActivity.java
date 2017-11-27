package cn.androidstudy.course09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BaseAdapterActivity extends AppCompatActivity {
    private ListView listView;
    private String[] cities={"郑州","开封","洛阳","安阳","新乡","焦作","濮阳","平顶山","南阳","许昌"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter3());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(BaseAdapterActivity.this, "Item "+position+" Clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyAdapter extends BaseAdapter{
        //决定ListView中显示的“项”的数量
        @Override
        public int getCount() {
            return 10;
        }
        //获得指定位置的对象
        @Override
        public Object getItem(int position) {
            return null;
        }
        //获得“项”的Id
        @Override
        public long getItemId(int position) {
            return 0;
        }
        //决定每个“项”的显示
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText("Item "+position);
            textView.setTextSize(20);
            textView.setPadding(20,20,20,20);
            return textView;
        }
    }
    //显示数组内容
    class MyAdapter2 extends BaseAdapter{
        //决定ListView中显示的“项”的数量
        @Override
        public int getCount() {
            return cities.length;
        }
        //获得指定位置的对象
        @Override
        public Object getItem(int position) {
            return cities[position];
        }
        //获得“项”的Id
        @Override
        public long getItemId(int position) {
            return position;
        }
        //决定每个“项”的显示
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(getApplicationContext());
            textView.setText(cities[position]);
            textView.setTextSize(20);
            textView.setPadding(20,20,20,20);
            return textView;
        }
    }

    //使用布局文件
    class MyAdapter3 extends BaseAdapter{
        //决定ListView中显示的“项”的数量
        @Override
        public int getCount() {
            return cities.length;
        }
        //获得指定位置的对象
        @Override
        public Object getItem(int position) {
            return cities[position];
        }
        //获得“项”的Id
        @Override
        public long getItemId(int position) {
            return position;
        }
        //决定每个“项”的显示
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list,null);
            TextView tv1 = (TextView) view.findViewById(R.id.textView);
            TextView tv2 = (TextView)view.findViewById(R.id.textView2);
            tv1.setText(cities[position]);
            tv2.setText("Item "+position);
            return view;
        }
    }

    //优化
    class MyAdapter4 extends BaseAdapter{
        //决定ListView中显示的“项”的数量
        @Override
        public int getCount() {
            return cities.length;
        }
        //获得指定位置的对象
        @Override
        public Object getItem(int position) {
            return cities[position];
        }
        //获得“项”的Id
        @Override
        public long getItemId(int position) {
            return position;
        }
        //决定每个“项”的显示
        //优化，就是只加载一次布局，并把布局保存起来，以后复用
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){//如果为空，则加载
                holder = new ViewHolder();
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list,null);
                holder.tv_name = (TextView) convertView.findViewById(R.id.textView);
                holder.tv_desc = (TextView)convertView.findViewById(R.id.textView2);
                convertView.setTag(holder);//保存ViewHolder对象
            }else {//如果非空，直接读取保存的
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_name.setText(cities[position]);
            holder.tv_desc.setText("Item "+position);
            return convertView;
        }
    }
    class ViewHolder{
        public TextView tv_name;
        public TextView tv_desc;
    }
}
