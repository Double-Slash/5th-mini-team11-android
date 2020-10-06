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

public class HorAdapter extends RecyclerView.Adapter<HorAdapter.HorViewHolder> {
    ArrayList<String> list;

    public HorAdapter( ArrayList<String > list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beginner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorViewHolder holder, int position) {
        holder.tvname.setText(list.get(position));
        holder.tvexplain.setText(list.get(position));
        holder.tvtime.setText(list.get(position));
        holder.tvlevel.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorViewHolder extends RecyclerView.ViewHolder {
        TextView tvname = itemView.findViewById(R.id.itemTv);
        TextView tvexplain = itemView.findViewById(R.id.itemTv2);
        TextView tvtime = itemView.findViewById(R.id.itemTv3);
        TextView tvlevel = itemView.findViewById(R.id.itemTv4);

        public HorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}