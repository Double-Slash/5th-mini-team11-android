package doubleslash05.mini.team11.ui.sj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.model.data.Button;
import doubleslash05.mini.team11.model.data.Recipe;
import doubleslash05.mini.team11.model.data.Recipe_Base;
import doubleslash05.mini.team11.model.data.Step;
import doubleslash05.mini.team11.model.data.Tab;
import doubleslash05.mini.team11.model.data.Text3;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_RECIPE = 0;
    private static final int ITEM_STEP = 1;
    private static final int ITEM_TEXT = 2;
    private static final int ITEM_BUTTON = 3;
    private static final int ITEM_TAB = 4;

    private Context context;
    private Recipe_Base[] list;

    public RecipeAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case ITEM_RECIPE:
                v = inflater.inflate(R.layout.item_recipe, parent, false);
                return new RecipeViewHolder(v);
            case ITEM_STEP:
                v = inflater.inflate(R.layout.item_step, parent, false);
                return new StepViewHolder(v);
            case ITEM_TEXT:
                v = inflater.inflate(R.layout.item_text3, parent, false);
                return new TextViewHolder(v);
            case ITEM_BUTTON:
                v = inflater.inflate(R.layout.item_button, parent, false);
                return new ButtonViewHolder(v);
            case ITEM_TAB:
                v = inflater.inflate(R.layout.item_tab, parent, false);
                return new TabViewHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RecipeViewHolder) {
            RecipeViewHolder r = (RecipeViewHolder) holder;
            Recipe_Base r1 = (Recipe_Base) list[position];
            r.content.setText(r1.recipeExplain);
            r.content2.setText(r1.recipeName);
            r.content3.setText(r1.recipeTime);
            r.content4.setText(r1.recipeLevel);
        } else if (holder instanceof StepViewHolder) {
            StepViewHolder s = (StepViewHolder) holder;
            Recipe_Base r2 = (Recipe_Base) list[position];
            s.content.setText(r2.recipeStep);
            s.imageView.setImageResource(r2.recipeImage);
        } else if (holder instanceof TextViewHolder) {
            TextViewHolder t = (TextViewHolder) holder;
            Recipe_Base r3 = (Recipe_Base) list[position];
            t.content.setText(r3.text);
        } else if (holder instanceof ButtonViewHolder) {
            ButtonViewHolder b = (ButtonViewHolder) holder;
            Recipe_Base r4 = (Recipe_Base) list[position];
        }else if (holder instanceof  TabViewHolder){
            TabViewHolder t2 = (TabViewHolder) holder;
            Recipe_Base r5 = (Recipe_Base)list[position];
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public int getItemViewType(int position) {
        if (list[position] instanceof Recipe) {
            return ITEM_RECIPE;
        } else if (list[position] instanceof Step) {
            return ITEM_STEP;
        } else if (list[position] instanceof Text3) {
            return ITEM_TEXT;
        }else if (list[position] instanceof Button) {
            return ITEM_BUTTON;
        }else if (list[position] instanceof Tab){
            return ITEM_TAB;
        }
        return -1;
    }

    public void setData(Recipe_Base[] list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        TextView content, content2, content3, content4;

        RecipeViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.recipeTv);
            content2 = itemView.findViewById(R.id.recipeTv2);
            content3 = itemView.findViewById(R.id.recipeTv3);
            content4 = itemView.findViewById(R.id.recipeTv4);
        }
    }

    public static class StepViewHolder extends RecyclerView.ViewHolder {
        TextView content;
        ImageView imageView;

        StepViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.stepTv);
            imageView = itemView.findViewById(R.id.stepIv);
        }
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        TextView content;

        TextViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.textview3);
        }
    }

    public static class ButtonViewHolder extends RecyclerView.ViewHolder{
        ButtonViewHolder(View itemView){
            super(itemView);
        }
    }
    public static class TabViewHolder extends RecyclerView.ViewHolder{
        TabViewHolder(View itemView){
            super(itemView);
        }
    }
}
