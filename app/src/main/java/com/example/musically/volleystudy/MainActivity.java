package com.example.musically.volleystudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myvolley.RequestQueue;
import com.example.myvolley.StringRequest;
import com.example.myvolley.Volley;


public class MainActivity extends AppCompatActivity {

	private static final String REQUEST_URL = "https://www.baidu.com/";
	private static final String TAG = "MainActivity";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.volley_test);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
				StringRequest request = new StringRequest("https://www.baidu.com/");
				requestQueue.add(request);
			}
		});
    }
}
