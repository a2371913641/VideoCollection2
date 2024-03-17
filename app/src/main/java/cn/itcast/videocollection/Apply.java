package cn.itcast.videocollection;

public class Apply {
    private String name;
    private int imageId;
    public Apply(String name,int imageId){
        this.name=name;
        this.imageId=imageId;
    }
    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }
}
