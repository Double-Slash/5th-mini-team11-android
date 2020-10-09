package doubleslash05.mini.team11.ui.sj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Base;
import doubleslash05.mini.team11.model.data.Category;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {
    ArrayList<String> list;

    public GridAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GridViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
        holder.iv.setImageResource(R.drawable.category_empty);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tv = itemView.findViewById(R.id.textItem0);
        ImageView iv = itemView.findViewById(R.id.imageItem0);

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

