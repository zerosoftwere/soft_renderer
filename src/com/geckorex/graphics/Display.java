package com.geckorex.graphics;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Display extends Canvas
{
	private final int 		width;
	private final int 		height;
	private Graphics 		graphics;
	private BufferStrategy 	bufferStrategy;
	private JFrame 			frame;
	private Device 			device;

	public Display(int width, int height, String caption)
	{
		Dimension dimension = new Dimension(width, height);
		setPreferredSize(dimension);
		setMaximumSize(dimension);
		setMinimumSize(dimension);

		this.width  = width;
		this.height = height;

		this.frame = new JFrame(caption);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.add(this);
		this.frame.pack();
		this.frame.setResizable(false);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);

		createBufferStrategy(1);
		this.bufferStrategy = getBufferStrategy();
		this.graphics 		= bufferStrategy.getDrawGraphics();

		this.device = new Device(this.graphics, width, height);
	}

	public int getWidth()
	{
		return this.width;
	}

	public int getHeight()
	{
		return this.height;
	}

	public Device getDevice()
	{
		return this.device;
	}
}