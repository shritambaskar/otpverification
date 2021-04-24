package com.ark.otpverification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Welcome to Git hub", Toast.LENGTH_SHORT).show();
        initialise();
    }

    private void initialise() {
        Toast.makeText(this, "Hello Guys This is test", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Hello this is second statement", Toast.LENGTH_SHORT).show();
    }
}