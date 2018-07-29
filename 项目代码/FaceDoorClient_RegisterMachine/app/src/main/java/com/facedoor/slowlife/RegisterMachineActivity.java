package com.facedoor.slowlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class RegisterMachineActivity extends AppCompatActivity {
    private Button btn_faceRegister;
    private Button btn_faceUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_machine);
        btn_faceRegister= (Button) findViewById(R.id.btn_faceRegister);
        btn_faceUpdate= (Button) findViewById(R.id.btn_faceUpdate);
        btn_faceRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterMachineActivity.this,RMIndexActivity.class);
                startActivity(intent);
            }
        });

        btn_faceUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterMachineActivity.this,RMFaceUpdateActivity.class);
                startActivity(intent);
            }
        });


    }
}
