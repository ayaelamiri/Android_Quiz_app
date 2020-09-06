package com.example.quiz_app;

import  android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class Register extends AppCompatActivity {
    Button bLogin;
    EditText etName,etMail,etPhone,etPassword;
    FirebaseAuth fAuth;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bLogin=(Button) findViewById(R.id.bLogin);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etPassword=(EditText)findViewById(R.id.etPassword);
        etMail=(EditText)findViewById(R.id.etMail);
        etName=(EditText)findViewById(R.id.etName);
        fAuth=FirebaseAuth.getInstance();


       /* if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();


        }
                */

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //traitement
                // (safi niya min... ila ...
                //startActivity(new Intent(Register.this,Login.class));




                String email = etMail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){

                    etMail.setError("Email is required.");
                    return;
                }

                if(TextUtils.isEmpty(password)) {

                    etPassword.setError("Password is required.");
                    return;

                }

                if(password.length() <6 ) {

                    etPassword.setError("Password must be > = 6 characters ");
                    return;

                }

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){

                            Toast.makeText(Register.this, "User created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        } else {
                            Toast.makeText(Register.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }








                    }
                });


            }
        });

    }
}







