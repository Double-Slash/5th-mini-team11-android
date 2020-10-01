package doubleslash05.mini.team11.ui.sj;

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
import doubleslash05.mini.team11.model.data.Base;
import doubleslash05.mini.team11.model.data.Beginner;
import doubleslash05.mini.team11.model.data.Category;
import doubleslash05.mini.team11.model.data.Text1;
import doubleslash05.mini.team11.model.data.Text2;
import doubleslash05.mini.team11.model.data.horizontal;

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_CATEGORY = 0;
    private static final int ITEM_BEGINNER = 1;
    private static final int ITEM_TEXT1 = 2;
    private static final int ITEM_TEXT2 = 3;


    private Base[] list;
    private Context context;
    private LayoutInflater inflater;

    public CategoryAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        /*LayoutInflater inflater =
                LayoutInflater.from(context);*/

        switch (viewType) {
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
            Category c1 = (Category) list[position];
            c.content[0].setText(c1.name[0]);
            c.image[0].setImageResource(c1.imageNum[0]);
            for (int i = 0; i < 9; i++) {
                c.content[i].setText(c1.name[i]);
                c.image[i].setImageResource(c1.imageNum[i]);
            }
        } else if (viewHolder instanceof BeginnerViewHolder) {
            BeginnerViewHolder b = (BeginnerViewHolder) viewHolder;
            Beginner c2 = (Beginner) list[position];
            b.content.setText(c2.name);
            b.image.setImageResource(c2.imageNum);
            b.contentExplain.setText(c2.explain);
            b.contentTime.setText(c2.time);
            b.contentLevel.setText(c2.level);
        } else if (viewHolder instanceof Text1ViewHolder) {
            Text1ViewHolder t1 = (Text1ViewHolder) viewHolder;
            Text1 c3 = (Text1) list[position];
            t1.content.setText(c3.name1);
            t1.contentExplain.setText(c3.name2);
        } else if (viewHolder instanceof Text2ViewHolder) {
            Text2ViewHolder t2 = (Text2ViewHolder) viewHolder;
            Text2 c4 = (Text2) list[position];
            t2.content.setText(c4.name);
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (list[position] instanceof Category) {
            return ITEM_CATEGORY;
        } else if (list[position] instanceof Beginner) {
            return ITEM_BEGINNER;
        } else if (list[position] instanceof Text1) {
            return ITEM_TEXT1;
        } else if (list[position] instanceof Text2) {
            return ITEM_TEXT2;
        }
        return -1;
    }

    public void setData(Base[] list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final static int STRINGNUM = 9;
        final static int IMAGENUM = 9;
        TextView[] content = new TextView[STRINGNUM];
        ImageView[] image = new ImageView[IMAGENUM];


        CategoryViewHolder(View itemView) {
            super(itemView);
            content[0] = itemView.findViewById(R.id.textItem0);
            image[0] = itemView.findViewById(R.id.imageItem0);
            content[1] = itemView.findViewById(R.id.textItem1);
            image[1] = itemView.findViewById(R.id.imageItem1);
            content[2] = itemView.findViewById(R.id.textItem2);
            image[2] = itemView.findViewById(R.id.imageItem2);
            content[3] = itemView.findViewById(R.id.textItem3);
            image[3] = itemView.findViewById(R.id.imageItem3);
            content[4] = itemView.findViewById(R.id.textItem4);
            image[4] = itemView.findViewById(R.id.imageItem4);
            content[5] = itemView.findViewById(R.id.textItem5);
            image[5] = itemView.findViewById(R.id.imageItem5);
            content[6] = itemView.findViewById(R.id.textItem6);
            image[6] = itemView.findViewById(R.id.imageItem6);
            content[7] = itemView.findViewById(R.id.textItem7);
            image[7] = itemView.findViewById(R.id.imageItem7);
            content[8] = itemView.findViewById(R.id.textItem8);
            image[8] = itemView.findViewById(R.id.imageItem8);
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
