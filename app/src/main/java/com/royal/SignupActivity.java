package com.royal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignupActivity extends AppCompatActivity {


        EditText edtFirstName;
        EditText edtLastName;
    EditText edtEmail;
    EditText edtPassword;
    Button btnSubmit;

    TextView tvLoginLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    //bind - connect - link
      edtFirstName =   findViewById(R.id.edtSignupFirstName);
      edtLastName = findViewById(R.id.edtSignupLastName);
      edtEmail = findViewById(R.id.edtSignupEmail);
      edtPassword = findViewById(R.id.edtSignupPassword);
      btnSubmit = findViewById(R.id.btnSignupSubmit);
      tvLoginLink = findViewById(R.id.tvSignupLoginLink);

      //on button click
      btnSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String firstName = edtFirstName.getText().toString();
              String lastName = edtLastName.getText().toString();
              String email = edtEmail.getText().toString();
              String password  = edtPassword.getText().toString();

              boolean isError = false;

              if(  firstName.isEmpty()){
                  edtFirstName.setError("Please Enter FirstName");
                  isError = true;
              }

              if(lastName.isEmpty()){
                  edtLastName.setError("Please Enter LastName");
                    isError = true;
              }

              if(isError){
                  Toast.makeText(getApplicationContext(),"Please Correct Error(s)",Toast.LENGTH_LONG).show();
              }else {

                  Log.i("SignupActivity", firstName);
                  Log.i("SignupActivity", lastName);
                  Log.i("SignupActivity", email);
                  Log.i("SignupActivity", password);

                  Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                  intent.putExtra("email",email);
                  intent.putExtra("password",password);
                  startActivity(intent);
              }
          }
      });

        tvLoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}