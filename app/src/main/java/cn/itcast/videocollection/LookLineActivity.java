package cn.itcast.videocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LookLineActivity extends AppCompatActivity {

    ArrayAdapter adapter;
    public static List<String> lineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_line);

        TextView setapplyName=(TextView) findViewById(R.id.looklineactivity_appname);
        Intent intent=getIntent();
        String ApplyName=intent.getStringExtra("ApplyName");
        setapplyName.setText(ApplyName);

        ListView lineListView=(ListView)findViewById(R.id.lookline_listview);
        lineList=loadList(ApplyName);
        adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,lineList);
        lineListView.setAdapter(adapter);
        lineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String line=adapter.getItem(position).toString();
                Toast.makeText(LookLineActivity.this,line,Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent();
                intent1.setAction(Intent.ACTION_VIEW);
                Uri content_url = Uri.parse(line);
                intent1.setData(content_url);
                startActivity(intent1);
//                jumpBrowser(line);
            }
        });

    }

    public List<String> loadList(String FileName){
        FileInputStream in=null;
        BufferedReader reader = null;
        ArrayList<String> applyList=new ArrayList<>();
        String line="";
        try {
            in = openFileInput(FileName);
            reader=new BufferedReader(new InputStreamReader(in));
            while ((line=reader.readLine())!=null){
                String name=line;
                applyList.add(name);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader!=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return applyList;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter.notifyDataSetChanged();
    }

    public  void jumpBrowser(String value)
    {
        /* 取得网页搜寻的intent */
        Intent search = new Intent(Intent.ACTION_WEB_SEARCH);
        search.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        search.putExtra(SearchManager.QUERY, value);
        final Bundle appData = getIntent().getBundleExtra(SearchManager.APP_DATA);
        if (appData != null) {
            search.putExtra(SearchManager.APP_DATA, appData);
        }
        startActivity(search);
    }

}