package com.bigdicegames.blendtest.core.shapes;

import com.bigdicegames.blendtest.core.GraphicsUtil;

import forplay.core.Canvas;

public class CircleShape extends Shape {
	public CircleShape() {
		Canvas canvas = canvasImage.canvas();
		canvas.setFillColor(GraphicsUtil.RGB(0xff, 0x00, 0x00));
		canvas.fillCircle(SHAPE_WIDTH*.60f, SHAPE_WIDTH*.50f, SHAPE_WIDTH*.35f);
	}
}
