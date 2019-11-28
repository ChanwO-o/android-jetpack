package com.example.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView tvNumber = findViewById(R.id.tvNumber);
		Button bCreateNumber = findViewById(R.id.bCreateNumber);

		getLifecycle().addObserver(new MainActivityObserver());

		final MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		final LiveData<String> myRandomNumber = viewModel.getMldNumber();

		myRandomNumber.observe(this, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				tvNumber.setText(s);
			}
		});

		bCreateNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewModel.createNumber();
			}
		});
	}
}
