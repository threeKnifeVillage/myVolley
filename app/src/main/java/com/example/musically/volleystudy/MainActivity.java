package com.example.musically.volleystudy;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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
				RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
				StringRequest request = new StringRequest(Request.Method.GET, REQUEST_URL, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.e(TAG, "response is " + response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, "response is error " + error);
					}
				});

				queue.add(request);
			}
		});

		SwipeRefreshLayout
    }

	@Override
	protected void onResume() {
		super.onResume();
	}
}
