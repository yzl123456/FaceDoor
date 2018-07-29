package com.facedoor.slowlife;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class RMIndexActivity extends AppCompatActivity {
    private Button btn_owner;
    private Button btn_guest;
    private Button btn_worker;
    private Button btn_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rmindex);
        btn_owner= (Button) findViewById(R.id.btn_owner);
        btn_guest= (Button) findViewById(R.id.btn_guest);
        btn_worker= (Button) findViewById(R.id.btn_worker);
        btn_other= (Button) findViewById(R.id.btn_other);
        btn_owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RMIndexActivity.this,OwnerInfoActivity.class);
                startActivity(intent);

            }
        });
        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RMIndexActivity.this,GuestInfoActivity.class);
                startActivity(intent);
            }
        });
        btn_worker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RMIndexActivity.this,WorkerInfoActivity.class);
                startActivity(intent);
            }
        });
        btn_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RMIndexActivity.this,OtherInfoActivity.class);
                startActivity(intent);

            }
        });




    }
}
