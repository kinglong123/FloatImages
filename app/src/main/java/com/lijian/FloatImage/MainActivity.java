package com.lijian.FloatImage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void gotoImage(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }
    public void gotoImage2(View view) {
        Intent intent = new Intent(this, scroll.class);
        startActivity(intent);
    }
    public void gotoImage3(View view) {
        Intent intent = new Intent(this, scroll2.class);
        startActivity(intent);
    }
    public void gotoImage4(View view) {
        Intent intent = new Intent(this, ImagesActivity2.class);
        startActivity(intent);
    }
    public void gotoImage5(View view) {
        Intent intent = new Intent(this, Animator.class);
        startActivity(intent);
    }
    public void gotoImage6(View view) {
        Intent intent = new Intent(this, SuperSlidingDrawerActivity.class);
        startActivity(intent);
    }
}
