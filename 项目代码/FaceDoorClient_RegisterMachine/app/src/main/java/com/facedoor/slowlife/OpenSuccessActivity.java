package com.facedoor.slowlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class OpenSuccessActivity extends AppCompatActivity {
    TextView name;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_success);
        Intent intent = getIntent();
        name= (TextView) findViewById(R.id.name);
        name.setText(intent.getStringExtra("name"));
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(OpenSuccessActivity.this,PermissionAcitivity.class);
                startActivity(intent1);
            }
        });
    }
}
