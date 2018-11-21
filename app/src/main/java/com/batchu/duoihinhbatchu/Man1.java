package com.batchu.duoihinhbatchu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.batchu.duoihinhbatchu.rest.RestClient;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Man1 extends AppCompatActivity {
    ArrayList<Integer> listId;
    int indexQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1);
        listId = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        indexQuest = prefs.getInt("indexQuest", 0);
        getAllIdQuest();

    }

    public void showPlay(View view) {
        Intent intent = new Intent(Man1.this, Man2.class);
        intent.putIntegerArrayListExtra("listId", listId);
        startActivity(intent);
    }

    private void getAllIdQuest() {
        final ProgressDialog dialog = new ProgressDialog(Man1.this);
        dialog.setMessage("Loading...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        for (int i = 1; i <= (indexQuest / 100) + 1; i++) {
            Call<JsonElement> callGetMedia = RestClient.getApiInterface().getAllIdQuest(100,i );

            callGetMedia.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    dialog.dismiss();
                    JsonElement json = response.body();
                    JsonArray allMedia = json.getAsJsonArray();
                    for (int i = 0; i < allMedia.size(); i++) {
                        int id = allMedia.get(i).getAsJsonObject().get("id").getAsInt();
                        listId.add(id);
                    }
                    Collections.reverse(listId);
                    Log.e("list",listId.toString());
                    Log.e("listsize",listId.size()+"");
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    dialog.dismiss();
                    Toast.makeText(Man1.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}
