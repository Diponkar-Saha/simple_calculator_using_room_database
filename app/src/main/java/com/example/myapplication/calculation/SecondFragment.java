package com.example.myapplication.calculation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.GradeRepository;
import com.example.myapplication.R;
import com.example.myapplication.model.Course;
import com.example.myapplication.model.DataController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    View view;
    DataController controller;
    EditText creditET,gpaET;
    Button addButton;
    TextView cgpaTV;
    double totalCredit=0;
    double totalCreditAndGpa=0;
    RecyclerView recyclerView;
    CourseRecyclearAdapter adapter;
    List<Course> myCourse=new ArrayList<>();
    FloatingActionButton floatingActionButton;
    GradeRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_second, container, false);
       controller=DataController.getInstance();
       repository=new GradeRepository(getActivity().getApplication());
       creditET=view.findViewById(R.id.editTextTextPersonName);
       gpaET=view.findViewById(R.id.editTextTextPersonName2);
       addButton=view.findViewById(R.id.button);
       cgpaTV=view.findViewById(R.id.textView3);
       recyclerView=view.findViewById(R.id.courseRecyclerView);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       adapter=new CourseRecyclearAdapter(myCourse);
       recyclerView.setAdapter(adapter);
       floatingActionButton=view.findViewById(R.id.saveButton);

       addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (creditET.getText().toString().equals("")||gpaET.getText().toString().equals("")){
                   Toast.makeText(getActivity(),"please insert feild",Toast.LENGTH_SHORT).show();
               }else{
                   Calculate(gpaET.getText().toString(),creditET.getText().toString());
               }

           }
       });
       floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               new AlertDialog.Builder(getActivity())
                       .setMessage("Do you want save?")
                       .setTitle("Warning")
                       .setCancelable(true)
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               //save
                               if(myCourse==null||myCourse.size()==0){
                                   Toast.makeText(getActivity(),"No Course,Add a course first",Toast.LENGTH_SHORT).show();


                               }else{
                                   repository.InsertCourse(myCourse);
                                   Toast.makeText(getActivity(),"Course Added",Toast.LENGTH_SHORT).show();


                               }

                           }
                       }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {

                   }
               }).show();

           }
       });
       Toast.makeText(getActivity(),controller.getCurrentsemister().getSemisterName(),Toast.LENGTH_SHORT).show();
        return view;
    }
    private void Calculate(String gpa, String credit){
        double gpaValue=Double.parseDouble(gpa);
        double creditValue=Double.parseDouble(credit);

        totalCreditAndGpa+=(gpaValue*creditValue);
        totalCredit+=creditValue;
        double cgpa=totalCreditAndGpa/totalCredit;
        cgpaTV.setText(String.format("CGPA: %.2f",cgpa));
        ///diponkar  sahs

        //Course course=new Course(gpaValue,creditValue,controller.getCurrentsemister().getId());
        Course course=new Course(gpaValue,creditValue, controller.getCurrentsemister().getId());
        myCourse.add(course);
        adapter.notifyDataSetChanged();


    }


}