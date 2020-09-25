package doubleslash05.mini.team11.UiData;

public class Beginner extends Base{
    public String name;
    public int imageNum;
    public String explain;
    public String time;
    public String level;

    public Beginner(String name,int imageNum,String explain,String time,String level){
        super(name,imageNum,explain,time,level);
        this.name=name;
        this.imageNum=imageNum;
        this.explain=explain;
        this.time=time;
        this.level=level;
    }

}
