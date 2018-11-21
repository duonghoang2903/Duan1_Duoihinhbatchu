package com.batchu.duoihinhbatchu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class man3 extends AppCompatActivity {
    TextView tvAnswer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man3);
        tvAnswer = findViewById(R.id.tvAnswer);
        tvAnswer.setText(getIntent().getStringExtra("answer"));
    }
    public void continueQuest(View view) {
        Intent intent = new Intent(man3.this, Man2.class);
        intent.putIntegerArrayListExtra("listId", getIntent().getIntegerArrayListExtra("listId"));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
