package co.edureka.edurekajuly7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class AllStudentsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    //ListView listView;
    //GridView listView;

    RecyclerView recyclerView;

    ArrayList<Student> students;
    StudentsAdapter adapter;

    StudentsRecyclerAdapter recyclerAdapter;

    void initViews(){
        //listView = findViewById(R.id.listView);

        recyclerView = findViewById(R.id.recyclerView);

        students = new ArrayList<>();

        Student s1 = new Student(R.drawable.contact,"John","99999 88888");
        Student s2 = new Student(R.drawable.category,"Jennie","77777 88888");
        Student s3 = new Student(R.drawable.folder,"Jim","66666 88888");
        Student s4 = new Student(R.drawable.music,"Jack","99999 55555");
        Student s5 = new Student(R.drawable.pb,"Joe","89898 45454");

        students.add(s1); //0
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5); //4
        students.add(s3);
        students.add(s4);

        //adapter = new StudentsAdapter(this,R.layout.list_item,students);
        //listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);

        recyclerAdapter = new StudentsRecyclerAdapter(this,R.layout.list_item,students);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        //recyclerView.setLayoutManager(linearLayoutManager); // Work as ListView
        recyclerView.setLayoutManager(gridLayoutManager); // Work as GridView

        recyclerView.setAdapter(recyclerAdapter);

        // HW: Explore how to put itemClickListener on RecyclerView
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Student student = students.get(position);
        Toast.makeText(this,"You Selected: "+student.name,Toast.LENGTH_LONG).show();
    }
}
