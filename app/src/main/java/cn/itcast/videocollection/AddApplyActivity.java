package cn.itcast.videocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddApplyActivity extends AppCompatActivity {

    ImageView imageView;
    EditText applyName;
    String FileName="apply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apply);
        Button addImage = (Button) findViewById(R.id.add_apply_image);
        imageView = (ImageView) findViewById(R.id.apply_image);
        applyName = (EditText) findViewById(R.id.apply_name);
        Button confirmAdd = (Button) findViewById(R.id.addapply_confirm_add);

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });

        confirmAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!applyName.getText().toString().equals("")) {
                    save(FileName,applyName.getText().toString());
//                    MainActivity.namelist.add(new Apply(applyName.getText().toString(),);
                    AddLineActivity.applyNameList.add(applyName.getText().toString());
                    Toast.makeText(AddApplyActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(AddApplyActivity.this,"请输入应用名称",Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button text=(Button) findViewById(R.id.open_file);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=load(FileName);
                Log.e("AddApplyActivity",data);
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

}