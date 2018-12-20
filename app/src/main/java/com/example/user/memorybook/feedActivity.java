package com.example.user.memorybook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class feedActivity extends AppCompatActivity {

    ListView listView;
    PostClass adapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    ArrayList<String> useremailFromFB;
    ArrayList<String >userimageFromFB;
    ArrayList<String>usercommentFB;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Menüyü aktiviteye bağladığımız yer

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Menü seçilince ne olacağı
        if(item.getItemId()==R.id.add_post){ //Eğer buna tıklandıysa intent yap
            Intent intent=new Intent(getApplicationContext(),uploadActivity.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        listView=findViewById(R.id.listView);

        useremailFromFB=new ArrayList<String>();
        usercommentFB=new ArrayList<String>();
        userimageFromFB=new ArrayList<String>();

        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        adapter=new PostClass(useremailFromFB, usercommentFB,userimageFromFB,this); //İndirdiğim verileri postclass a atıyor

        listView.setAdapter(adapter);

    }
}
