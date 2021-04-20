package com.ark.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPActivity extends AppCompatActivity {

    private EditText inputMobile;
    private Button btnGetOTP;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);
        initialise();
        this.btnGetOTP.setOnClickListener(this::getOtp);
    }

    private void getOtp(View view) {
        if (inputMobile.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Enter Mobile", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        btnGetOTP.setVisibility(View.INVISIBLE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + inputMobile.getText().toString(),
                60,
                TimeUnit.SECONDS,
                SendOTPActivity.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        progressBar.setVisibility(View.GONE);
                        btnGetOTP.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        progressBar.setVisibility(View.GONE);
                        btnGetOTP.setVisibility(View.VISIBLE);
                        Toast.makeText(SendOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        progressBar.setVisibility(View.GONE);
                        btnGetOTP.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(),VerifyOTPActivity.class);
                        intent.putExtra("mobile",inputMobile.getText().toString());
                        intent.putExtra("verificationId",verificationId);
                        startActivity(intent);
                    }
                }
        );

    }

    private void initialise() {
        inputMobile = findViewById(R.id.inputMobile);
        btnGetOTP = findViewById(R.id.btnGetOtp);
        progressBar = findViewById(R.id.progressBar);
    }
}