package doubleslash05.mini.team11;

public class Person {
    String name;
    String rechat;
    String date;


    public Person(String name, String rechat,String date) {
        this.name = name;
        this.rechat = rechat;
        this.date= date;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name=name;
    }

    public String getrechat(){
        return rechat;
    }
    public void setrechat(){
        this.rechat=rechat;
    }
    public String getDate(){
        return date;
    }
    public void setDate(){
        this.date=date;
    }

}
