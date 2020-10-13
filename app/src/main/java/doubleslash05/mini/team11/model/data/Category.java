package doubleslash05.mini.team11.model.data;


import java.util.ArrayList;

public  class Category extends Base{

    public ArrayList<String> item;
    public ArrayList<Integer> imageNum;

    public Category(ArrayList<String> item,ArrayList<Integer> imageNum) {
        this.item = item;
        this.imageNum=imageNum;
    }
}

//    public String name;
//    public int imageNum;
//    public ArrayList<String> itemList;
//
//
//    public Category(String name0,int imageNum0,ArrayList<String> itemList){
//
//        this.name=name0;
//        this.imageNum=imageNum0;
//
//        this.itemList = itemList;
//    }
//
//}
