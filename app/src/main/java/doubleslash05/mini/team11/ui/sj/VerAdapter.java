package doubleslash05.mini.team11.ui.sj;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;

public class VerAdapter extends RecyclerView.Adapter<VerAdapter.VerViewHolder> {
    ArrayList<String> list;

    public VerAdapter( ArrayList<String > list) {
        this.list = list;
    }

    @NonNull
    @Override
    public VerAdapter.VerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VerAdapter.VerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beginner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VerAdapter.VerViewHolder holder, int position) {
        holder.tvname.setText(list.get(position));
        holder.tvexplain.setText(list.get(position));
        holder.tvtime.setText(list.get(position));
        holder.tvlevel.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class VerViewHolder extends RecyclerView.ViewHolder {
        TextView tvname = itemView.findViewById(R.id.itemTv);
        TextView tvexplain = itemView.findViewById(R.id.itemTv2);
        TextView tvtime = itemView.findViewById(R.id.itemTv3);
        TextView tvlevel = itemView.findViewById(R.id.itemTv4);

        public VerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
