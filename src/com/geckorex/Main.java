package com.geckorex;

import com.geckorex.graphics.Display;
import com.geckorex.graphics.Device;

public class Main
{
	public static final int DEFAULT_WIDTH 	= 640;
	public static final int DEFAULT_HEIGHT 	= 480;
	
	public static void main(String[] args)
	{
		Display display = new Display(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Gecko Renderer");
		Device 	device  = display.getDevice();

		while(true)
		{
			device.clear((byte)0x00);

			drawRandomPixels(device);
			drawTrangle(device);

			device.present();
		}
	}

	public static void drawRandomPixels(Device device)
	{
		for (int i = 0; i < 10000; ++i)
		{
			int x 	= (int)(Math.random() * DEFAULT_WIDTH);
			int y	= (int)(Math.random() * DEFAULT_HEIGHT);

			byte a 	= (byte)(0xFF);
			byte r 	= (byte)(Math.random() * 256);
			byte g 	= (byte)(Math.random() * 256);
			byte b 	= (byte)(Math.random() * 256);

			device.drawPixel(x, y, a, r, g, b);
		}
	}

	public static void drawTrangle(Device device)
	{
		device.drawLine(320, 120, 160, 360, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF);
		device.drawLine(320, 120, 480, 360, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF);
		device.drawLine(160, 360, 480, 360, (byte)0xFF, (byte)0x00, (byte)0x00, (byte)0xFF);
	}
}