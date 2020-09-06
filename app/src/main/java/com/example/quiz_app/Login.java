package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //declaration
    EditText etMail , etPassword;
    Button bLogin1;
    TextView tvRegister;
    FirebaseAuth fAuth;

    public void Bouton1(View v){
        Button btn1 = (Button) findViewById(R.id.bLogin1);
        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(500);

        btn1.startAnimation(alpha);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //recupération des id
        etMail=(EditText)findViewById(R.id.etMail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        bLogin1=(Button) findViewById(R.id.bLogin1);
        tvRegister=(TextView) findViewById(R.id.tvRegister);
        fAuth=FirebaseAuth.getInstance();



        //association des listeenners

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //traitement
                // (safi niya min... ila ...
                startActivity(new Intent(Login.this,Register.class));

            }
        });

        bLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //traitement
                // (safi niya min... ila ...
                //startActivity(new Intent(Login.this,Quiz1.class));

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



                //authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if(task.isSuccessful()){

                            Toast.makeText(Login.this, "looged in successflly", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),QuizActivity.class));

                        }


                        else{


                            Toast.makeText(Login.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();



                        }

                    }
                });











            }
        });







    }
}

