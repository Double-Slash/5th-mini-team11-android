package doubleslash05.mini.team11.ui.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import doubleslash05.mini.team11.R;

public class TestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int VIEW_TYPE_TEST1 = 0;
    private static final int VIEW_TYPE_TEST2 = 1;

    private TestData[] list;
    private Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View v;
        switch (viewType) {
            case VIEW_TYPE_TEST1:
                v = inflater.inflate(R.layout.item_test1, parent, false);
                return new Test1ViewHolder(v);
            case VIEW_TYPE_TEST2:
                v = inflater.inflate(R.layout.item_test2, parent, false);
                return new Test2ViewHolder(v);
        }

        return null;
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof Test1ViewHolder){
            Test1ViewHolder h = (Test1ViewHolder) holder;
            TestData1 t1 = (TestData1) list[position];
            h.textView.setText(t1.text);
            h.button.setText(t1.buttonText);
        }else if(holder instanceof Test2ViewHolder){
            Test2ViewHolder h = (Test2ViewHolder) holder;
            TestData2 t2 = (TestData2) list[position];
            h.textView.setText(t2.text);
            h.imageView.setImageResource(t2.imgResource);
        }
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    @Override
    public int getItemViewType(int position) {
        if (list[position] instanceof TestData1) {
            return VIEW_TYPE_TEST1;
        } else if (list[position] instanceof TestData2) {
            return VIEW_TYPE_TEST2;
        }

        return -1;
    }

    public void setData(TestData[] list) {
        this.list = list;
        notifyDataSetChanged();
    }


    private static class Test1ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        Button button;

        Test1ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.text_test1);
            button = v.findViewById(R.id.button_test1);
        }
    }


    private static class Test2ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        Test2ViewHolder(View v){
            super(v);
            textView = v.findViewById(R.id.text_test2);
            imageView = v.findViewById(R.id.imageview_test2);
        }
    }
}
