package net.entetrs.xenon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.entetrs.xenon.commons.Global;
import net.entetrs.xenon.commons.displays.Fader;
import net.entetrs.xenon.commons.displays.Fader.State;
import net.entetrs.xenon.commons.utils.GdxCommons;

/**
 * contrôleur principal LibGDX qui gère les changement d'écrans par Fade-in /
 * Fade-out. Cette classe fournit aussi un SpriteBatch, eventuellement. Ce
 * contrôleur implémente le design pattern singleton (une seule instance unique
 * en mémoire JVM).
 * 
 * @author CDT RBN
 *
 */
public final class MainControler extends Game implements XenonControler
{
	/* pour dessiner des texture et sprites à l'écran */
	private SpriteBatch batch;

	/* instance du fader pour "fade-in et fade-out" */
	private Fader fader;

	private ShapeRenderer shapeRenderer;

	private Viewport viewPort;

	private Camera camera;

	/* DP SINGLETON */
	private static MainControler instance = new MainControler();

	private MainControler()
	{
		/* protection */
	}

	public static MainControler getInstance()
	{
		return instance;
	}

	@Override
	public void create()
	{
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		this.batch = new SpriteBatch();
		this.batch.enableBlending();
		this.fader = Fader.getInstance();
		this.shapeRenderer = new ShapeRenderer();
		this.camera = new OrthographicCamera();
		this.viewPort = new FitViewport(Global.width, Global.height, camera);
		this.viewPort.apply();
		GdxCommons.adaptCameraToViewPort(camera);
		this.setScreen(XenonScreen.LOADING.createScreen(this));
	}

	/**
	 * cette méthode est déclenchée par LibGDX à 60 FPS (60 images par secondes
	 * !)
	 */
	@Override
	public void render()
	{
		GdxCommons.clearScreen();
		if (!fader.getCurrentState().equals(State.BLACK_SCREEN))
		{
			batch.setProjectionMatrix(camera.combined);
			super.render();
		}
		fader.render();
	}

	@Override
	public void showScreen(XenonScreen screen)
	{
		Screen nextScreen = screen.createScreen(this);
		fader.startFadeOut(() -> this.setScreen(nextScreen));
	}

	@Override
	public ShapeRenderer getShapeRenderer()
	{
		return shapeRenderer;
	}

	@Override
	public SpriteBatch getBatch()
	{
		return this.batch;
	}

	@Override
	public void resize(int width, int height)
	{
		viewPort.update(width, height);
		GdxCommons.adaptCameraToViewPort(camera);
	}
}
