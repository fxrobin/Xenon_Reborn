package net.entetrs.xenon.commons.displays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import net.entetrs.xenon.commons.Global;

public final class Fader
{
	private float step = (MAX_ALPHA - MIN_ALPHA) / Global.FADE_SECONDS;
	private float currentAlpha = MIN_ALPHA;
	private State currentState = State.BLACK_SCREEN;

	private static final float MIN_ALPHA = 0.0f;
	private static final float MAX_ALPHA = 1.0f;

	private ShapeRenderer fadeRenderer = new ShapeRenderer();

	private static Fader instance = new Fader();

	public static Fader getInstance()
	{
		return instance;
	}

	private Fader()
	{
		/* protection */
	}

	public enum State
	{
		BLACK_SCREEN, FADING_OUT, FADING_IN, DISPLAYING_SCREEN;
	}

	public State getCurrentState()
	{
		return currentState;
	}

	public void startFadeIn()
	{
		currentAlpha = MAX_ALPHA;
		currentState = State.FADING_IN;
	}

	public void startFadeOut()
	{
		currentAlpha = MIN_ALPHA;
		currentState = State.FADING_OUT;
	}

	public void fade()
	{
		float delta = Gdx.graphics.getDeltaTime();

		switch (currentState)
		{
			case FADING_IN:
				fadeIn(delta);
				break;
			case FADING_OUT:
				fadeOut(delta);
				break;
			default:
				break;
		}

		if (currentState != State.DISPLAYING_SCREEN)
		{
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			fadeRenderer.begin(ShapeType.Filled);
			fadeRenderer.setColor(0, 0, 0, currentAlpha);
			fadeRenderer.rect(0, 0, Global.width, Global.height);
			fadeRenderer.end();
		}
	}

	private void fadeOut(float delta)
	{
		currentAlpha = currentAlpha + (step * delta);
		if (currentAlpha > MAX_ALPHA)
		{
			currentAlpha = MAX_ALPHA;
			this.currentState = State.BLACK_SCREEN;
		}
	}

	private void fadeIn(float delta)
	{
		currentAlpha = currentAlpha - (step * delta);
		if (currentAlpha < MIN_ALPHA)
		{
			currentAlpha = MIN_ALPHA;
			this.currentState = State.DISPLAYING_SCREEN;
		}
	}
}
