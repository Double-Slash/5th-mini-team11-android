package doubleslash05.mini.team11.UiData;

import androidx.annotation.IdRes;

public abstract class Base {
    public String name;
    public int imageNum;
    public String explain;
    public String time;
    public String level;
    public Base(String name,int imageNum,String explain,String time,String level){
        this.name = name;
        this.imageNum=imageNum;
        this.explain=explain;
        this.time=time;
        this.level=level;
    }
}
