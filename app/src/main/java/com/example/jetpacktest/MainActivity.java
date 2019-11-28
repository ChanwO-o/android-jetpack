package com.example.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TextView tvNumber = findViewById(R.id.tvNumber);
		getLifecycle().addObserver(new MainActivityObserver());
		MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
		String myRandomNumber = viewModel.getNumber();
		tvNumber.setText(myRandomNumber);
	}
}
