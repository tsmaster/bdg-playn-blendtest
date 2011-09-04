package com.bigdicegames.blendtest.core;

public class GraphicsUtil {

	public static int RGB(int r, int g, int b) {
		return 0xff000000 + 
				((r&0xff)<<16) +
				((g&0xff)<<8) +
				(b&0xff);
	}
	
	public static int RGBA(int r, int g, int b, int a) {
		return ((a&0xff)<<24) + 
				((r&0xff)<<16) +
				((g&0xff)<<8) +
				(b&0xff);
	}

}
