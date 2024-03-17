package cn.itcast.videocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApplyAdapter  adapter;
    public static List<Apply> namelist;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namelist=load("apply");
        adapter=new ApplyAdapter(
                MainActivity.this,R.layout.apply_item,namelist);
        listView=(ListView) findViewById(R.id.apply_name);
        Button switchView=(Button) findViewById(R.id.switch_view);
        Button add=(Button)findViewById(R.id.add_button);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data=adapter.getItem(position).getName();
                Intent intent=new Intent(MainActivity.this,LookLineActivity.class);
                intent.putExtra("ApplyName",data);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("MainActivity:","Click switchView");
                Intent intent=new Intent(MainActivity.this,AddLineActivity.class);
                startActivity(intent);
            }
        });
    }

    public List<Apply> load(String FileName){
        FileInputStream in=null;
        BufferedReader reader = null;
        ArrayList<Apply> applyList=new ArrayList<>();
        String line="";
        try {
            in = openFileInput(FileName);
            reader=new BufferedReader(new InputStreamReader(in));
            while ((line=reader.readLine())!=null){
                Log.e("MainActivity",line);
                Apply apply=new Apply(line,R.mipmap.ic_launcher);
                applyList.add(apply);
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
    protected void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();

    }
}