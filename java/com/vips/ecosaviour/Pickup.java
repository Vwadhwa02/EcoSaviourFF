package com.vips.ecosaviour;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vips.ecosaviour.databinding.ActivityHomeBinding;
import com.vips.ecosaviour.databinding.ActivityPickupBinding;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;
import java.util.UUID;

public class Pickup extends AppCompatActivity {

    // views for button
    private Button btnChoose, btnUpload;

    // view for image view  rw
    private ImageView imageView;

    // Uri indicates, where the image will be picked from
    Uri filePath;


    // request code
    private final int PICK_IMAGE_REQUEST = 1;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("message");


    // instance for firebase storage and StorageReference

    FirebaseStorage storage;
    StorageReference storageReference;
    ActivityPickupBinding binding;
    Uri imageUri;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));
        actionBar.setBackgroundDrawable(colorDrawable);

        // initialise views
        btnChoose = findViewById(R.id.btnChoose);
        btnUpload = findViewById(R.id.btnUpload);
        imageView = findViewById(R.id.imageView);
        EditText add=findViewById(R.id.etImage);
        //progressDialog= findViewById(R.id.progressBar);


        // get the Firebase storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        // on pressing btnSelect SelectImage() is called
        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectImage();
            }
        });

        // on pressing btnUpload uploadImage() is called
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();
            }
        });
    }

    private void SelectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setType("image/*");
        //get intent to choosing content page in Android

        intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent.setAction(intent.ACTION_GET_CONTENT);
        //intent.setAction(intent.)
        //startActivity(intent);
        startActivity(intent);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
        //startActivityForResult(intent, 100);
    }

    @Override
    public void onTopResumedActivityChanged(boolean isTopResumedActivity) {
        super.onTopResumedActivityChanged(isTopResumedActivity);
    }

    private void uploadImage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading file");
        progressDialog.show();


        SimpleDateFormat f = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fil = f.format(now);
        //storageReference.putFile(Uri);
        storageReference.putFile(imageUri).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        binding.imageView.setImageURI(null);
                        Toast.makeText(Pickup.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Pickup.this, "failed to Upload", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
    }



    // Select Image method

//    public void SelectImage {
//
//        // Defining Implicit Intent to mobile gallery
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent, 100);
//
//    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data != null && data.getData() != null) {

            imageUri = data.getData();
            //binding.imgView.setImageURI(imageUri);
            //Picasso.with(this).load(imageUri).into(imageView);
            //binding.imgView.setImageURI(imageUri);
            imageView.setImageURI(imageUri);


        }
    }
}
// Get the Uri of data

// UploadImage method
//            public void uploadImage() {
//                if (filePath != null) {
//
//                    // Code for showing progressDialog while uploading
//                    ProgressDialog progressDialog
//                            = new ProgressDialog(this);
//                    progressDialog.setTitle("Uploading...");
//                    progressDialog.show();
//
//                    // Defining the child of storageReference
//                    StorageReference ref
//                            = storageReference
//                            .child(
//                                    "images/"
//                                            + UUID.randomUUID().toString());
//
//                    // adding listeners on upload
//                    // or failure of image
//                    ref.putFile(filePath)
//                            .addOnSuccessListener(
//                                    new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//                                        @Override
//                                        public void onSuccess(
//                                                UploadTask.TaskSnapshot taskSnapshot) {
//
//                                            // Image uploaded successfully
//                                            // Dismiss dialog
//                                            progressDialog.dismiss();
//                                            Toast.makeText(Pickup.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
//                                        }
//                                    })
//
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//
//                                    // Error, Image not uploaded
//                                    progressDialog.dismiss();
//                                    Toast
//                                            .makeText(Pickup.this, "Failed " + e.getMessage(),
//                                                    Toast.LENGTH_SHORT)
//                                            .show();
//                                }
//                            })
//                            .addOnProgressListener(
//                                    new OnProgressListener<UploadTask.TaskSnapshot>() {
//
//                                        // Progress Listener for loading
//                                        // percentage on the dialog box
//                                        @Override
//                                        public void onProgress(
//                                                UploadTask.TaskSnapshot taskSnapshot) {
//                                            double progress
//                                                    = (100.0
//                                                    * taskSnapshot.getBytesTransferred()
//                                                    / taskSnapshot.getTotalByteCount());
//                                            progressDialog.setMessage(
//                                                    "Uploaded "
//                                                            + (int) progress + "%");
//                                        }
//                                    });
