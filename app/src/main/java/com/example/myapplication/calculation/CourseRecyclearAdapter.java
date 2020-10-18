package com.example.myapplication.calculation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Course;

import java.util.List;

public class CourseRecyclearAdapter extends RecyclerView.Adapter<CourseRecyclearAdapter.viewHolder> {
List<Course> alCourse;

    public CourseRecyclearAdapter(List<Course> alCourse) {
        this.alCourse = alCourse;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courseitemlayout,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Course course=alCourse.get(position);
        holder.courseGPAtextview.setText(course.getCourseGpa()+"");
        holder.courseCredittextview.setText(course.getCourseCredit()+"");
        holder.courseNametextview.setText("Course  :" +(position+1));

    }

    @Override
    public int getItemCount() {
      if(alCourse==null||alCourse.size()==0){
          return 0;
      }else{
          return  alCourse.size();

        }
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView courseNametextview,courseCredittextview,courseGPAtextview;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            courseCredittextview=itemView.findViewById(R.id.coursecreditTextView);
            courseGPAtextview=itemView.findViewById(R.id.courseGPATextView);
            courseNametextview=itemView.findViewById(R.id.coursenameTextView);
        }
    }
}
