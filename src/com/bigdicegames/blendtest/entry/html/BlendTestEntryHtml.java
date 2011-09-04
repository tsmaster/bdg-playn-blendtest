package com.bigdicegames.blendtest.entry.html;

import com.bigdicegames.blendtest.core.BlendTest;

import forplay.core.ForPlay;
import forplay.html.HtmlAssetManager;
import forplay.html.HtmlGame;
import forplay.html.HtmlPlatform;

public class BlendTestEntryHtml extends HtmlGame {
	@Override
	public void start() {
		HtmlAssetManager assets = HtmlPlatform.register().assetManager();
		assets.setPathPrefix("blendtest/");
		ForPlay.run(new BlendTest());
	}
}
