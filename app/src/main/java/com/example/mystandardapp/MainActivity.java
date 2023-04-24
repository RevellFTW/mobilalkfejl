package com.example.mystandardapp;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class MainActivity extends AppCompatActivity {

    EditText userNameET;
    EditText passwordET;
    EditText phoneET;
    private Button buttonVerify;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameET = findViewById(R.id.editTextUserName);
        passwordET = findViewById(R.id.editTextPassword);
        mAuth = FirebaseAuth.getInstance();
        buttonVerify = findViewById(R.id.mobileLoginButton);


    }

    public void showMobileLoginDialog(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_mobile_login);
        dialog.setCancelable(true);

        final EditText editTextPhoneNumber = dialog.findViewById(R.id.editTextPhoneNumber);
        final Button buttonLogin = dialog.findViewById(R.id.buttonLogin);


        buttonLogin.setOnClickListener(v -> {
            String phone = editTextPhoneNumber.getText().toString().trim();

            if(TextUtils.isEmpty(phone)){
                Toast.makeText(MainActivity.this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone,
                    60,
                    java.util.concurrent.TimeUnit.SECONDS,
                    MainActivity.this,
                    new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        @Override
                        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            mAuth.signInWithCredential(phoneAuthCredential);
                            Toast.makeText(MainActivity.this, "Verification completed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onVerificationFailed(@NonNull FirebaseException e) {
                            Toast.makeText(MainActivity.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                            // Save the verification ID and resending token so we can use them later
                            SharedPreferences preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                            preferences.edit().putString("verificationId", verificationId).apply();
                            preferences.edit().putString("resendingToken", forceResendingToken.toString()).apply();

                            // Start the verification activity to enter the code
                            Intent intent = new Intent(MainActivity.this, ActivityVerification.class);
                            startActivity(intent);
                        }
                    }
            );
        });
        dialog.show();
    }




    public void login(View view){
        String userName = userNameET.getText().toString();
        String password = passwordET.getText().toString();

        mAuth.signInWithEmailAndPassword(userName, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        if(user != null) {
                            redirectToMain(view);
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }

                });
    }


    public void redirectToMain(View view) {
        Intent intent = new Intent(this, packageList.class);
        startActivity(intent);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void googleLogin(View view) {
    }

    public void anonymLogin(View view) {
    }
}