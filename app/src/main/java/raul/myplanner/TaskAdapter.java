package raul.myplanner;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {


    Context context;
    ArrayList<MyTask> myTasks;

    public TaskAdapter(Context c, ArrayList<MyTask> p) {
        context = c;
        myTasks = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_task, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myTasks.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myTasks.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myTasks.get(i).getDatedoes());


        final String getTitleDoes = myTasks.get(i).getTitledoes();
        final String getDescDoes = myTasks.get(i).getDescdoes();
        final String getDateDoes = myTasks.get(i).getDatedoes();
        final String getKeyDoes = myTasks.get(i).getKeydoes();



        myViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent aa = new Intent(context, EditTaskDesk.class);
               aa.putExtra("titledoes", getTitleDoes);
               aa.putExtra("descdoes", getDescDoes);
               aa.putExtra("datedoes", getDateDoes);
               aa.putExtra("keydoes", getKeyDoes);
               context.startActivity(aa);
           }
        });

    }

    @Override
    public int getItemCount() {
        return myTasks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titledoes, descdoes, datedoes, keydoes;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titledoes = (TextView) itemView.findViewById(R.id.titledoes);
            descdoes = (TextView) itemView.findViewById(R.id.descdoes);
            datedoes = (TextView) itemView.findViewById(R.id.datedoes);

        }
    }
}
