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

import com.royal.model.ResponseModel;
import com.royal.model.UserModel;
import com.royal.service.SessionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                  //credit : 2000

                Retrofit retrofit =   new Retrofit.Builder()
                          .baseUrl("https://diamondgamenode.onrender.com")
                          .addConverterFactory(GsonConverterFactory.create())
                          .build();

                SessionService sessionService =  retrofit.create(SessionService.class);

                  UserModel userModel = new UserModel();
                  userModel.setFirstName(firstName);
                  userModel.setEmail(email);
                  userModel.setPassword(password);
                  userModel.setLastName(lastName);
                  userModel.setCredit(32000);

                 Call<ResponseModel> call =  sessionService.signupApi(userModel);


                 call.enqueue(new Callback<ResponseModel>() {
                     @Override
                     public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            Log.i("api","success");

                         Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                         intent.putExtra("email",email);
                         intent.putExtra("password",password);
                         startActivity(intent);
                     }

                     @Override
                     public void onFailure(Call<ResponseModel> call, Throwable t) {
                         Log.i("api","fail");
                         Log.i("api",t.getMessage());

                     }
                 });


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