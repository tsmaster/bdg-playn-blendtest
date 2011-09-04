package com.bigdicegames.blendtest.core.shapes;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;

import com.bigdicegames.blendtest.core.GraphicsUtil;

import forplay.core.Canvas;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class ImgTextShape extends Shape {
	public ImgTextShape(final ResourceCallback<Image> parentCallback) {
	    Image image = assetManager().getImage("images/imgtext.png");
	    image.addCallback(new ResourceCallback<Image>() {
			@Override
			public void done(Image resource) {
				Canvas canvas = canvasImage.canvas();
				canvas.drawImage(resource, 0, 0);
				parentCallback.done(resource);
			}

			@Override
			public void error(Throwable err) {
				parentCallback.error(err);
			}} );
	}
}
