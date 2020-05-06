package com.example.icanteen3;

import androidx.appcompat.app.AppCompatActivity;
import .MainActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MineActivity extends AppCompatActivity {

    private TextView nametext;
    private TextView pointtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        nametext=(TextView)findViewById(R.id.nametext);
        pointtext=(TextView)findViewById(R.id.pointtext);
        nametext.setText(studentId);
    }
}
