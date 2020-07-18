package com.example.jetpacktest;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.ar.sceneform.rendering.ModelRenderable;

public class MainActivityViewModel extends ViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<String> mldNumber;
	private MutableLiveData<ModelRenderable> mldFighter;

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

	public MutableLiveData<ModelRenderable> getMldFighter(Context context) {
		Log.i(TAG, "getMldFighter()");
		if (mldFighter == null) {
			mldFighter = new MutableLiveData<>();
			createRenderableFromAsset(context, "SciFi_Fighter.sfb");
		}
		return mldFighter;
	}

	public void createRenderableFromAsset(Context context, String assetFileName) {
		ModelRenderable.builder()
				// To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
				.setSource(context, Uri.parse(assetFileName))
				.build()
				.thenAccept(renderable -> {
					Log.i(TAG, "createRenderableFromAsset()");
					mldFighter.setValue(renderable);
				})
				.exceptionally(throwable -> {
					Log.e(TAG, "Unable to load Renderable.", throwable);
					return null;
				});
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "ViewModel destroyed");
	}

//	public CompletableFuture<ModelRenderable> renderAsset(Context context, String assetFileName) {
//		ModelRenderable.builder()
//				// To load as an asset from the 'assets' folder ('src/main/assets/andy.sfb'):
//				.setSource(context, Uri.parse(assetFileName))
//				.build();
////				.thenAccept(renderable -> {
////					Log.i(TAG, "renderAsset() thenAccept()");
////					fighterRenderable[0] = renderable;
////				})
////				.exceptionally(
////						throwable -> {
////							Log.e(TAG, "Unable to load Renderable.", throwable);
////							return null;
////						});
//	}
}
