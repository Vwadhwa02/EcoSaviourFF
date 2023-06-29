package com.vips.ecosaviour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bu1=findViewById(R.id.button2);
        //Button bu2=findViewById(R.id.button1);
        Button bu3=findViewById(R.id.button1);
//        bu2.setOnClickListener((View view) ->{
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLSf--lA0ENQs6OuF1rsnwfInhG4aYlU7n0Q4yIsSdruXuuzwfA/viewform?usp=sf_link"));
//            startActivity(intent);
//        });
//        bu2.setOnClickListener((View view) ->{
//            Intent view2 = new Intent(MainActivity.this,Home.class);
//            startActivity(view2);
//        });
        bu1.setOnClickListener((View view) ->{
            Intent view2 = new Intent(MainActivity.this,Home.class);
            startActivity(view2);
        });
        bu3.setOnClickListener((View view) ->{
            Intent view2 = new Intent(MainActivity.this,AI.class);
            startActivity(view2);
        });
    }
}