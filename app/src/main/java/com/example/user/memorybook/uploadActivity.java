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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class uploadActivity extends AppCompatActivity {

    ImageView postImage;
    EditText postCommentText;
    FirebaseDatabase firebaseDatabase; //Veri tabanı oluşturmak için
    DatabaseReference myRef; //Veri tabanı için gerekli
    private FirebaseAuth mAuth; //Kimin upload ettiğini bilmek için mauth gerekli
    private StorageReference mStoreRef; //Storage için gerekli
    Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);


        postCommentText=findViewById(R.id.postCommentText);
        postImage=findViewById(R.id.postImageView);

        //Kullanacağım tüm firebase objelerini ve değişkenlerini oluşturdum
        firebaseDatabase =FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        mAuth =FirebaseAuth.getInstance();

        mStoreRef=FirebaseStorage.getInstance().getReference();

    }

    public void upload(View view){ //Her kayıtta images klasörüne kayıt olur

        UUID uuıd=UUID.randomUUID(); //Upload edilen her resim için uuıd kullanılıyor
        final String imageName="images/"+uuıd+".jpg";

        StorageReference storageReference=mStoreRef.child(imageName);
        storageReference.putFile(selectedImage).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //download url
                    StorageReference newReferance=FirebaseStorage.getInstance().getReference(imageName);
                    newReferance.getDownloadUrl().addOnSuccessListener( new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String downloadURL=uri.toString();
                            FirebaseUser user=mAuth.getCurrentUser();
                            String userEmail=user.getEmail();

                            String userComment=postCommentText.getText().toString();
                        }
                    });
                //username
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(uploadActivity.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void selectImage(View view){


        //Eğer izin yoksa izin istenire
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
            selectedImage =data.getData(); //Uri resimlerin tutulduğu url dir.O yolu kullanarak bitmap oluşturacağız
            try {
                Bitmap bitmap=MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);
                postImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
