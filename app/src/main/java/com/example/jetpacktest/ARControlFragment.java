package com.example.jetpacktest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ARControlFragment extends Fragment {

	private MainActivityViewModel mViewModel;

	public static ARControlFragment newInstance() {
		return new ARControlFragment();
	}

	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_arcontrol, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);

		LiveData<String> myRandomNumber = mViewModel.getMldNumber();

		TextView tvNumber = getView().findViewById(R.id.tvNumber);
		Button bCreateNumber = getView().findViewById(R.id.bCreateNumber);

		/*
			First param in .observe(): use getViewLifecycleOwner() instead of 'this'
			LiveData obj will be scoped to the lifecycle of the fragment's viewer, instead of the fragment instance
			Which means when the viewer is destroyed, the observer gets removed regardless of whether the fragment instance is still alive.
		*/
		myRandomNumber.observe(getViewLifecycleOwner(), new Observer<String>() {
			@Override
			public void onChanged(String s) {
				tvNumber.setText(s);
			}
		});

		bCreateNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mViewModel.createNumber();
			}
		});
	}

}
