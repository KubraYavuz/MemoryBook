package com.example.user.memorybook;

import android.content.Intent;
import android.icu.lang.UCharacterEnums;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signinActivity extends AppCompatActivity {
/**
    private FirebaseAuth mAuth;
    EditText emailText;
    EditText passwordText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mAuth = FirebaseAuth.getInstance();
        emailText=findViewById(R.id.emailText);
        passwordText=findViewById(R.id.passwordText);

        FirebaseUser user=mAuth.getCurrentUser();  //Eğer kayıtlı kullanıcı varsa buradan ulaşabiliriz


        if(user !=null){ //Kullanıcı varsa
            Intent intent=new Intent(getApplicationContext(),feedActivity.class);
            startActivity(intent);
        }
    }
    public void signIn(View view)
    {
        mAuth.signInWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent intent=new Intent(getApplicationContext(),feedActivity.class);
                            startActivity(intent); //Kullanıcı olutuğunda bir sonraki aktiviteye gidilecek
                        }
                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signinActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
    public void signUp(View view)
    {

        //Kullanıcı girişinin başarılı olması ve olmaması durumunda
        mAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                      if(task.isSuccessful()){
                          Toast.makeText(signinActivity.this,"User Created!",Toast.LENGTH_SHORT).show();

                          Intent intent=new Intent(getApplicationContext(),feedActivity.class);
                          startActivity(intent); //Kullanıcı olutuğunda bir sonraki aktiviteye gidilecek
                      }



                    }
                }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(signinActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }*/
}
