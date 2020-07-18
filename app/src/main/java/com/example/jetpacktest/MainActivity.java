package com.example.jetpacktest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private ArFragment arFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ar_fragment);

		getLifecycle().addObserver(new MainActivityObserver());

		final MainActivityViewModel viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

		LiveData<ModelRenderable> mldFighter = viewModel.getMldFighter(this);
		mldFighter.observe(this, new Observer<ModelRenderable>() {
			@Override
			public void onChanged(ModelRenderable modelRenderable) {
				renderModel(modelRenderable);
			}
		});
	}

	private void renderModel(ModelRenderable modelRenderable) {
		TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
		node.setParent(arFragment.getArSceneView().getScene());
		node.setRenderable(modelRenderable);
	}
}
