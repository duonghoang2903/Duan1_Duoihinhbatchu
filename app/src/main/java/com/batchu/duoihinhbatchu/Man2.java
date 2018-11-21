package com.batchu.duoihinhbatchu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.batchu.duoihinhbatchu.rest.RestClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Man2 extends AppCompatActivity {
    ImageView quest, back, goiy;
    String trueAnswer;
    TableLayout request;
    int indexQuest=0;
    int ruby=0;
    TextView tvIndexQuest, tvRuby;
    ArrayList<Integer> listId;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man2);
        quest = findViewById(R.id.imageQuest);
        //Anh cau hoi
        quest.setImageResource(R.drawable.potato);
        Intent intent = getIntent();
        listId = intent.getIntegerArrayListExtra("listId");
        //Back
//        back = findViewById(R.id.imgBack);
        goiy = findViewById(R.id.imgGoiY);

        request = findViewById(R.id.request);
        tvIndexQuest = findViewById(R.id.indexQuest);
        tvRuby = findViewById(R.id.ruby);
        prefs= getSharedPreferences("data", MODE_PRIVATE);
//        prefs.edit().clear().commit();
        indexQuest = prefs.getInt("indexQuest", 0);
        ruby = prefs.getInt("ruby", 0);
        tvIndexQuest.setText(indexQuest+"");
        tvRuby.setText(ruby+"");

        Log.e("index", indexQuest + "");
        Log.e("ruby", ruby + "");
        addBox();

    }

    public void setTextForAnswer() {
        TableRow row1 = new TableRow(this);
        request.addView(row1);
        TableRow row2 = new TableRow(this);
        request.addView(row2);
        for (int i = 0; i < 7; i++) {
            TextView textRequest = new TextView(this);
            textRequest.setId(i);
            textRequest.setGravity(Gravity.CENTER);
            textRequest.setWidth(100);
            textRequest.setHeight(100);
            textRequest.setPadding(15, 10, 15, 10);
            textRequest.setTextColor(Color.WHITE);
            textRequest.setBackgroundColor(Color.RED);
            textRequest.setTextSize(18);
            textRequest.setLayoutParams(new TableRow.LayoutParams(100, 100));
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) textRequest.getLayoutParams();
            marginParams.setMargins(10, 5, 10, 10);
            row1.addView(textRequest);
        }
        for (int i = 7; i < 14; i++) {
            TextView textRequest = new TextView(this);
            textRequest.setId(i);
            textRequest.setGravity(Gravity.CENTER);
            textRequest.setWidth(100);
            textRequest.setHeight(100);
            textRequest.setPadding(15, 10, 15, 10);
            textRequest.setTextColor(Color.WHITE);
            textRequest.setBackgroundColor(Color.RED);
            textRequest.setTextSize(18);
            textRequest.setLayoutParams(new TableRow.LayoutParams(100, 100));
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams)
                    textRequest.getLayoutParams();
            marginParams.setMargins(10, 5, 10, 5);
            row2.addView(textRequest);
        }
        List<String> listText = new ArrayList<>();
        for (int i = 0; i < trueAnswer.length(); i++) {
            listText.add(String.valueOf(trueAnswer.charAt(i)));
        }

        while (listText.size() < 14) {
            Random rnd = new Random();
            char c = (char) (rnd.nextInt(26) + 'a');
            listText.add(String.valueOf(c));

        }

        Collections.shuffle(listText);
        for (int i = 0; i < 14; i++) {
            TextView textRequest = findViewById(i);
            textRequest.setText((listText.get(i) + "").toUpperCase());
        }

    }

    public void addBox() {
        final ProgressDialog dialog = new ProgressDialog(Man2.this);
        dialog.setMessage("Loading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Call<JsonElement> callGetImage = RestClient.getApiInterface().getImage(listId.get(indexQuest));

        callGetImage.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                JsonElement image = response.body();
                trueAnswer = image.getAsJsonObject().get("title").getAsJsonObject().get("rendered").getAsString();
                String linkImage =image.getAsJsonObject().get("guid").getAsJsonObject().get("rendered").getAsString();
                Glide.with(Man2.this)
                        .load(linkImage)
                        .into(quest);
                TableRow answer = findViewById(R.id.answer);
                TableRow answer2 = findViewById(R.id.answer2);
                if (trueAnswer.length() > 6) {
                    for (int i = 0; i < 6; i++) {

                        TextView text = new TextView(Man2.this);
                        text.setId(i + 16);
                        text.setTextSize(14);
                        text.setGravity(Gravity.CENTER);
                        text.setBackgroundColor(Color.BLUE);
                        text.setTextColor(Color.WHITE);
                        text.setLayoutParams(new TableRow.LayoutParams(100, 100));
                        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
                        marginParams.setMargins(10, 5, 10, 5);
                        answer.addView(text);

                    }
                    for (int i = 6; i < trueAnswer.length(); i++) {

                        TextView text = new TextView(Man2.this);
                        text.setId(i + 16);

                        text.setTextSize(14);
                        text.setGravity(Gravity.CENTER);
                        text.setBackgroundColor(Color.BLUE);
                        text.setTextColor(Color.WHITE);
                        text.setLayoutParams(new TableRow.LayoutParams(100  , 100));
                        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
                        marginParams.setMargins(10, 5, 10, 5);
                        answer2.addView(text);

                    }
                } else {
                    for (int i = 0; i < trueAnswer.length(); i++) {

                        TextView text = new TextView(Man2.this);
                        text.setId(i + 16);

                        text.setTextSize(20);
                        text.setGravity(Gravity.CENTER);
                        text.setBackgroundColor(Color.BLUE);
                        text.setTextColor(Color.WHITE);
                        text.setLayoutParams(new TableRow.LayoutParams(80, 80));
                        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) text.getLayoutParams();
                        marginParams.setMargins(10, 5, 10, 5);
                        answer.addView(text);

                    }
                }
                setTextForAnswer();
                setClick();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(Man2.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
            }
        });



    }

    int count = 0;

    public void setClick() {
        //Set click cho cau tra loi
        for (int i = 0; i < 14; i++) {
            final TextView answer = findViewById(i);
            if (answer.getVisibility() == View.VISIBLE) {
                answer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int j = 0; j < trueAnswer.length(); j++) {
                            TextView result = findViewById(j + 16);
                            if (result.getText().toString().isEmpty()) {
                                result.setText(answer.getText().toString());
                                result.setTypeface(null,Typeface.BOLD);
                                answer.setVisibility(View.INVISIBLE);
                                count++;
                                break;
                            }
                        }
                        checkAnswer();

                    }
                });
            }
        }
        // Set click cho dap an
        for (int i = 0; i < trueAnswer.length(); i++) {
            final TextView result = findViewById(i + 16);
            result.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int j = 0; j < 14; j++) {
                        TextView answer = findViewById(j);
                        if (result.getText().toString().equalsIgnoreCase(answer.getText().toString()) && answer.getVisibility() == View.INVISIBLE) {
                            answer.setVisibility(View.VISIBLE);
                            result.setText("");
                            count--;
                            break;
                        }
                    }
                }
            });
        }

        //Set click goi y
        goiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ruby>=20){
                    for (int i = 0; i < trueAnswer.length(); i++) {
                        TextView result = findViewById(i + 16);
                        int color = result.getCurrentTextColor();
                        if(color != Color.GREEN){
                            result.setText(String.valueOf(trueAnswer.charAt(i)).toUpperCase());
                            result.setTextColor(Color.GREEN);
                            result.setTypeface(null,Typeface.BOLD);
                            result.setOnClickListener(null);
                            ruby-=20;
                            prefs.edit().putInt("ruby", ruby);
                            tvRuby.setText(ruby+"");
                            count++;
                            for (int j = 0; j < 14; j++) {
                                TextView answer = findViewById(j);
                                if(answer.getText().toString().toUpperCase().equalsIgnoreCase(String.valueOf(trueAnswer.charAt(i)).toUpperCase())){
                                    answer.setVisibility(View.INVISIBLE);
                                    Log.e("answer",answer.getText().toString());
                                    Log.e("goiy",String.valueOf(trueAnswer.charAt(i)).toUpperCase());
                                    break;
                                }
                            }
                            break;
                        }
                    }

                    checkAnswer();
                }else {
                    Toast.makeText(Man2.this, "ban chua du diem",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void checkAnswer(){
        if (count == trueAnswer.length()) {
            String dapan = "";
            for (int j = 0; j < trueAnswer.length(); j++) {
                TextView result = findViewById(j + 16);
                dapan += result.getText().toString();
            }
            if (dapan.equalsIgnoreCase(trueAnswer)) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                indexQuest++;
                ruby += 5;
                Log.e("index2", indexQuest + "");
                Log.e("ruby2", ruby + "");
                editor.putInt("indexQuest", indexQuest);
                editor.putInt("ruby", ruby);
                editor.commit();
                Intent intent = new Intent(Man2.this, man3.class);
                intent.putExtra("answer", trueAnswer);
                intent.putIntegerArrayListExtra("listId", listId);
                startActivity(intent);
                //TODO khi tra loi dung
            } else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
