package com.royal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalcActivity extends AppCompatActivity {

    EditText edtN1;
    EditText edtN2;
    Button btnAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //logic

        //binding
        edtN1 = findViewById(R.id.edtCalcN1);
        edtN2 = findViewById(R.id.edtCalcN2);
        btnAns = findViewById(R.id.btnCalcAns);


        //
        btnAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btn click logic
                String n1 = edtN1.getText().toString();
                String n2 = edtN2.getText().toString();

                int  x = Integer.parseInt(n1);
                int  y = Integer.parseInt(n2);

                int ans = x+y;

                Log.i("CalcActivity",ans+"");

                Toast.makeText(getApplicationContext(),"ans = "+ans,Toast.LENGTH_LONG).show();
            }
        });

    }


}