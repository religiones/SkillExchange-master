package com.example.aoge.skillexchange;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_launch);


        new Thread(new Runnable() {
            @Override
            public void run() {
                readFromFile(LaunchActivity.this);
            }
        }).start();
    }

    public void readFromFile(Context context) {
        String path = context.getFilesDir()+"/userinfo.txt";
        File dir = new File(path);
        FileReader reader;
        try {
            if(dir.exists()) {
                reader = new FileReader(path);
                BufferedReader breader = new BufferedReader(reader);
                String line = breader.readLine();
                if (line == null) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    breader.close();
                    finish();
                } else {
                    UserInformation.userinformation = line;
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    breader.close();
                    finish();
                }
            }else{
                dir.createNewFile();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
