package com.project.zpace.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.project.zpace.R;
import com.project.zpace.database.entities.UserEntity;
import com.project.zpace.viewmodel_general.MvvmViewModel;

import java.util.List;

public class MvvmActivity extends AppCompatActivity {

    private MvvmViewModel mvvmViewModel;

    private Button btn1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm_activity);

btn1=findViewById(R.id.btn1);
        mvvmViewModel= new ViewModelProvider(MvvmActivity.this).get(MvvmViewModel.class);

        mvvmViewModel.getUserEntity().observe(this, new Observer<List<UserEntity>>() {
            @Override
            public void onChanged(List<UserEntity> userEntities) {

                //update recycler view here
                Toast.makeText(getApplicationContext(),"changed,",Toast.LENGTH_LONG).show();


            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mvvmViewModel.insert(new UserEntity(0,1));
            }
        });



    }
}
