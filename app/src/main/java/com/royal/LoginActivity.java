package com.royal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    Button btnLogin;

    TextView tvSignupLink,tvForgotPasswordLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //bind
        edtEmail = findViewById(R.id.edtLoginEmail);
        edtPassword    = findViewById(R.id.edtLoginPassword);
        btnLogin =findViewById(R.id.btnLoginSubmit);
        tvSignupLink = findViewById(R.id.tvLoginNewUser);
        tvForgotPasswordLink = findViewById(R.id.tvLoginForgotPassword);

        Intent intent =  getIntent();
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        edtEmail.setText(email);
        edtPassword.setText(password);

        tvSignupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent1);
            }
        });

        tvForgotPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent1);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email= edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                boolean isError  = false;

                if(email.isEmpty()){
                    isError = true;
                    edtEmail.setError("Please Enter Email");
                }

                if(password.isEmpty()){
                    isError = true;
                    edtPassword.setError("Please Enter Password");
                }

                if(isError == true){
                    Toast.makeText(getApplicationContext(),"Please correct error(s)",Toast.LENGTH_LONG).show();
                }else{
                    Intent intent1 = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent1);

                }
            }
        });

    }
}