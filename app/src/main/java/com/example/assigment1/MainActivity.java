package com.example.assigment1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<Task> listAdapter ;
    private EditText text ;
    private EditText TaskName ;
    private EditText TaskDescription ;
    private CheckBox TaskStatus ;
    private Button ADD ;
    private Button cancel ;
    private Button EnterData ;
    private ListView ListView ;
    private ArrayList<Task> task;
     private  Task iteam;
    private  TextView Count;
    private  TextView Title;
    private  TextView status;
    private  TextView Descreption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();

        ListView = findViewById(R.id.task_list);
        ListView.setAdapter(listAdapter);
        TaskName=findViewById(R.id.TaskName);
        TaskName.setVisibility(View.GONE);
        TaskDescription=findViewById(R.id.TaskDescription);
        TaskDescription.setVisibility(View.GONE);
        TaskStatus=findViewById(R.id.TaskStatus);
        TaskStatus.setVisibility(View.GONE);
        ADD= findViewById(R.id.ADD_Button);
        cancel=findViewById(R.id.cancel);
        Title=findViewById(R.id.ViewText_Tittle);
        Title.setVisibility(View.GONE);
        status=findViewById(R.id.ViewText_Status);
        status.setVisibility(View.GONE);
        Descreption=findViewById(R.id.ViewText_Description);
        Descreption.setVisibility(View.GONE);
        cancel.setVisibility(View.GONE);


        ADD.setVisibility(View.GONE);
        EnterData=findViewById(R.id.Enter);
        Count=findViewById(R.id.Taskcount);
        Count.setText("You have  "+task.size() + " tasks");

        EnterData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ADD.setVisibility(View.VISIBLE);
                TaskDescription.setVisibility(View.VISIBLE);
                TaskName.setVisibility(View.VISIBLE);
                TaskStatus.setVisibility(View.VISIBLE);
                ListView.setVisibility(View.GONE);
                Title.setVisibility(View.VISIBLE);
                status.setVisibility(View.VISIBLE);
                Descreption.setVisibility(View.VISIBLE);
                cancel.setVisibility(View.VISIBLE);



            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ADD.setVisibility(View.GONE);
                TaskDescription.setVisibility(View.GONE);
                TaskName.setVisibility(View.GONE);
                TaskStatus.setVisibility(View.GONE);
                ListView.setVisibility(View.VISIBLE);
                Title.setVisibility(View.GONE);
                status.setVisibility(View.GONE);
                Descreption.setVisibility(View.GONE);
                cancel.setVisibility(View.GONE);




            }
        });



        ADD.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                ListView.setVisibility(View.VISIBLE);
                if(TaskName!=null || TaskDescription !=null){
                    String name= TaskName.getText().toString();
                    String discRiption=TaskDescription.getText().toString();
                    Boolean isDone=TaskStatus.isChecked();
                    if(!name.isEmpty()) {
                        Task entered = new Task(name, discRiption, isDone);
                        task.add(entered);
                        Count.setText("You have  "+task.size() + " tasks");
                        listAdapter.notifyDataSetChanged();
                        SaveData();
                    }
                    ListView.setVisibility(View.VISIBLE);
                    Title.setVisibility(View.GONE);
                    status.setVisibility(View.GONE);
                    Descreption.setVisibility(View.GONE);
                    ADD.setVisibility(View.GONE);
                    TaskDescription.setVisibility(View.GONE);
                    TaskName.setVisibility(View.GONE);
                    TaskStatus.setVisibility(View.GONE);
                    cancel.setVisibility(View.GONE);

                }



            }
        });

        listAdapter= new CustomAdaptor(this,task);


        ListView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


    }


              private void SaveData(){
                  SharedPreferences sharedPreferences= getSharedPreferences("sharedPreferences",MODE_PRIVATE);
                  SharedPreferences.Editor editor=sharedPreferences.edit();
                  Gson gson = new Gson();
                  String json =gson.toJson(task);
                  editor.putString("task list",json);
                  editor.apply();

              }
              private void loadData(){
                  SharedPreferences sharedPreferences= getSharedPreferences("sharedPreferences",MODE_PRIVATE);
                  SharedPreferences.Editor editor=sharedPreferences.edit();
                  Gson gson = new Gson();
                  String json=sharedPreferences.getString("task list",null);
                  Type type = new TypeToken<ArrayList<Task>>(){}.getType();
                  task= gson.fromJson(json,type);
                  if(task==null){
                      task=new ArrayList<>();
                  }



              }
             protected class CustomAdaptor extends ArrayAdapter<Task>{

              Context context;
              ArrayList<Task> list;

                 CustomAdaptor(Context context,ArrayList<Task> list){
                     super(context,R.layout.row,list);
                     this.context = context;
                     this.list = list;
                 }

                 public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                     LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(context.LAYOUT_INFLATER_SERVICE);
                      View row=layoutInflater.inflate(R.layout.row,parent,false);
                      CheckBox check= row.findViewById(R.id.taskStatusRow);
                      check.setChecked(list.get(position).getStatus());
                      Button delete=row.findViewById(R.id.delete);
                     TextView name=row.findViewById(R.id.TaskNameRow);
                     TextView status=row.findViewById(R.id.taskStatusRow);
                     TextView description=row.findViewById(R.id.TaskDescriptionRow);
                     status.setText(list.get(position).getTaskName());
                     //name.setText("Status : "+list.get(position).getStatus().toString());
                     description.setText("description: "+list.get(position).getDescription().toString());
                     if(list.get(position).getStatus().toString().equals("false")){
                         name.setText("Status : Not Done ");
                     }else{
                         name.setText("Status : Done ");

                     }
                     check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                     public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                         list.get(position).setStatus(check.isChecked());
                         SaveData();
                         if(list.get(position).getStatus().toString().equals("false")){
                             name.setText("Status : Not Done ");
                         }else{
                             name.setText("Status : Done ");

                         }


                     }
                     });

                     delete.setOnClickListener(new View.OnClickListener() {

                         @Override
                         public void onClick(View view) {
                             Toast.makeText(context, "task "+list.get(position).getTaskName()+" was deleted", Toast.LENGTH_SHORT).show();

                           list.remove(list.get(position))  ;
                             Count.setText("You have  "+task.size() + " tasks");
                           listAdapter.notifyDataSetChanged();
                             SaveData();

                         }
                     });


                     return row;
                 }


                 }


}