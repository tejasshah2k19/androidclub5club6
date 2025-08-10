package com.royal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    Button btnStartGame,btnScoreBoard,btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //binding
        btnStartGame = findViewById(R.id.btnHomeStartGame);
        btnScoreBoard = findViewById(R.id.btnHomeScoreBorad);
        btnLogout = findViewById(R.id.btnHomeLogout);


        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = LayoutInflater.from(HomeActivity.this);
                View dialogView = inflater.inflate(R.layout.input_amount, null);
                final EditText etAmount = dialogView.findViewById(R.id.etAmount);

                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Enter Amount")
                        .setView(dialogView)
                        .setPositiveButton("OK", (dialog, which) -> {
                            String text = etAmount.getText().toString().trim();
                            if (text.isEmpty()) {
                                Toast.makeText(HomeActivity.this, "Please enter an amount", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            try {
                                int amount = Integer.parseInt(text);
                                Intent intent = new Intent(getApplicationContext(),GamePlayActivity.class);
                                intent.putExtra("amount",amount);
                                startActivity(intent);
                            } catch (NumberFormatException e) {

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .show();


            }
        });






    }
}