package cn.itcast.videocollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ApplyAdapter adapter;
    public static List<Apply> namelist;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namelist=getInstalled(this);
        recyclerView=(RecyclerView) findViewById(R.id.apply_name);
        adapter=new ApplyAdapter(this,R.layout.apply_item,namelist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Button switchView=(Button) findViewById(R.id.switch_view);
        Button add=(Button)findViewById(R.id.add_button);
        recyclerView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddLineActivity.class);
                startActivity(intent);
            }
        });

        switchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchView();
            }
        });

    }



    public void switchView(){
        if(adapter.type==0){
            adapter.setType(1);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,4));
            adapter.notifyDataSetChanged();
        }else{
            adapter.setType(0);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        adapter.notifyDataSetChanged();

    }


    private List<Apply> getInstalled(Context context) {
        List<Apply> applyList = new ArrayList<>();
        List<PackageInfo> packageInfos = context.getPackageManager().getInstalledPackages(0);

        for (PackageInfo packageInfo: packageInfos) {
            if((packageInfo.applicationInfo.flags&ApplicationInfo.FLAG_SYSTEM)==0) {
                String name = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable image = packageInfo.applicationInfo.loadIcon(getPackageManager());
                Apply apply = new Apply(name, image);
                applyList.add(apply);
            }
        }
        return applyList;
    }

}