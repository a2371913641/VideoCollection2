package cn.itcast.videocollection;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class AddLineActivity extends AppCompatActivity {
    EditText line;
    ListView applyNameListView;
    public static List<String> applyNameList;
//    String FileName="apply";
    String FileName;
    NameAdapter adapter1;
    static int itemId=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line);
        applyNameListView=(ListView) findViewById(R.id.dialog_apply_name);
        line=(EditText)findViewById(R.id.interlinkage);
        Button addLine=(Button)findViewById(R.id.confirm_add);
        Button addApply=(Button)findViewById(R.id.add_apply);

        applyNameListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//        applyNameList=loadList(FileName);
        applyNameList=returnName();
        adapter1=new NameAdapter(this,applyNameList);
        applyNameListView.setAdapter(adapter1);
        applyNameListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileName=applyNameList.get(position);
                Log.e("FileName","-----------------------------------------------------------------------------");

                Drawable drawable1=new ColorDrawable(Color.parseColor("#ffffff"));
                Drawable drawable0=new ColorDrawable(Color.parseColor("#ECECEC"));

                if(itemId!=position){
                    view.setBackground(drawable1);
                    if(adapter1.returnView(itemId)!=null){
                        adapter1.returnView(itemId).setBackground(drawable0);
                    }
                    itemId=position;
                }
            }
        });

        addLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!line.getText().toString().equals("")&&FileName!=null) {
                    String Data=line.getText().toString();
                    Log.e("AddLineActivity",Data);
                    save(FileName,Data);
                    Toast.makeText(AddLineActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddLineActivity.this,"请输入链接和选择应用",Toast.LENGTH_SHORT).show();
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
//                String data=load(FileName);
//                Log.e("AddLineActivity",data);
            }
        });

    }

    public void save(String FileName,String inputText) {
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput(FileName, Context.MODE_APPEND);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText+"\n");
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(writer!=null){
                    Log.e("writer:","OK");
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load(String FileName){
        FileInputStream in=null;
        BufferedReader reader = null;
        StringBuilder content=new StringBuilder();
        String line="";
        try {
            in = openFileInput(FileName);
            reader=new BufferedReader(new InputStreamReader(in));
            while ((line=reader.readLine())!=null){
                content.append(line);
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
        return content.toString();
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

    public List<String> returnName(){
        ArrayList<String> names=new ArrayList<>();
        for(Apply apply:MainActivity.namelist){
            names.add(apply.getName());
        }
        return names;
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        adapter1.notifyDataSetChanged();
        applyNameListView.setBackgroundColor(Color.parseColor("#ECECEC"));
    }

}