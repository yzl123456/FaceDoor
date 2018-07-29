package com.slowlife.facedoor.activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.slowlife.facedoor.R;
import com.slowlife.facedoor.adapter.FamilyGuestAdapter;
import com.slowlife.facedoor.adapter.GuestAdapter;
import com.slowlife.facedoor.domain.FamilyGuest;
import com.slowlife.facedoor.domain.Guest;

import java.util.ArrayList;
import java.util.List;

public class FamilyGuestActivity extends TabActivity {
    private List<FamilyGuest> list;
    private List<Guest> list2;
    private ListView listView;
    private ListView familyList;
    private ListView guestList;
    private TabHost mhost;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family_guest);
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
        inflater.inflate(R.layout.tab_family, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab_guest, tabHost.getTabContentView());
        TabHost.TabSpec tab = tabHost.newTabSpec("TAB").setIndicator("家人");

        familyList= (ListView) findViewById(R.id.tab_family);
        list=new ArrayList<>();
        FamilyGuest familyGuest1=new FamilyGuest("洛海","男","业主","13588290871","331003199706103114",R.drawable.face1);
        FamilyGuest familyGuest2=new FamilyGuest("于情","女","业主","13750618299","331003199706103114",R.drawable.face2);
        FamilyGuest familyGuest3=new FamilyGuest("洛雨","男","业主子女","13588290871","331003199706103114",R.drawable.face3);
        list.add(familyGuest1);
        list.add(familyGuest2);
        list.add(familyGuest3);
        FamilyGuestAdapter adapter=new FamilyGuestAdapter(FamilyGuestActivity.this,list);
        familyList.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        tab.setContent(R.id.tab_family);
        tabHost.addTab(tab);

        TabHost.TabSpec  web = tabHost.newTabSpec("TAB").setIndicator("访客");
        guestList= (ListView) findViewById(R.id.tab_guest);
        list2=new ArrayList<>();
        Guest guest1=new Guest("李雷","男","13588298734","2018-02-01","1个月","浙A.27384",R.drawable.face4,"使用中");
        Guest guest2=new Guest("王鹏","男","13750618299","2018-01-28","3个月","浙J.24875",R.drawable.face5,"待使用");
        Guest guest3=new Guest("李晓","女","13751418231","2018-01-08","1个月","浙J.44875",R.drawable.face6,"已过期");
        list2.add(guest1);
        list2.add(guest2);
        list2.add(guest3);
        GuestAdapter adapter1=new GuestAdapter(FamilyGuestActivity.this,list2);
        guestList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
        web.setContent(R.id.tab_guest);
        tabHost.addTab(web);



    }
}
