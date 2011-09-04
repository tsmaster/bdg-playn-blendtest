package com.bigdicegames.blendtest.entry.java;

import com.bigdicegames.blendtest.core.BlendTest;

import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class BlendTestEntryJava {
	public static void main(String[] args) {
		JavaAssetManager assets = JavaPlatform.register().assetManager();
		assets.setPathPrefix("src/com/bigdicegames/blendtest/resources");
		ForPlay.run(new BlendTest());
	}
}
