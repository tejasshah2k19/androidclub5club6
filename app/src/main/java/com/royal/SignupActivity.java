package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

      //on button click
      btnSubmit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String firstName = edtFirstName.getText().toString();
              String lastName = edtLastName.getText().toString();
              String email = edtEmail.getText().toString();
              String password  = edtPassword.getText().toString();

              Log.i("SignupActivity",firstName);
              Log.i("SignupActivity",lastName);
              Log.i("SignupActivity",email);
              Log.i("SignupActivity",password);

          }
      });



    }
}