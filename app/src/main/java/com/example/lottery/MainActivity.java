package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequestWithOkHttp();
    }
    private void sendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://api.6vzz.com/api/cpcx.php?lottery_id=dlt")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                ParseJsonWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void ParseJsonWithGson(String response){
        Gson gson = new Gson();
        JsonRootBean jsonRootBean = gson.fromJson(response,JsonRootBean.class);
        Log.i("好好学习", jsonRootBean.getCode()+" " +jsonRootBean.getData().getResult());
    }
}
