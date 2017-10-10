package com.jmoore.wpa2hasher;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import wpa2hasher.WPA2Hasher;

public class MainActivity extends AppCompatActivity{

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText ssidET=(EditText)findViewById(R.id.editText);
                EditText passET=(EditText)findViewById(R.id.editText2);
                setTextOfView(new WPA2Hasher(ssidET.getText().toString(),passET.getText().toString(),true).generate());
            }
        });
        tv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ClipboardManager clipboard=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip=ClipData.newPlainText("Clipboard",tv.getText().toString());
                clipboard.setPrimaryClip(clip);
            }
        });
    }

    private void setTextOfView(String text){
        tv.setText(text);
    }
}