package raul.myplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleTask, descTask, dateTask;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleTask = findViewById(R.id.titledoes);
        descTask = findViewById(R.id.descdoes);
        dateTask = findViewById(R.id.datedoes);

        btnSaveUpdate = findViewById(R.id.btnSaveTask);
        btnDelete = findViewById(R.id.btnDelete);

        //get values from previous content
        titleTask.setText(getIntent().getStringExtra("titledoes"));
        descTask.setText(getIntent().getStringExtra("descdoes"));
        dateTask.setText(getIntent().getStringExtra("datedoes"));

        final String keykeyDoes = getIntent().getStringExtra("keydoes");

        //refference to the DB


        reference = FirebaseDatabase.getInstance().getReference().child("PlannerApp").child("Task" + keykeyDoes);


        //event for save button

        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("titledoes").setValue(titleTask.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(descTask.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(dateTask.getText().toString());
                        dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes);
                        Intent a = new Intent(EditTaskDesk.this, MainActivity.class);
                        startActivity(a);
                        Toast.makeText(getApplicationContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent a = new Intent(EditTaskDesk.this, MainActivity.class);
                            startActivity(a);
                            Toast.makeText(getApplicationContext(), "Task Deleted", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Fail to delete task", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
