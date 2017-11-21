package cn.androidstudy.course09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleAdapterActivity extends AppCompatActivity {
    private ListView listView;
    private String[] cities={"郑州","开封","洛阳","安阳","新乡","焦作","濮阳","平顶山","南阳","许昌"};
    private List<Map<String,String>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_adapter);

        listView = (ListView)findViewById(R.id.listview);
        getData();
        SimpleAdapter sa = new SimpleAdapter(this,
                data,
                R.layout.item_list,
                new String[]{"city","desc"},
                new int[]{R.id.textView,R.id.textView2});
        listView.setAdapter(sa);
    }

    private void getData() {
        data = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            Map<String,String> oneMap = new HashMap<>();
            oneMap.put("city",cities[i]);
            oneMap.put("desc","河南省的地级市。");
            data.add(oneMap);
        }
    }
}
