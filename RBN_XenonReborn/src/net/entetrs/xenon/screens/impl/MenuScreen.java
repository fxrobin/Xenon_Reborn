package net.entetrs.xenon.screens.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.entetrs.xenon.commons.Global;
import net.entetrs.xenon.commons.fonts.FontUtils;
import net.entetrs.xenon.commons.libs.FontLib;
import net.entetrs.xenon.commons.libs.SoundLib;
import net.entetrs.xenon.commons.libs.TextureLib;
import net.entetrs.xenon.commons.utils.DeltaTimeAccumulator;
import net.entetrs.xenon.screens.AbstractScreen;
import net.entetrs.xenon.screens.XenonControler;
import net.entetrs.xenon.screens.XenonScreen;

public class MenuScreen extends AbstractScreen
{
	private static final String MSG = "PRESS SPACEBAR";
	private static final int MSG_WIDTH = FontUtils.getWidth(MSG);

	private Log log = LogFactory.getLog(this.getClass());

	private BackgroundTravelling backgroundTravelling;
	private DeltaTimeAccumulator accumulator = new DeltaTimeAccumulator(1);
	private boolean displayTitle = true;

	private Texture titleTexture;
	private float titleX;
	private float titleY;

	private Monitor monitor;
	private DisplayMode currentMode;
	private GlyphLayout layout;

	public MenuScreen(XenonControler controler, SpriteBatch batch)
	{
		super(controler, batch);
		log.info("Instanciation de MenuScreen");
		backgroundTravelling = new BackgroundTravelling();
		titleTexture = TextureLib.TITLE.get();
		titleX = (Global.width - titleTexture.getWidth()) / 2f;
		titleY = (Global.height - titleTexture.getHeight()) / 2f;
		monitor = Gdx.graphics.getMonitor();
		currentMode = Gdx.graphics.getDisplayMode(monitor);
		layout = new GlyphLayout();
	}

	@Override
	public void show()
	{
		SoundLib.INTRO.loop();
	}

	@Override
	public void hide()
	{
		SoundLib.INTRO.stop();
	}

	@Override
	public void render(float delta)
	{
		this.checkInput();
		this.backgroundTravelling.translateBackGround(delta);
		this.backgroundTravelling.drawBackGround(this.getBatch());
		this.drawTitle();
		this.drawDisplayMode();
		this.drawMessage(delta);
	}

	private void drawDisplayMode()
	{
		String msgDisplayMode = String.format("%s / %s", currentMode, monitor.name);
		layout.setText(FontLib.DEFAULT.getFont(), msgDisplayMode);
		BitmapFont font = FontLib.DEFAULT.getFont();
		font.draw(this.getBatch(), msgDisplayMode, (Global.width - layout.width) / 2, 150);
	}

	private void drawMessage(float delta)
	{
		/* jour / nuit / jour / nuit ! */
		displayTitle = accumulator.addAndCheck(delta) ? !displayTitle : displayTitle;
		if (displayTitle)
		{
			FontUtils.print(this.getBatch(), (Global.width - MSG_WIDTH) / 2f, 60, MSG);
		}
	}

	private void drawTitle()
	{
		SpriteBatch batch = this.getBatch();
		batch.draw(titleTexture, titleX, titleY);
	}

	private void switchFullScreen()
	{
		Monitor currMonitor = Gdx.graphics.getMonitor();
		DisplayMode displayMode = Gdx.graphics.getDisplayMode(currMonitor);
        
		if (!Gdx.graphics.isFullscreen())
		{
			if (!Gdx.graphics.setFullscreenMode(displayMode))
			{
				log.error("Erreur de passage en plein écran");
			}
		}
		else
		{
			if (!Gdx.graphics.setWindowedMode(Global.width, Global.height))
			{
				log.error("Erreur de passage mode fenêtré");
			}
		}
	}

	
	private void checkInput()
	{
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
		{
			SoundLib.CLIC.play();
			this.getControler().showScreen(XenonScreen.GAME_PLAY);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.F1))
		{
			this.switchFullScreen();
		}

		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
	}
}
