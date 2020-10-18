package com.example.myapplication.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.GradeRepository;
import com.example.myapplication.R;
import com.example.myapplication.model.DataController;
import com.example.myapplication.model.Semister;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Home_FragmentInterface {
    View root;
    GradeRepository repository;
    RecyclerView recyclerView;
    Home_RecyclerAdapter adapter;
    List<Semister> allSemister=new ArrayList<>();
    DataController controller;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.fragment_home, container, false);

        root=inflater.inflate(R.layout.fragment_home, container, false);
        repository=new GradeRepository(getActivity().getApplication());


        recyclerView=root.findViewById(R.id.home_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        allSemister =repository.GetAllSemister();
        adapter=new Home_RecyclerAdapter(allSemister);
        recyclerView.setAdapter(adapter);
        controller=DataController.getInstance();
        controller.setHome_fragmentInterface(this);



        FloatingActionButton fab = root.findViewById(R.id.fab);
        repository=new GradeRepository(getActivity().getApplication());
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_user_dialog);
                final EditText semisterNameEdit=dialog.findViewById(R.id.dialog_semister_edittex);
                Button createButton=dialog.findViewById(R.id.dialog_create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(semisterNameEdit.getText().toString().equals("")){
                            Toast.makeText(getActivity(),"please enter semister name",Toast.LENGTH_SHORT).show();
                        }else {
                            String semisterName = semisterNameEdit.getText().toString();
                            Toast.makeText(getActivity(),semisterName+ "  is semister name", Toast.LENGTH_SHORT).show();
                            insertSemister(semisterName);
                            dialog.dismiss();
                        }

                    }
                });
                dialog.show();
            }
        });
        return root;
    }
public void insertSemister(String semisterName){
    Semister temp=new Semister(semisterName,0.0);
    allSemister.add(temp);
    adapter.notifyDataSetChanged();
    repository.InsertSemister(temp);

}

    @Override
    public void onSemisterItemClic(Semister semister) {
        controller.setCurrentsemister(semister);
        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);

    }
}