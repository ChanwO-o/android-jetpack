package com.example.jetpacktest;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
	private String TAG = this.getClass().getSimpleName();
	private String number;

	public String getNumber() {
		Log.i(TAG, "getNumber()");
		if (number == null)
			createNumber();
		return number;
	}

	private void createNumber() {
		Log.i(TAG, "createNumber()");
		number = "Number: " + Math.random() * 100;
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "ViewModel destroyed");
	}
}
