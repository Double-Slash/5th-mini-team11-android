package doubleslash05.mini.team11.ui.sj;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.UiData.Base;
import doubleslash05.mini.team11.UiData.Beginner;
import doubleslash05.mini.team11.UiData.Category;
import doubleslash05.mini.team11.UiData.Text1;
import doubleslash05.mini.team11.UiData.Text2;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_CATEGORY = 0;
    private static final int ITEM_BEGINNER = 1;
    private static final int ITEM_TEXT1 = 2;
    private static final int ITEM_TEXT2 = 3;

    private Base[] list;
    private Context context;


    public CategoryAdapter(Context context){
        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater inflater =
                LayoutInflater.from(context);

        switch (viewType){
            case ITEM_CATEGORY:
                view = inflater.inflate(R.layout.item_category, parent, false);
                return new CategoryViewHolder(view);
            case ITEM_BEGINNER:
                view = inflater.inflate(R.layout.item_beginner, parent, false);
                return new BeginnerViewHolder(view);
            case ITEM_TEXT1:
                view = inflater.inflate(R.layout.item_text1, parent, false);
                return new Text1ViewHolder(view);
            case ITEM_TEXT2:
                view = inflater.inflate(R.layout.item_text2, parent, false);
                return new Text2ViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof CategoryViewHolder) {
            CategoryViewHolder c = (CategoryViewHolder) viewHolder;
            Base c1 = (Base) list[position];
            c.content.setText(c1.name);
            c.image.setImageResource(c1.imageNum);
        }
        else if (viewHolder instanceof BeginnerViewHolder){
            BeginnerViewHolder b = (BeginnerViewHolder) viewHolder;
            Base c2 = (Base)list[position];
            b.content.setText(c2.name);
            b.image.setImageResource(c2.imageNum);
            b.contentExplain.setText(c2.explain);
            b.contentTime.setText(c2.time);
            b.contentLevel.setText(c2.level);
        }
        else if (viewHolder instanceof Text1ViewHolder){
            Text1ViewHolder T1 = (Text1ViewHolder) viewHolder;
            Base c3 = (Base)list[position];
            T1.content.setText(c3.name);
            T1.contentExplain.setText(c3.explain);
        }
        else if (viewHolder instanceof Text2ViewHolder){
            Text2ViewHolder T2 = (Text2ViewHolder) viewHolder;
            Base c4 = (Base)list[position];
            T2.content.setText(c4.name);
        }

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public int getItemViewType(int position) {
        if(list[position]instanceof Category){
            return ITEM_CATEGORY;
        }else if(list[position]instanceof Beginner){
            return ITEM_BEGINNER;
        }else if(list[position]instanceof Text1){
            return ITEM_TEXT1;
        }else if(list[position]instanceof Text2){
            return ITEM_TEXT2;
        }
        return -1;
    }
    public void setData(Base[] list){
        this.list = list;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView image;

        CategoryViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.textItem);
            image = itemView.findViewById(R.id.imageItem);
        }
    }

    public static class BeginnerViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView image;
        TextView contentExplain;
        TextView contentTime;
        TextView contentLevel;

        BeginnerViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.itemTv);
            image = itemView.findViewById(R.id.itemIv);
            contentExplain = itemView.findViewById(R.id.itemTv2);
            contentTime = itemView.findViewById(R.id.itemTv3);
            contentLevel = itemView.findViewById(R.id.itemTv4);
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
}
