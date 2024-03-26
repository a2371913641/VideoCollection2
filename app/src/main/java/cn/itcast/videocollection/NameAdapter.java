package cn.itcast.videocollection;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends BaseAdapter {

    private List<String> mlists;
    private Context mcontext;
    static public List<ViewAndContent> views=new ArrayList<>();
    class ViewAndContent{
        public View view;
        public  int position;
        ViewAndContent(View view,int position){
            this.view=view;
            this.position=position;
        }

        public int getPosition(){
            return position;
        }

        public View getView(){
            return view;
        }

        public void setContent(int position){
            this.position=position;
        }
    }

    public View returnView(int itemId){
        View view=null;
        for(ViewAndContent viewAndContent:views){
            if(itemId==viewAndContent.getPosition()){
                view=viewAndContent.getView();
                break;
            }
        }
        return view;
    }
    public NameAdapter(Context context,List<String> lists){
//        Log.e("TextAdapter","TextAdapter()");
        mlists=lists;
        mcontext=context;
    }
    @Override
    public int getCount() {
//        Log.e("TextAdapter","getCount()");
        return mlists.size();
    }


    @Override
    public Object getItem(int position) {
//        Log.e("TextAdapter","getItem()");
        return null;
    }


    @Override
    public long getItemId(int position) {
//        Log.e("TextAdapter","getItemId()");
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("TextAdapter","getView()");
        Log.e("TextAdapter","itemid="+AddLineActivity.itemId);
        Drawable drawable1=new ColorDrawable(Color.parseColor("#ffffff"));
        Drawable drawable0=new ColorDrawable(Color.parseColor("#ECECEC"));
        if(convertView==null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.apply_name_item, parent, false);
            convertView.setBackground(drawable0);
            views.add(new ViewAndContent(convertView,position));
        }else {
            if(AddLineActivity.itemId!=position) {
                convertView.setBackground(drawable0);
            }else{
                convertView.setBackground(drawable1);
            }
        }

        TextView textView = (TextView) convertView.findViewById(R.id.apply_name_item);
        textView.setText(mlists.get(position));
        for (ViewAndContent viewAndContent:views){
            if(viewAndContent.getView()==convertView){
                viewAndContent.setContent(position);
                break;
            }
        }
        return convertView;
    }
}
