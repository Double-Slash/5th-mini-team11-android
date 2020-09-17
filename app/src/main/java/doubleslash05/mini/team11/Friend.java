package doubleslash05.mini.team11;

import android.widget.ImageView;

public class Friend {
    private String f_name;
    private String f_state;
    private int f_iv;
    private String f_music;

    public Friend (String f_name,String f_state,int f_iv,String f_music)
    {
        this.f_name=f_name;
        this.f_state=f_state;
        this.f_iv = f_iv;
        this.f_music = f_music;
    }

    public String getF_name(){
        return f_name;
    }
    public void setF_name(){
        this.f_name=f_name;
    }

    public String getF_state(){
        return f_state;
    }
    public void setF_state(){
        this.f_state=f_state;
    }

    public int getF_iv(){
        return f_iv;
    }
    public void setF_iv(int f_iv1){
        this.f_iv=f_iv1;
    }

    public String getF_music(){
        return f_music;
    }
    public void setF_music(){
        this.f_music=f_music;
    }

}
