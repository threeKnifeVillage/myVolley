package com.example.musically.volleystudy;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myvolley.RequestQueue;
import com.example.myvolley.StringRequest;
import com.example.myvolley.Volley;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends Activity {

	private static final String REQUEST_URL = "https://www.baidu.com/";
	private static final String TAG = "MainActivity";

	private ImageView mImageView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.volley_test);
		mImageView = (ImageView) findViewById(R.id.avatar);
        button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//				StringRequest request = new StringRequest("https://www.baidu.com/");
//				requestQueue.add(request);
//				EventBus.getDefault().post(new MessageEvent("Hello everyone"));
//				Picasso.with(getApplicationContext()).load("https://p3-dy.bytecdn.cn/aweme/1080x1080/20a450007fe4f49ccae5f.jpeg").into(mImageView);

				// Create a very simple REST adapter which points the GitHub API.
				Retrofit retrofit = new Retrofit.Builder()
						.baseUrl(API_URL)
						.addConverterFactory(GsonConverterFactory.create())
						.build();

				// Create an instance of our GitHub API interface.
				GitHub github = retrofit.create(GitHub.class);
				// Create a call instance for looking up Retrofit contributors.
				Call<List<Contributor>> call = github.contributors("square", "retrofit");
				call.enqueue(new Callback<List<Contributor>>() {
					@Override
					public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
						List<Contributor> contributors = response.body();
						for (Contributor contributor : contributors) {
							Log.e("wanglei",contributor.login + " (" + contributor.contributions + ")");
						}
					}

					@Override public void onFailure(Call<List<Contributor>> call, Throwable t) {

					}
				});
			}
		});

    }

	@Override protected void onResume() {
		super.onResume();
	}

	@Override protected void onStart() {
		super.onStart();
		EventBus.getDefault().register(this);
	}

	@Override protected void onStop() {
		super.onStop();
		EventBus.getDefault().unregister(this);
	}

	@Subscribe
	public void onEvent(MessageEvent event) {
		Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
	}

	public static final String API_URL = "https://api.github.com";

	public static class Contributor {
		public final String login;
		public final int contributions;

		public Contributor(String login, int contributions) {
			this.login = login;
			this.contributions = contributions;
		}
	}

	public interface GitHub {
		@GET("/repos/{owner}/{repo}/contributors")
		Call<List<Contributor>> contributors(
				@Path("owner") String owner,
				@Path("repo") String repo);
	}

	public static void main(String... args) throws IOException {
		// Create a very simple REST adapter which points the GitHub API.
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(API_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		// Create an instance of our GitHub API interface.
		GitHub github = retrofit.create(GitHub.class);

		// Create a call instance for looking up Retrofit contributors.
		Call<List<Contributor>> call = github.contributors("square", "retrofit");

		// Fetch and print a list of the contributors to the library.
		List<Contributor> contributors = call.execute().body();
		for (Contributor contributor : contributors) {
			System.out.println(contributor.login + " (" + contributor.contributions + ")");
		}
	}
}
