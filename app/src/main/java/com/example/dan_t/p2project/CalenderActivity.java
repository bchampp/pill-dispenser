package com.example.dan_t.p2project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.dan_t.p2project.patient.PatientCondition;
import com.example.dan_t.p2project.patient.PillTime;

import java.util.Iterator;

public class CalenderActivity extends Activity {

    ListView patientListView;

    private ArrayAdapter<String> patientAdapter;

    private String alertText;

    private PatientCondition condition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_calender);

        String name = getIntent().getStringExtra("name");

        condition = PatientCondition.forName(name);

        refresh(condition);

        Button addTime = (Button) findViewById(R.id.calenderTime);

        addTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(CalenderActivity.this, TimeActivity.class);
                intent.putExtra("name", condition.getName());
                startActivity(intent);
                return false;
            }
        });

        Button cancelTime = (Button) findViewById(R.id.calenderCancel);

        cancelTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(CalenderActivity.this, InfoActivity.class);
                intent.putExtra("name", condition.getName());
                startActivity(intent);
                return false;
            }
        });
    }

    public void refresh(PatientCondition condition) {
        patientAdapter = new ArrayAdapter<String>(this, R.layout.activity_bluetooth_entity);

        patientListView = (ListView) findViewById(R.id.times);

        patientListView.setAdapter(patientAdapter);


        Iterator<PillTime> iterator = condition.getPillTimes().iterator();

        while(iterator.hasNext()) {
            PillTime pillTime = iterator.next();
            patientAdapter.add("" + pillTime.getHour() + (pillTime.getMinute() < 10 ? ":0" : ":") + pillTime.getMinute() + " " + pillTime.getType().getText());
        }
    }
}
