package com.example.myapplication.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.DataController;
import com.example.myapplication.model.Semister;

import java.util.List;

public class Home_RecyclerAdapter extends RecyclerView.Adapter<Home_RecyclerAdapter.viewHolder>{
    List<Semister> mysemisterList;

    public Home_RecyclerAdapter(List<Semister> mysemisterList) {
        this.mysemisterList = mysemisterList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.semister_recycler,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Semister curentsemister=mysemisterList.get(position);
        holder.semisterNameTextView.setText(curentsemister.getSemisterName());
        holder.semisterCreditTextView.setText(curentsemister.getSemisterCredit()+"");
    }

    @Override
    public int getItemCount() {
        if(mysemisterList==null||mysemisterList.isEmpty()){
            return 0;
        }else{
             return mysemisterList.size();
        }
    }



    //Create Recycler Adapter
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView semisterNameTextView,semisterCreditTextView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            semisterNameTextView=itemView.findViewById(R.id.semister_item_re);
            semisterCreditTextView=itemView.findViewById(R.id.semister_credit_re);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            DataController.instance.getHome_fragmentInterface().onSemisterItemClic(mysemisterList.get(getAdapterPosition()));

        }
    }

}
