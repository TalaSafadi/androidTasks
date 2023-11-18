package com.example.assigment1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class First_page extends AppCompatActivity {
    private Button viewTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        viewTask= findViewById(R.id.viewTask);
        viewTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent intent=new Intent(First_page.this,MainActivity.class);
               startActivity(intent);


            }
        });


    }
}
