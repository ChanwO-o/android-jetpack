package com.example.jetpacktest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<String> mldNumber;

	public MutableLiveData<String> getMldNumber() {
		Log.i(TAG, "getMldNumber()");
		if (mldNumber == null) {
			mldNumber = new MutableLiveData<>();
			createNumber();
		}
		return mldNumber;
	}

	public void createNumber() {
		Log.i(TAG, "createNumber()");
		mldNumber.setValue("Number: " + Math.random() * 100);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "ViewModel destroyed");
	}
}
