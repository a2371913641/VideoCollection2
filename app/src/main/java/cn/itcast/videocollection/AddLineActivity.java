package cn.itcast.videocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddLineActivity extends AppCompatActivity {
    EditText line;
    ListView applyName;
    String FileName;
    SaveLoad saveLoad=new SaveLoad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line);
        applyName=(ListView) findViewById(R.id.dialog_apply_name);
        line=(EditText)findViewById(R.id.interlinkage);
        Button addLine=(Button)findViewById(R.id.confirm_add);
        Button addApply=(Button)findViewById(R.id.add_apply);

        applyName.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        ArrayList<String> applyname=new ArrayList<>();
        for(Apply apply:Data.applyList){
            applyname.add(apply.getName());
        }
        for(String name:applyname){
            Log.e("SwitchViewActivity",name.toString());
        }
        ArrayAdapter adapter1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,applyname);
        applyName.setAdapter(adapter1);
        applyName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileName=adapter1.getItem(position).toString();
                saveLoad.save(FileName,FileName);
            }
        });

        addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!line.getText().toString().equals("")) {
                    saveLoad.save(FileName,addLine.getText().toString());
                    Toast.makeText(AddLineActivity.this,"此功能不完善",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddLineActivity.this,"请输入链接",Toast.LENGTH_SHORT).show();
                }
            }
        });

        addApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddLineActivity.this,AddApplyActivity.class);
                startActivity(intent);
            }
        });

        Button open=(Button) findViewById(R.id.line_open_file);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}