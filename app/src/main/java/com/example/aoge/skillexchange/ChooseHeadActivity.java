package com.example.aoge.skillexchange;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChooseHeadActivity extends AppCompatActivity {

    private String headp;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private ImageButton btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_head);
        btn1 = (ImageButton)findViewById(R.id.btn_head1);
        btn1.setImageResource(R.drawable.man1);

        btn2 = (ImageButton)findViewById(R.id.btn_head2);
        btn2.setImageResource(R.drawable.man2);

        btn3 = (ImageButton)findViewById(R.id.btn_head3);
        btn3.setImageResource(R.drawable.man3);

        btn4 = (ImageButton)findViewById(R.id.btn_head4);
        btn4.setImageResource(R.drawable.man4);

        btn5 = (ImageButton)findViewById(R.id.btn_head5);
        btn5.setImageResource(R.drawable.man5);
    }

    public void onClick1(View view){
        headp = "1";
        Intent intent = new Intent();
        intent.putExtra("number",headp);
        setResult(0,intent);
        finish();
    }

    public void onClick2(View view){
        headp = "2";
        Intent intent = new Intent();
        intent.putExtra("number",headp);
        setResult(0,intent);
        finish();
    }

    public void onClick3(View view){
        headp = "3";
        Intent intent = new Intent();
        intent.putExtra("number",headp);
        setResult(0,intent);
        finish();
    }

    public void onClick4(View view){
        headp = "4";
        Intent intent = new Intent();
        intent.putExtra("number",headp);
        setResult(0,intent);
        finish();
    }

    public void onClick5(View view){
        headp = "5";
        Intent intent = new Intent();
        intent.putExtra("number",headp);
        setResult(0,intent);
        finish();
    }

}
