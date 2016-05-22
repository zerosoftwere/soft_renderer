package com.geckorex.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

public class Device
{
	private final int 		width;
	private final int 		height;
	private Graphics 		graphics;
	private BufferedImage	imageBuffer;
	private byte[]			frontBuffer;
	private byte[]			backBuffer;

	public Device(Graphics graphics, int width, int height)
	{
		this.width  	 = width;
		this.height 	 = height;
		this.graphics 	 = graphics;
		this.imageBuffer = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		this.frontBuffer = ((DataBufferByte)this.imageBuffer.getRaster().getDataBuffer()). getData();
		this.backBuffer	 = new byte[width * height * 4];
	}

	public void clear(byte shade)
	{
		Arrays.fill(this.backBuffer, shade);
	}

	public void present()
	{
		for (int i = 0; i < this.width * this.height; ++i)
		{
			this.frontBuffer[i * 3	  ] = this.backBuffer[i * 4 + 1];
			this.frontBuffer[i * 3 + 1] = this.backBuffer[i * 4 + 2];
			this.frontBuffer[i * 3 + 2] = this.backBuffer[i * 4 + 3];
		}
		this.graphics.drawImage(this.imageBuffer, 0, 0, this.width, this.height, null);
	}
}