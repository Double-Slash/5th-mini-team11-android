package doubleslash05.mini.team11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items =new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {      /* view를 생성할때 실행되어  viewholder를 리턴*/
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item,viewGroup,false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {    /* viewholder내용 변경*/
        Person item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView textView2;
        private TextView textView3;

        public ViewHolder(View itemVIew){
            super(itemVIew);

            textView= itemVIew.findViewById(R.id.textView);
            textView2= itemVIew.findViewById(R.id.textView2);
            textView3 = itemVIew.findViewById(R.id.textView3);

        }

        public void setItem(Person item){
            textView.setText(item.getName());
            textView2.setText(item.getrechat());
            textView3.setText(item.getDate());
        }
    }
    public void addItem(Person item){
        items.add(item);
    }
    public void setItems(ArrayList<Person>items){
        this.items = items;
    }
    public Person getItem(int position){
        return items.get(position);
    }
    public void setItem(int position,Person item){
        items.set(position,item);
    }
}
