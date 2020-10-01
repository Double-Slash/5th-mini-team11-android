package doubleslash05.mini.team11.model.data;



public  class Category extends Base{
    final static int STRINGNUM=9;
    final static int IMAGENUM=9;
    public String[] name=new String[STRINGNUM];
    public int[] imageNum = new int[IMAGENUM];

    public Category(String name0,int imageNum0,String name1,int imageNum1,String name2,int imageNum2,String name3,int imageNum3,
                    String name4,int imageNum4,String name5,int imageNum5,String name6,int imageNum6,String name7,int imageNum7,
                    String name8,int imageNum8){

        this.name[0]=name0;
        this.name[1]=name1;
        this.name[2]=name2;
        this.name[3]=name3;
        this.name[4]=name4;
        this.name[5]=name5;
        this.name[6]=name6;
        this.name[7]=name7;
        this.name[8]=name8;
        this.imageNum[0]=imageNum0;
        this.imageNum[1]=imageNum1;
        this.imageNum[2]=imageNum2;
        this.imageNum[3]=imageNum3;
        this.imageNum[4]=imageNum4;
        this.imageNum[5]=imageNum5;
        this.imageNum[6]=imageNum6;
        this.imageNum[7]=imageNum7;
        this.imageNum[8]=imageNum8;

    }

}
