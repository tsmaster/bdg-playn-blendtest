package com.bigdicegames.blendtest.core;

import static forplay.core.ForPlay.graphics;
import static forplay.core.ForPlay.mouse;

import java.util.ArrayList;

import com.bigdicegames.blendtest.core.shapes.CircleShape;
import com.bigdicegames.blendtest.core.shapes.CrossShape;
import com.bigdicegames.blendtest.core.shapes.ImgTextShape;
import com.bigdicegames.blendtest.core.shapes.RectangleShape;
import com.bigdicegames.blendtest.core.shapes.Shape;
import com.bigdicegames.blendtest.core.shapes.TextShape;

import forplay.core.Canvas;
import forplay.core.Canvas.Composite;
import forplay.core.CanvasImage;
import forplay.core.Game;
import forplay.core.Image;
import forplay.core.Mouse;
import forplay.core.ResourceCallback;
import forplay.core.Surface;
import forplay.core.SurfaceLayer;


public class BlendTest implements Game, Mouse.Listener{
	private static final int WIDTH = 820;
	private static final int HEIGHT = 450;
	private static final int COLOR_BACKGROUND = GraphicsUtil.RGB(0x44, 0x44, 0x44);
	private static final float SHAPE_SPACING = 1.5f;
	private static final float SOURCE_PICKER_LEFT = 0.5f * Shape.SHAPE_WIDTH;
	private static final float DEST_PICKER_LEFT = 2.5f * Shape.SHAPE_WIDTH;
	private SurfaceLayer surfaceLayer;
	private ArrayList<Shape> shapes;
	private int selectedSource;
	private int selectedDestination;

