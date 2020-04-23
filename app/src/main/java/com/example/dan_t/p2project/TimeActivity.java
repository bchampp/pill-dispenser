package com.example.dan_t.p2project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.dan_t.p2project.patient.PatientCondition;
import com.example.dan_t.p2project.patient.PillTime;

public class TimeActivity extends Activity {

        private boolean finished;

        Spinner hour, minute, type;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            setContentView(R.layout.activity_time);

            String name = getIntent().getStringExtra("name");

            final PatientCondition condition = PatientCondition.forName(name);

            hour = findViewById(R.id.hourHand);

            ArrayAdapter<String> hourAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

            for(int i = 1; i < 13; i++) {
                hourAdapter.add("" + i);
            }

            hour.setAdapter(hourAdapter);

            minute = findViewById(R.id.minuteHand);

            ArrayAdapter<String> minuteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

            for(int i = 0; i < 4; i++) {
                minuteAdapter.add(i == 0 ? "00" : "" + i * 15);
            }

            minute.setAdapter(minuteAdapter);


            type = findViewById(R.id.morningType);

            ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);

            for(MorningType t : MorningType.values()) {
                typeAdapter.add("" + t.getText());
            }

            type.setAdapter(typeAdapter);

            Button submit = (Button) findViewById(R.id.submitTime);

            submit.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if(finished) {
                        return false;
                    }

                    int hour = Integer.parseInt(TimeActivity.this.hour.getSelectedItem().toString());
                    int minute = Integer.parseInt(TimeActivity.this.minute.getSelectedItem().toString());
                    MorningType type = MorningType.forText(TimeActivity.this.type.getSelectedItem().toString());

                    PillTime time = new PillTime(hour, minute, type);

                    condition.getPillTimes().add(time);
                    condition.setPills(condition.getPills() + 1);

                    Log.d("here", "size: "+ condition.getPillTimes().size());

                    Intent intent = new Intent(TimeActivity.this, CalenderActivity.class);
                    intent.putExtra("name", condition.getName());
                    startActivity(intent);
                    finished = true;
                    return true;
                }
            });

            Button cancel = (Button) findViewById(R.id.cancelTime);

            cancel.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {


                    if(finished) {
                        return false;
                    }

                    Intent intent = new Intent(TimeActivity.this, CalenderActivity.class);
                    intent.putExtra("name", condition.getName());
                    startActivity(intent);
                    finished = true;
                    return false;
                }
            });


        }
}
