package doubleslash05.mini.team11.UiData;

public  class Category extends Base{
    public String name;
    public int imageNum;

    public Category(String name,int imageNum){
        super(name,imageNum,null,null,null);
        this.name=name;
        this.imageNum = imageNum;
    }

}