	@Override
	public void init() {
		graphics().setSize(WIDTH, HEIGHT);
		surfaceLayer = graphics().createSurfaceLayer(WIDTH, HEIGHT);
		graphics().rootLayer().add(surfaceLayer);
		
		shapes = new ArrayList<Shape>();
		shapes.add(new CircleShape());
		shapes.add(new RectangleShape());
		shapes.add(new CrossShape());
		shapes.add(new TextShape());
		shapes.add(new ImgTextShape(new ResourceCallback<Image>(){
			@Override
			public void done(Image resource) {
				draw();
			}

			@Override
			public void error(Throwable err) {
				// TODO Auto-generated method stub
				
			}}));		
		selectedSource = 0;
		selectedDestination = 1;
		mouse().setListener(this);
		draw();
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(float alpha) {
	}
	
	private void draw() {
		Surface surface = surfaceLayer.surface();
		
		surface.clear();
		surface.setFillColor(COLOR_BACKGROUND);
		surface.fillRect(0, 0, WIDTH, HEIGHT);

		// draw SRCs
		CanvasImage labelCanvasImage = graphics().createImage(120, 80);
		Canvas labelCanvas = labelCanvasImage.canvas();
		labelCanvas.setFillColor(GraphicsUtil.RGB(0xff, 0xff, 0xff));
		labelCanvas.drawText("Source", 0, 18);
		surface.drawImage(labelCanvasImage, SOURCE_PICKER_LEFT, 0);
		
		for (int i=0; i< shapes.size(); ++i){
			surface.save();
			float y = getPickerHeight(i);
			surface.translate(SOURCE_PICKER_LEFT, y);
			surface.drawImage(shapes.get(i).getImage(), 0, 0);
			if (i==selectedSource) {
				drawBox(surface);
			}
			surface.restore();
		}
		
		// draw DSTs
		labelCanvasImage = graphics().createImage(120, 80);
		labelCanvas = labelCanvasImage.canvas();
		labelCanvas.setFillColor(GraphicsUtil.RGB(0xff, 0xff, 0xff));
		labelCanvas.drawText("Destination", 0, 18);
		surface.drawImage(labelCanvasImage, DEST_PICKER_LEFT, 0);
		
		for (int i=0; i< shapes.size(); ++i){
			surface.save();
			float h = getPickerHeight(i);
			surface.translate(DEST_PICKER_LEFT, h);
			surface.drawImage(shapes.get(i).getImage(), 0, 0);
			if (i==selectedDestination) {
				drawBox(surface);
			}
			surface.restore();
		}
		
		Composite[] composites = Canvas.Composite.values();
		for (int i=0; i < composites.length; ++i) {
			surface.save();
			int x = i % 2;
			int y = i / 2;
			surface.translate(400 + x * 200, 
					10 + y * Shape.SHAPE_HEIGHT * SHAPE_SPACING);
			
			CanvasImage canvasImage = graphics().createImage((int)Shape.SHAPE_WIDTH, (int)Shape.SHAPE_HEIGHT);
			Canvas canvas = canvasImage.canvas();
			canvas.drawImage(shapes.get(selectedDestination).getImage(), 0, 0);
			canvas.setCompositeOperation(composites[i]);
			canvas.drawImage(shapes.get(selectedSource).getImage(), 0, 0);
			surface.drawImage(canvasImage, 0, 0);
			
			labelCanvasImage = graphics().createImage(120, 80);
			labelCanvas = labelCanvasImage.canvas();
			labelCanvas.setFillColor(GraphicsUtil.RGB(0xff, 0xff, 0xff));
			labelCanvas.drawText(composites[i].name(), 0, 40);
			surface.drawImage(labelCanvasImage, 1.5f * Shape.SHAPE_WIDTH, 0);
			surface.restore();
		}
		
		labelCanvasImage = graphics().createImage(400, 80);
		labelCanvas = labelCanvasImage.canvas();
		labelCanvas.setFillColor(GraphicsUtil.RGB(0xff, 0xff, 0xff));
		labelCanvas.drawText(graphics().toString(), 0, 18);
		surface.drawImage(labelCanvasImage, SOURCE_PICKER_LEFT, HEIGHT-30);
	}

	private float getPickerHeight(int i) {
		 return (i+0.5f) * Shape.SHAPE_HEIGHT * SHAPE_SPACING;
	}

	private void drawBox(Surface surface) {
		surface.setFillColor(GraphicsUtil.RGB(0, 0, 0xbb));
		
		float outset = 5.0f;
		
		surface.drawLine(-outset, -outset, -outset, Shape.SHAPE_HEIGHT+outset, 2);
		
		surface.drawLine(-outset, Shape.SHAPE_HEIGHT+outset, Shape.SHAPE_WIDTH+outset, Shape.SHAPE_HEIGHT+outset, 2);
		surface.drawLine(Shape.SHAPE_WIDTH+outset, Shape.SHAPE_HEIGHT+outset, Shape.SHAPE_WIDTH+outset, -outset, 2);
		surface.drawLine(Shape.SHAPE_WIDTH+outset, -outset, -outset, -outset, 2);
	}

	@Override
	public int updateRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void onMouseDown(float x, float y, int button) {
		boolean pickingSource = false;
		boolean pickingDestination = false;
		
		if (x>= SOURCE_PICKER_LEFT && x <= SOURCE_PICKER_LEFT+Shape.SHAPE_WIDTH) {
			pickingSource = true;
		}
		else if (x>= DEST_PICKER_LEFT && x <= DEST_PICKER_LEFT+Shape.SHAPE_WIDTH) {
			pickingDestination = true;
		}
		else {
			return;
		}
		
		for (int i=0;i<shapes.size(); ++i) {
			float h = getPickerHeight(i);
			if (y >= h && y <= h + Shape.SHAPE_HEIGHT) {
				if (pickingSource) {
					selectedSource = i;
					draw();
					return;
				} else {
					selectedDestination = i;
					draw();
					return;
				}
			}
		}
	}

	@Override
	public void onMouseUp(float x, float y, int button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelScroll(float velocity) {
		// TODO Auto-generated method stub
		
	}

}
