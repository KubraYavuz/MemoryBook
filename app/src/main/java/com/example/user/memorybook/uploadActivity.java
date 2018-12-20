package com.example.user.memorybook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class uploadActivity extends AppCompatActivity {

    ImageView postImage;
    EditText postCommentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        postCommentText=findViewById(R.id.postCommentText);
        postImage=findViewById(R.id.postImageView);
    }

    public void upload(View view){


    }

    public void selectImage(View view){


        //Eğer izin yoksa izin istenir
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//SEçilen resim nereye kayıtlı onun yolunu verir
            startActivityForResult(intent,2);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

       if(requestCode==1){ //İzni alabildiysek
           if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
               Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//SEçilen resim nereye kayıtlı onun yolunu verir
               startActivityForResult(intent,2);
           }
       }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    //Medyadan resim seçtikten sonra
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==2 && resultCode==RESULT_OK && data !=null){
            Uri image =data.getData(); //Uri resimlerin tutulduğu url dir.O yolu kullanarak bitmap oluşturacağız
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),image);
                postImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
