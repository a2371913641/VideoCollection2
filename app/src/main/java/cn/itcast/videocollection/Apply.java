package cn.itcast.videocollection;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

public class Apply {
    private String name;
    private Drawable imageId;
    public Apply(String name, Drawable imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public Drawable getImageId(){
        return imageId;
    }
}
