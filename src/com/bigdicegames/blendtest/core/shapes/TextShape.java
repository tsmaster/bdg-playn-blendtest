package com.bigdicegames.blendtest.core.shapes;

import com.bigdicegames.blendtest.core.GraphicsUtil;

import forplay.core.Canvas;

public class TextShape extends Shape {
	public TextShape() {
		Canvas canvas = canvasImage.canvas();
		canvas.setFillColor(GraphicsUtil.RGB(0xff, 0xff, 0xff));
		canvas.drawText("TEXT", SHAPE_HEIGHT*.1f, SHAPE_HEIGHT*.5f);
	}
}
