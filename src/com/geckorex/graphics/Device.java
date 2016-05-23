package com.geckorex.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Arrays;

public class Device
{
	private final int		width;
	private final int		height;
	private Graphics		graphics;
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

	public void drawPixel(int x, int y, byte a, byte r, byte g, byte b)
	{
		int index = (x + y * width) * 4;

		this.backBuffer[index    ] = a;
		this.backBuffer[index + 1] = b;
		this.backBuffer[index + 2] = g;
		this.backBuffer[index + 3] = r;
	}

	public void drawLine(int x1, int y1, int x2, int y2, byte a, byte r, byte g, byte b)
	{
		int dx = Math.abs(x2 - x1);
		int dy = Math.abs(y2 - y1);
		int sx = (x1 < x2) ? 1 : -1;
		int sy = (y1 < y2) ? 1 : -1;
		int err = dx - dy;

		while (true) 
		{
			drawPixel(x1, y1, a, r, g, b);

			if ((x1 == x2) && (y1 == y2)) break;
			int e2 = 2 * err;
			if (e2 > -dy) { err -= dy; x1 += sx; }
			if (e2 <  dx) { err += dx; y1 += sy; }
		}
	}

	public void clear(byte shade)
	{
		Arrays.fill(this.backBuffer, shade);
	}

	public void present()
	{
		for (int i = 0; i < this.width * this.height; ++i)
		{
			this.frontBuffer[i * 3    ] = this.backBuffer[i * 4 + 1];
			this.frontBuffer[i * 3 + 1] = this.backBuffer[i * 4 + 2];
			this.frontBuffer[i * 3 + 2] = this.backBuffer[i * 4 + 3];
		}
		this.graphics.drawImage(this.imageBuffer, 0, 0, this.width, this.height, null);
	}
}