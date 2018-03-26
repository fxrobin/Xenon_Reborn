package net.entetrs.xenon.commons.libs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.utils.Disposable;

import net.entetrs.xenon.commons.utils.GdxCommons;

public enum TextureAsset implements Disposable
{
	TITLE("commons/xenon-reborn.png"), 
	BACKGROUND_BOMBING_PIXELS("backgrounds/bombing-pixels-white.jpg"),
	BACKGROUND_SPACE_DOCK("backgrounds/space-dock.png", TextureWrap.Repeat), 
	BACKGROUND_WALL("backgrounds/wall.png", TextureWrap.Repeat), 
	BACKGROUND_STATIC("backgrounds/static-background.png"), 
	BACKGROUND_LEFT("backgrounds/left_bg.png", TextureWrap.Repeat), 
	BACKGROUND_RIGHT("backgrounds/right_bg.png", TextureWrap.Repeat), 
	BACKGROUND_SPACE("backgrounds/space.jpg", TextureWrap.Repeat), 
	FOOTER("backgrounds/footer.png"),
	LIFE("commons/life.png"),

	SHIELD("ships/shield.png"), 
	SHIP_LEFT("ships/ship_left.png"), 
	SHIP_RIGHT("ships/ship_right.png"), 
	SHIP("ships/ship_normal.png"), 
	SHIP_NOREACTOR("ships/ship_noreactor.png"),
	

	ENEMY("enemies/enemy.png"), 
	BUG("enemies/bug.png"), 
	PERFORATOR("enemies/perforator.png"), 
	BIG_ENEMY("enemies/big-enemy.png"),
	RAFALE("enemies/rafale.png"),
	BLACK_BIRD("enemies/black-bird.png"),
	XENON_SHIP("enemies/xenon-ship.png"),
	BULLET("shoots/bullet.png"),

	FONT("fonts/font-blue.png");

	private final String fileName;
	private final TextureWrap wrap;

	private Texture texture;

	private TextureAsset(String fileName)
	{
		this(fileName, null);
	}

	private TextureAsset(String fileName, TextureWrap wrap)
	{
		this.fileName = fileName;
		this.wrap = wrap;
	}

	@Override
	public String toString()
	{
		return this.fileName;
	}

	public Texture get()
	{
		if (texture == null)
		{
			Texture tmpTexture = AssetLib.getInstance().get(this, Texture.class);
			if (wrap != null)
			{
				tmpTexture.setWrap(wrap, wrap);
			}
			this.texture = tmpTexture;
		}
		return texture;
	}

	@Override
	public void dispose()
	{
		if (texture != null)
		{
			this.texture.dispose();
		}
	}

	public static void disposeAll()
	{
		GdxCommons.disposeAll(TextureAsset.values());
	}

}
