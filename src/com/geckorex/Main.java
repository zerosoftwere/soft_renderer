package com.geckorex;

import com.geckorex.graphics.Display;
import com.geckorex.graphics.Device;

public class Main
{
	public static void main(String[] args)
	{
		Display display = new Display(640, 480, "Gecko Renderer");
		Device 	device  = display.getDevice();

		while(true)
		{
			device.clear((byte)0x00);

			device.present();
		}
	}
}