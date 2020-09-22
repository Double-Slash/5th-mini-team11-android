package doubleslash05.mini.team11.ui.sj;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;

public class BeginnerAdapter extends RecyclerView.Adapter<BeginnerAdapter.MyViewHolder2>{
    private Activity activity2;
    private ArrayList<Beginner> datalist2;

    @NonNull
    @Override
    public BeginnerAdapter.MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beginner,
                parent,false);
        MyViewHolder2 viewHolder2 = new MyViewHolder2(view);
        return viewHolder2;
    }

    @Override
    public void onBindViewHolder(@NonNull BeginnerAdapter.MyViewHolder2 holder, int position) {
        Beginner data = datalist2.get(position);
        holder.beginTv1.setText(data.getBeginName());
        holder.beginIv1.setImageResource(data.getBeginImage());
        holder.beginTv2.setText(data.getBeginExplain());
        holder.beginTv3.setText(data.getBeginTime());
        holder.beginTv4.setText(data.getBeginLevel());
    }

    @Override
    public int getItemCount() {
        return datalist2.size();
    }


    public class MyViewHolder2 extends RecyclerView.ViewHolder{
        private TextView beginTv1,beginTv2,beginTv3,beginTv4;
        private ImageView beginIv1;

        public MyViewHolder2(View itemview2){
            super(itemview2);

            beginIv1 = (ImageView) itemview2.findViewById(R.id.itemIv);
            beginTv1 = (TextView) itemview2.findViewById(R.id.itemTv);
            beginTv2 = (TextView) itemview2.findViewById(R.id.itemTv2);
            beginTv3 = (TextView) itemview2.findViewById(R.id.itemTv3);
            beginTv4 = (TextView) itemview2.findViewById(R.id.itemTv4);

        }
    }

    public BeginnerAdapter(Activity activity2,ArrayList<Beginner> datalist2){
        this.activity2 = activity2;
        this.datalist2 = datalist2;
    }
}
