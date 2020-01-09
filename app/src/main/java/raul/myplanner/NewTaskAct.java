package raul.myplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlepage, addtitle, adddesc, adddate;
    EditText titletask, desctask, datetask;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer taskNum = new Random().nextInt();
    String keydoes = Integer.toString(taskNum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);


        titlepage = findViewById(R.id.titlepage);

        addtitle = findViewById(R.id.addtitle);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);

        titletask = findViewById(R.id.titledoes);
        desctask = findViewById(R.id.descdoes);
        datetask = findViewById(R.id.datedoes);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);

        //save data into the firebase
        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("PlannerApp").child("Task" + taskNum);
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("titledoes").setValue(titletask.getText().toString());
                        dataSnapshot.getRef().child("descdoes").setValue(desctask.getText().toString());
                        dataSnapshot.getRef().child("datedoes").setValue(datetask.getText().toString());
                        dataSnapshot.getRef().child("keydoes").setValue(keydoes);

                        Intent a = new Intent(NewTaskAct.this,MainActivity.class);
                        startActivity(a);
                        Toast.makeText(getApplicationContext(), "Successfully created", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(NewTaskAct.this,MainActivity.class);
                startActivity(a);
                Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
            }
        });


        //import fonts

        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ml.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/mm.ttf");

        //customize fonts
        titlepage.setTypeface(MMedium);

        addtitle.setTypeface(MLight);
        titletask.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        desctask.setTypeface(MMedium);

        adddate.setTypeface(MLight);
        datetask.setTypeface(MMedium);

        btnSaveTask.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);
    }
}


