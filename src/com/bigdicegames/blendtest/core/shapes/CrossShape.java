package com.bigdicegames.blendtest.core.shapes;

import com.bigdicegames.blendtest.core.GraphicsUtil;

import forplay.core.Canvas;
import forplay.core.Surface;

public class CrossShape extends Shape {
	public CrossShape() {
		Canvas canvas = canvasImage.canvas();
		canvas.setStrokeColor(GraphicsUtil.RGB(0xbb, 0x44, 0x44));
		canvas.setStrokeWidth(4);
		canvas.drawLine(3, 3, SHAPE_WIDTH-3, SHAPE_HEIGHT-3);
		canvas.drawLine(3, SHAPE_HEIGHT-3, SHAPE_HEIGHT-3, 3);
	}
}
