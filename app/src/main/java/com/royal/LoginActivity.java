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

import com.royal.model.ResponseModel;
import com.royal.model.UserModel;
import com.royal.service.SessionService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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


                    Retrofit retrofit =   new Retrofit.Builder()
                            .baseUrl("https://diamondgamenode.onrender.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    SessionService sessionService = retrofit.create(SessionService.class);

                    UserModel userModel = new UserModel();
                    userModel.setEmail(edtEmail.getText().toString());
                    userModel.setPassword(edtPassword.getText().toString());


                   Call<ResponseModel> call = sessionService.loginApi(userModel);

                    call.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            if(response.code() == 200){
                                //login valid
                                //redirect to home
                                //data read ?
                                String firstName =  response.body().getUser().getFirstName();
                                int credit = response.body().getUser().getCredit();

                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);

                                intent.putExtra("firstName",firstName);
                                intent.putExtra("credit",credit);

                                startActivity(intent);
                            }else{
                                //login invalid
                                Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModel> call, Throwable t) {

                        }
                    });



                    Intent intent1 = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent1);

                }
            }
        });

    }
}