package cn.itcast.videocollection;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SaveLoad extends AppCompatActivity {
    public void save(String FileName,String inputText) {
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{
            out=openFileOutput(FileName, Context.MODE_APPEND);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
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
