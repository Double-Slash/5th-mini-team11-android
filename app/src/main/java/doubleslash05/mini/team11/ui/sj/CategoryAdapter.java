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
            c.content2.setText(c1.name2);
            c.image2.setImageResource(c1.imageNum2);
            c.content3.setText(c1.name3);
            c.image3.setImageResource(c1.imageNum3);
            c.content4.setText(c1.name4);
            c.image4.setImageResource(c1.imageNum4);
            c.content5.setText(c1.name5);
            c.image5.setImageResource(c1.imageNum5);
            c.content6.setText(c1.name6);
            c.image6.setImageResource(c1.imageNum6);
            c.content7.setText(c1.name7);
            c.image7.setImageResource(c1.imageNum7);
            c.content8.setText(c1.name8);
            c.image8.setImageResource(c1.imageNum8);
            c.content9.setText(c1.name9);
            c.image9.setImageResource(c1.imageNum9);
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
        TextView content,content2,content3,content4,content5,content6,content7,content8,content9;
        ImageView image,image2,image3,image4,image5,image6,image7,image8,image9;


        CategoryViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.textItem);
            image = itemView.findViewById(R.id.imageItem);
            content2 = itemView.findViewById(R.id.textItem2);
            image2 = itemView.findViewById(R.id.imageItem2);
            content3 = itemView.findViewById(R.id.textItem3);
            image3 = itemView.findViewById(R.id.imageItem3);
            content4 = itemView.findViewById(R.id.textItem4);
            image4 = itemView.findViewById(R.id.imageItem4);
            content5 = itemView.findViewById(R.id.textItem5);
            image5 = itemView.findViewById(R.id.imageItem5);
            content6 = itemView.findViewById(R.id.textItem6);
            image6 = itemView.findViewById(R.id.imageItem6);
            content7 = itemView.findViewById(R.id.textItem7);
            image7 = itemView.findViewById(R.id.imageItem7);
            content8 = itemView.findViewById(R.id.textItem8);
            image8 = itemView.findViewById(R.id.imageItem8);
            content9 = itemView.findViewById(R.id.textItem9);
            image9 = itemView.findViewById(R.id.imageItem9);
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
