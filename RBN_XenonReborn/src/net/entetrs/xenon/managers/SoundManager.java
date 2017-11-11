package net.entetrs.xenon.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;

import net.entetrs.xenon.commons.GdxCommons;

public enum SoundManager implements Disposable
{
	SHOOT("shoot.mp3"), 
	SHIELD_UP("shield_up.wav"), 
	SHIELD_DOWN("shield_down.wav"), 
	CLIC("clic.wav"), 
	EXPLOSION("explosion.wav"), 
	INTRO("intro.mp3"), 
	MUSIC("music.mp3");
	
	private Sound sound;
	
	private SoundManager(String fileName)
	{
		String completeName = String.format("sounds/%s", fileName);
		this.sound = Gdx.audio.newSound(Gdx.files.internal(completeName));
	}

	public Sound getSound()
	{
		return this.sound;
	}
	
	public void play()
	{
		this.sound.play();
	}
	
	public void play(float vol)
	{
		this.sound.play(vol);
	}
	
	public void loop(float vol)
	{
		this.sound.loop(vol);
	}
	
	public void loop()
	{
		this.sound.loop();
	}
	
	public void stop()
	{
		this.sound.stop();
	}


	@Override
	public void dispose()
	{
		System.out.print("DISPOSE SOUNDS ...");
		GdxCommons.disposeAll(SoundManager.values());
		System.out.println("OK");
	}



}
