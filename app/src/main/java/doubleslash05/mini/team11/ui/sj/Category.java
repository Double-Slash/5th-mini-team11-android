package doubleslash05.mini.team11.ui.sj;

public class Category {
    private String name;
    private int imageNum;

    public Category(String name1,int imageNum1){
        this.name = name1;
        this.imageNum = imageNum1;
    }
    public String getName(){
        return name;
    }
    public int getImageNum(){
        return imageNum;
    }
    public void setName(String name1){
        this.name = name1;
    }
    public void setImageNum(int imageNum1){
        this.imageNum = imageNum1;
    }
}
