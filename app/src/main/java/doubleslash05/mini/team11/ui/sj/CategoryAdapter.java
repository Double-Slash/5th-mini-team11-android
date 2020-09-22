package doubleslash05.mini.team11.ui.sj;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private Activity activity;
    private ArrayList<Category> datalist;

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category,
                parent,false);
        MyViewHolder viewHolder1 = new MyViewHolder(view);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        Category data = datalist.get(position);
        holder.tv1.setText(data.getName());
        holder.iv1.setImageResource(data.getImageNum());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        private ImageView iv1;

        public MyViewHolder(View itemview) {
            super(itemview);

            tv1 = (TextView) itemview.findViewById(R.id.textItem);
            iv1 = (ImageView) itemview.findViewById(R.id.imageItem);
        }
    }
        public CategoryAdapter(Activity activity,ArrayList<Category> datalist){
        this.activity = activity;
        this.datalist = datalist;
    }
}
