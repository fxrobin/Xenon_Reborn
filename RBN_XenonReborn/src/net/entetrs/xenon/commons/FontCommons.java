package net.entetrs.xenon.commons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import net.entetrs.xenon.libs.TextureLib;

public class FontCommons
{

	private static final int FONT_W = 32;
	private static final int FONT_H = 38;

	private static Texture fontAZ;
	private static Texture font09;

	static
	{
		fontAZ = TextureLib.FONT_AZ.get();
		font09 = TextureLib.FONT_09.get();
	}

	public static int getWidth(String txt)
	{
		return txt.length() * FONT_W;
	}

	public static int getHeight(String txt)
	{
		return FONT_H;
	}

	public static void print(Batch b, float x, float y, String txt)
	{
		txt = txt.toUpperCase();
		for (int i = 0; i < txt.length(); i++)
		{
			char c = txt.charAt(i);
			print(b, x + (i * FONT_W), y, c);
		}
	}

	public static void print(Batch b, float x, float y, char c)
	{
		if (c >= 65 && c <= 90)
		{
			int offset = (c - 65) * FONT_W;
			b.draw(fontAZ, x, y, offset, 0, FONT_W, FONT_H);
		}
		if (c >= 48 && c <= 57)
		{
			int offset = (c - 48) * FONT_W;
			b.draw(font09, x, y, offset, 0, FONT_W, FONT_H);
		}
	}

}
