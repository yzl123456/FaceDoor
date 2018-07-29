package com.slowlife.facedoor.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.adapter.GuestApplyAdapter;
import com.slowlife.facedoor.domain.FamilyGuest;
import com.slowlife.facedoor.domain.GuestApply;

import java.util.ArrayList;
import java.util.List;

public class GuestApplyActivity extends AppCompatActivity {
    private List<GuestApply> list;
    private ListView applyList;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_apply);
        btnBack= (Button) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        applyList= (ListView) findViewById(R.id.apply_list);

        list=new ArrayList<>();
        GuestApply guestApply1=new GuestApply("王五","男","13485048027","2018-02-01","1个月","浙A.27384",R.drawable.face7,"待操作");
        GuestApply guestApply2=new GuestApply("钱一","女","13943228853","2018-02-04","1个月","浙A.37364",R.drawable.face8,"已同意");
        GuestApply guestApply3=new GuestApply("赵三","男","13509702637","2018-02-06","1个月","浙A.93384",R.drawable.face9,"已忽视");
        list.add(guestApply1);
        list.add(guestApply2);
        list.add(guestApply3);
        GuestApplyAdapter adapter=new GuestApplyAdapter(GuestApplyActivity.this,list);
        applyList.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }
}
