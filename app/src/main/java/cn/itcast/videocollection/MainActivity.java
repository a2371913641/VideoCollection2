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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initApplys();
        ApplyAdapter adapter=new ApplyAdapter(
                MainActivity.this,R.layout.apply_item,Data.applyList);
        ListView listView=(ListView) findViewById(R.id.apply_name);
        Button switchView=(Button) findViewById(R.id.switch_view);
        Button add=(Button)findViewById(R.id.add_button);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Apply apply= Data.applyList.get(position);
                Toast.makeText(MainActivity.this,apply.getName(),Toast.LENGTH_SHORT).show();
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

    private void initApplys(){
        for(int i=0;i<4;i++){
            Apply douyin=new Apply("抖音",R.mipmap.ic_launcher);
            Data.applyList.add(douyin);
            Apply kuaishou=new Apply("快手",R.mipmap.ic_launcher);
            Data.applyList.add(kuaishou);
            Apply bilbil=new Apply("BilBil",R.mipmap.ic_launcher);
            Data.applyList.add(bilbil);
        }
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }
}