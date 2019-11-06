package com.example.lottery;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private TextView lottery_result;
    private TextView lottery_date;
    private TextView lottery_issue;
    private TextView ssq_result;
    private TextView ssq_date;
    private TextView ssq_issue;
    public static final String lottery_url = "https://api.6vzz.com/api/cpcx.php?lottery_id=dlt";
    public static final  String ssq_url = "https://api.6vzz.com/api/cpcx.php?lottery_id=ssq";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        lotterySendRequestWithOkHttp();
        ssqSendRequestWithOkHttp();

    }
    private void initView(){
        lottery_result = findViewById(R.id.lottery_result);
        lottery_date = findViewById(R.id.lottery_date);
        lottery_issue = findViewById(R.id.lottery_issue);
        ssq_result = findViewById(R.id.ssq_result);
        ssq_date = findViewById(R.id.ssq_date);
        ssq_issue = findViewById(R.id.ssq_issue);
    }
    private void lotterySendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(lottery_url)
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                lotteryParseJsonWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void ssqSendRequestWithOkHttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(ssq_url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    ssqParseJsonWithGson(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void lotteryParseJsonWithGson(String response) {
        Gson gson = new Gson();
        final JsonRootBean jsonRootBean = gson.fromJson(response, JsonRootBean.class);
        int status = jsonRootBean.getCode();
        if (status == 200) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lottery_result.setText(jsonRootBean.getData().getResult());
                    lottery_date.setText(jsonRootBean.getData().getResulttime());
                    lottery_issue.setText(jsonRootBean.getData().getPeriodicalnum());
                }
            }).start();
        }else {
            Looper.prepare();
            Toast.makeText(this,"获取参数错误，显示结果不准确",Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }
    private void ssqParseJsonWithGson(String response) {
        Gson gson = new Gson();
        final JsonRootBean jsonRootBean = gson.fromJson(response, JsonRootBean.class);
        int status = jsonRootBean.getCode();
        if (status == 200) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ssq_result.setText(jsonRootBean.getData().getResult());
                    ssq_date.setText(jsonRootBean.getData().getResulttime());
                    ssq_issue.setText(jsonRootBean.getData().getPeriodicalnum());
                }
            }).start();
        }else {
            Looper.prepare();
            Toast.makeText(this,"获取参数错误，显示结果不准确",Toast.LENGTH_LONG).show();
            Looper.loop();
        }
    }
}
