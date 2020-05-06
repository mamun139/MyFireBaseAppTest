package com.example.myfireapptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button savaDataButton,loadDataButton;
    EditText nameEditText,ageEditText;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference= FirebaseDatabase.getInstance().getReference("students");

        savaDataButton=findViewById(R.id.btnSaveData);
        loadDataButton=findViewById(R.id.btnLoadData);
        nameEditText=findViewById(R.id.etName);
        ageEditText=findViewById(R.id.etAge);

        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

        savaDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void saveData() {
        String name=nameEditText.getText().toString().trim();
        String age=ageEditText.getText().toString().trim();

        String key=databaseReference.push().getKey();
        Student student=new Student(name,age);

        databaseReference.child(key).setValue(student);

        Toast.makeText(getApplicationContext(),"Successful",Toast.LENGTH_LONG).show();
        nameEditText.setText("");
        ageEditText.setText("");



    }
}
