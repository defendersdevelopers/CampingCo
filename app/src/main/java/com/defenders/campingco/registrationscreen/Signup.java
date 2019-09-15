package com.defenders.campingco.registrationscreen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.defenders.campingco.R;
import com.defenders.campingco.tabsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    ProgressDialog pd;
    FirebaseAuth mAuth;

    TextView emailTV,passwordTV,repasswordTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        pd = new ProgressDialog(this);
        pd.setMessage("signing up...");

        mAuth = FirebaseAuth.getInstance();
        emailTV = findViewById(R.id.emailId);
        passwordTV = findViewById(R.id.password);
        repasswordTV = findViewById(R.id.repassword);




        FrameLayout cancel =(FrameLayout)findViewById(R.id.button1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cancel = new Intent(getApplicationContext(), walkthrough.class);
                startActivity(cancel);

            }
        });


        FrameLayout save =(FrameLayout) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            registerNewUser();

            }
        });
    }

    private void registerNewUser() {
        pd.show();

        String email, password,repassword;
        email = emailTV.getText().toString();
        password = passwordTV.getText().toString();
        repassword = repasswordTV.getText().toString();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }
        if(!password.equals(repassword)){
            Toast.makeText(getApplicationContext(), "passwords not matching!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            pd.dismiss();

                            Intent intent = new Intent(Signup.this, tabsActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    }
                });
    }
}
