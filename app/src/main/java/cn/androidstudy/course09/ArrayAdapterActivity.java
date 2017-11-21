package cn.androidstudy.course09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayAdapterActivity extends AppCompatActivity {
    private ListView listView;
    private String[] cities={"郑州","开封","洛阳","安阳","新乡","焦作","濮阳","平顶山","南阳","许昌"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        listView = (ListView)findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                cities);
        listView.setAdapter(adapter);
    }
}
