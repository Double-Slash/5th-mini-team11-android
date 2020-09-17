package doubleslash05.mini.team11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    ArrayList<Friend> items= new ArrayList<>();


    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater2 = LayoutInflater.from(parent.getContext());
        View itemview2= inflater2.inflate(R.layout.friend_item,parent,false);
        return new ViewHolder(itemview2);  /*여기있는 ViewHolder */
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder holder, int position) {
        Friend item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2,tv3;
        ImageView iv1;

        public ViewHolder(View itemView){
            super(itemView);
            tv1 = itemView.findViewById(R.id.text1);
            tv2=itemView.findViewById(R.id.text2);
            tv3 = itemView.findViewById(R.id.text3);
            iv1 = itemView.findViewById(R.id.image1);

        }
        public void setItem(Friend item){
            tv1.setText(item.getF_name());
            tv2.setText(item.getF_music());
            tv3.setText(item.getF_state());
            iv1.setImageResource(item.getF_iv());
        }

    }
    public void addItem(Friend item){
        items.add(item);
    }
    public void setItems(ArrayList<Friend>items){
        this.items = items;
    }
    public Friend getItem(int position){
        return items.get(position);
    }
    public void setItem(int position, Friend item){
        items.set(position,item);
    }



}
