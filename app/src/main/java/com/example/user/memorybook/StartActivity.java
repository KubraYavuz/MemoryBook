package com.example.user.memorybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.memorybook.user_sign.RegisterActivity;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {

    private Button btnReg,btnLog;

    private FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnReg=(Button)findViewById(R.id.start_reg_btn);
        btnLog=(Button)findViewById(R.id.start_log_btn);

        fAuth=FirebaseAuth.getInstance();

        updateUI();

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        Intent regIntent=new Intent(StartActivity.this,RegisterActivity.class);
        startActivity(regIntent);

    }
    private void login(){

    }
    private void updateUI(){
        if(fAuth.getCurrentUser()!=null){
            Log.i("StartActvity","fAuth !=null");
        }else{
            Intent startIntent=new Intent(StartActivity.this,MainActivity.class);
            startActivity(startIntent);
            finish();
            Log.i("StartActvity","fAuth ==null");
        }
    }
}
