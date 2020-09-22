package doubleslash05.mini.team11.ui.sj;

public class Beginner {
    private String beginName;
    private String beginExplain;
    private int beginImage;
    private String beginTime;
    private String beginLevel;

    public Beginner(String beginName1,String beginExplain1,int beginImage1,String beginTime1,String beginLevel1){
        this.beginName=beginName1;
        this.beginExplain=beginExplain1;
        this.beginImage=beginImage1;
        this.beginTime=beginTime1;
        this.beginLevel=beginLevel1;
    }

    public String getBeginName(){
        return beginName;
    }
    public void setBeginName(String beginName1){
        this.beginName=beginName1;
    }

    public String getBeginExplain(){
        return beginExplain;
    }
    public void setBeginExplain(String beginExplain1){
        this.beginExplain=beginExplain1;
    }

    public int getBeginImage(){
        return beginImage;
    }
    public void setBeginImage(int beginImage1){
        this.beginImage=beginImage1;
    }

    public String getBeginTime(){
        return beginTime;
    }
    public void setBeginTime(String beginTime1){
        this.beginTime=beginTime1;
    }

    public String getBeginLevel(){
        return beginLevel;
    }
    public void setBeginLevel(String beginLevel1){
        this.beginLevel=beginLevel1;
    }
}
