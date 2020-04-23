package com.example.dan_t.p2project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan_t.p2project.patient.PatientCondition;

public class InfoActivity extends Activity {

    private String pillsText;

    PatientCondition condition;

    TextView age;

    TextView pills;

    TextView lastTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_info);

        String name = getIntent().getStringExtra("name");

        condition = PatientCondition.forName(name);

        ImageView patientImage = (ImageView) findViewById(R.id.patientImage);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), condition.getImagePath());

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) 1200) / width;
        float scaleHeight = ((float) 1400) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);

        patientImage.setImageBitmap(resizedBitmap);

        TextView text = (TextView) findViewById(R.id.patientName);

        text.setText(condition.getName());

        age = (TextView) findViewById(R.id.patientAge);

        age.setText("Age: " + condition.getAge());

        pills = (TextView) findViewById(R.id.patientPills);

        pills.setText("Pills Needed Daily: " + condition.getPills());

        if(condition.getLastTaken() != null) {
            lastTaken = (TextView) findViewById(R.id.lastTaken);
            lastTaken.setText("Last Taken: " + condition.getLastTaken().getHour() + (condition.getLastTaken().getMinute() < 10 ? ":0" : ":") + condition.getLastTaken().getMinute() + " " + condition.getLastTaken().getType().getText());
        }

        Button button = (Button) findViewById(R.id.setCalender);

        button.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(InfoActivity.this, CalenderActivity.class);
                intent.putExtra("name", condition.getName());
                startActivity(intent);
                finish();
                return false;
            }
        });

        Button cancel = (Button) findViewById(R.id.infoCancel);

        cancel.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });




        /*ImageView view = (ImageView) findViewById(R.id.background);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                InfoActivity.this.startActivity(intent);
                InfoActivity.this.finish();
                return false;
            }
        });*/
    }
}
