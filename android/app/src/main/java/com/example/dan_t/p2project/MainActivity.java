package com.example.dan_t.p2project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dan_t.p2project.patient.Patient;
import com.example.dan_t.p2project.patient.PatientCondition;

public class MainActivity extends Activity {

    ListView patientListView;

    private ArrayAdapter<String> patientAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);

        patientAdapter = new ArrayAdapter<String>(this, R.layout.activity_bluetooth_entity);

        patientListView = (ListView) findViewById(R.id.patients);

        patientListView.setAdapter(patientAdapter);

        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PatientCondition condition = PatientCondition.forName(patientAdapter.getItem(position));
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);

                intent.putExtra("name", condition.getName());
                startActivity(intent);
            }
        });

        for(Patient patient : Patient.values()) {
            PatientCondition condition = patient.getCondition();
            patientAdapter.add(condition.getName());
    }


    }




    public void alert(String text) {
        new AlertDialog.Builder(this)
                .setTitle("Patient: " + R.string.Patient)
                .setMessage(text)
                .setIcon(android.R.drawable.ic_dialog_alert);
    }

}
