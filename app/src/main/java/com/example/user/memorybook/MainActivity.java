package com.example.user.memorybook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        fAuth=FirebaseAuth.getInstance();

        updateUI();
    }

    private void updateUI(){

        if(fAuth.getCurrentUser()!=null){
            Log.i("MainActivity","fAuth !=null");
        }else{
            Intent startIntent=new Intent(MainActivity.this,StartActivity.class);
            startActivity(startIntent);
            finish();
            Log.i("MainActivity","fAuth ==null");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.main_new_note_btn:
            Intent newIntent=new Intent(MainActivity.this,NewNoteActivity.class);
            startActivity(newIntent);
                break;

        }

        return true;
    }

}
