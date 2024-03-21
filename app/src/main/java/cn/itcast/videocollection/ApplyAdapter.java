package cn.itcast.videocollection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ViewHolder> {
    private List<Apply> mApplyList;
    Context mcontext;
    int mviewType;
    public List<ViewHolder> holders=new ArrayList<>();
    public int type=0;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View ApplyView;
        ImageView applyImage;
        TextView applyName;

        public ViewHolder(View view){
            super(view);
            ApplyView =view;
            applyImage=(ImageView)view.findViewById(R.id.apply_image);
            applyName=(TextView) view.findViewById(R.id.apply_name);
        }
    }
    public ApplyAdapter(Context context,int viewType,List<Apply> applyList){
        mApplyList=applyList;
        mcontext=context;
        mviewType=viewType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int mviewType) {
        View view;
        if(type==0){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_item,
                    parent, false);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apply_item2,
                    parent, false);
        }

        final ViewHolder holder = new ViewHolder(view);
        holder.ApplyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Apply apply = mApplyList.get(position);
                Intent intent=new Intent(mcontext,LookLineActivity.class);
                intent.putExtra("ApplyName",apply.getName());
                mcontext.startActivity(intent);
            }
        });
        Holders(holder);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Apply apply=mApplyList.get(position);
        holder.applyImage.setImageDrawable(apply.getImageId());
        holder.applyName.setText(apply.getName());
    }

    @Override
    public int getItemCount(){
        return mApplyList.size();
    }

    public void Holders(ViewHolder holder){
        holders.add(holder);
    }

    public void setType(int type) {
        this.type = type;
    }

//用来获取当前项Item是哪种类型的布局
    public int getItemViewType() {
        return type;
    }

}
