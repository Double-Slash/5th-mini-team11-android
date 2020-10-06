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
import doubleslash05.mini.team11.model.data.Beginner;
import doubleslash05.mini.team11.model.data.Category;
import doubleslash05.mini.team11.model.data.Text1;
import doubleslash05.mini.team11.model.data.Text2;
import doubleslash05.mini.team11.model.data.VerBeginner;
import doubleslash05.mini.team11.util.Log;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_CATEGORY = 0;
    private static final int ITEM_BEGINNER = 1;
    private static final int ITEM_TEXT1 = 2;
    private static final int ITEM_TEXT2 = 3;
    private static final int ITEM_VERTICAL = 4;


    ArrayList<Object> list = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM_CATEGORY) {
            return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recycler, parent, false));
        } else if (viewType == ITEM_BEGINNER) {
            return new BeginnerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_recycler, parent, false));
        } else if (viewType == ITEM_TEXT1) {
            return new Text1ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text1, parent, false));
        } else if (viewType == ITEM_TEXT2) {
            return new Text2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text2, parent, false));
        }else if (viewType == ITEM_VERTICAL) {
            return new VerticalViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_recycler, parent, false));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof CategoryViewHolder) {
            ((CategoryViewHolder) viewHolder).rv.setAdapter(new GridAdapter(((Category) list.get(position)).itemList));
//            CategoryViewHolder c = (CategoryViewHolder) viewHolder;
//            Category c1 = (Category) list.get(position);
//            c.content.setText(c1.name);
//            c.image.setImageResource(c1.imageNum);
//            c.rv.setAdapter(new GridAdapter(((Category) list.get(position)).itemList));

        } else if (viewHolder instanceof BeginnerViewHolder) {
            ((BeginnerViewHolder) viewHolder).rv2.setAdapter(new HorAdapter(((Beginner) list.get(position)).itemList));
//            BeginnerViewHolder b = (BeginnerViewHolder) viewHolder;
//            Beginner c2 = (Beginner) list.get(position);
//            b.content.setText(c2.name);
//            b.image.setImageResource(c2.imageNum);
//            b.contentExplain.setText(c2.explain);
//            b.contentTime.setText(c2.time);
//            b.contentLevel.setText(c2.level);
//            b.rv2.setAdapter(new HorAdapter(((Beginner) list.get(position)).itemList));

        } else if (viewHolder instanceof Text1ViewHolder) {
            Text1ViewHolder t1 = (Text1ViewHolder) viewHolder;
            Text1 c3 = (Text1) list.get(position);
            t1.content.setText(c3.name1);
            t1.contentExplain.setText(c3.name2);
        } else if (viewHolder instanceof Text2ViewHolder) {
            Text2ViewHolder t2 = (Text2ViewHolder) viewHolder;
            Text2 c4 = (Text2) list.get(position);
            t2.content.setText(c4.name);
        }else if (viewHolder instanceof VerticalViewHolder) {
            ((VerticalViewHolder) viewHolder).rv3.setAdapter(new VerAdapter(((VerBeginner) list.get(position)).itemList));
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof Category) {
            return ITEM_CATEGORY;
        } else if (list.get(position) instanceof Beginner) {
            return ITEM_BEGINNER;
        } else if (list.get(position) instanceof Text1) {
            return ITEM_TEXT1;
        } else if (list.get(position) instanceof Text2) {
            return ITEM_TEXT2;
        }else if (list.get(position) instanceof VerBeginner) {
            return ITEM_VERTICAL;
        }
        return -1;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView content;
        ImageView image;
        RecyclerView rv;

        CategoryViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.textItem0);
            image = itemView.findViewById(R.id.imageItem0);
            rv = itemView.findViewById(R.id.gridrecycler);
        }
    }

    public static class BeginnerViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView image;
        TextView contentExplain;
        TextView contentTime;
        TextView contentLevel;
        RecyclerView rv2;

        BeginnerViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.itemTv);
            image = itemView.findViewById(R.id.itemIv);
            contentExplain = itemView.findViewById(R.id.itemTv2);
            contentTime = itemView.findViewById(R.id.itemTv3);
            contentLevel = itemView.findViewById(R.id.itemTv4);
            rv2 = itemView.findViewById(R.id.horizonrecycler);
        }
    }

    public static class Text1ViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView contentExplain;

        Text1ViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.itemText);
            contentExplain = itemView.findViewById(R.id.itemText2);
        }
    }

    public static class Text2ViewHolder extends RecyclerView.ViewHolder {
        TextView content;

        Text2ViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.itemText3);
        }
    }
    public static class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView image;
        TextView contentExplain;
        TextView contentTime;
        TextView contentLevel;
        RecyclerView rv3;

        VerticalViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.itemTv);
            image = itemView.findViewById(R.id.itemIv);
            contentExplain = itemView.findViewById(R.id.itemTv2);
            contentTime = itemView.findViewById(R.id.itemTv3);
            contentLevel = itemView.findViewById(R.id.itemTv4);
            rv3 = itemView.findViewById(R.id.verticalrecycler);
        }
    }


}
