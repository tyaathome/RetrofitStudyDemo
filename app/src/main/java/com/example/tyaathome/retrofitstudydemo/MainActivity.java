package com.example.tyaathome.retrofitstudydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tyaathome.retrofitstudydemo.model.BaseResponse;
import com.example.tyaathome.retrofitstudydemo.model.inter.OnCompleted;
import com.example.tyaathome.retrofitstudydemo.model.net.RequestVersion;
import com.example.tyaathome.retrofitstudydemo.model.net.ResponseVersion;
import com.example.tyaathome.retrofitstudydemo.service.AppService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestVersion version = new RequestVersion();
                version.soft_id = "10001";
                AppService.getInstance().startRequest(MainActivity.this, version, new OnCompleted() {
                    @Override
                    public void onCompleted(BaseResponse response) {
                        ResponseVersion responseVersion = (ResponseVersion) response;
                        TextView tv = (TextView) findViewById(R.id.tv);
                        tv.setText(responseVersion.info.des);
                    }
                });
            }
        });
    }
}
