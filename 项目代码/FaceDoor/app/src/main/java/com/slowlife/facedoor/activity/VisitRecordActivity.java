package com.slowlife.facedoor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.slowlife.facedoor.R;

public class VisitRecordActivity extends AppCompatActivity {
    ImageView visiter;
    ImageView suspecter;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_record);
        btnBack= (ImageButton) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.tab_record_visiter, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab_record_suspecter, tabHost.getTabContentView());
        TabHost.TabSpec tab = tabHost.newTabSpec("TAB").setIndicator("访客");
        visiter= (ImageView) findViewById(R.id.iv_record_visiter);
        visiter.setImageResource(R.drawable.record_visiter);


        tab.setContent(R.id.iv_record_visiter);
        tabHost.addTab(tab);

        TabHost.TabSpec  web = tabHost.newTabSpec("TAB").setIndicator("可疑人员");
        suspecter= (ImageView) findViewById(R.id.iv_record_suspecter);
        suspecter.setImageResource(R.drawable.record_suspecter);
        web.setContent(R.id.iv_record_suspecter);
        tabHost.addTab(web);
    }
}
