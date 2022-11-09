package com.ae2dms.cw.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * SoundEffect handles the game's SFX.
 * Classes that want to use SFX will call the static variables in this enum and
 * play them via the play() method.
 */
public enum SoundEffect {
	FRUIT("sfx/fruit.wav"),
	DEATH("sfx/death.wav"),
	SHOOT("sfx/shoot.wav"),
	POP("sfx/pop.wav"),
	BUBBLED("sfx/bubbled.wav"),
	JUMP("sfx/jump.wav"),
	EXPLODE("sfx/explode.wav"),
	MAIN("sfx/main.wav"),
	LAND("sfx/land.wav");

	/**
	 * collection of volumes
	 */
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}
	
	public static Volume volume = Volume.LOW;
	
	private Clip clip;

	/**
	 * set clip
	 * @param soundFileName
	 */
	SoundEffect(String soundFileName) {
		// sets the sound effect
		try {
			URL url = this.getClass().getClassLoader().getResource(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * play the sound
	 */
	public void play() {
		clip.start();
//		if (!GameFeature.getSound()){
//			return;
//		}
//		// plays the sound effect
//		if (volume != Volume.MUTE) {
////			if (clip.isRunning()) {
////				clip.stop();
////			}
//			clip.setFramePosition(0);
//			clip.start();
//		}
	}

	/**
	 * stop the sound
	 */
	public void stop(){
		if (clip.isRunning()) {
			clip.stop();
		}
	}

	/**
	 * sets whether or not the sound effect loops
	 */
	public void setToLoop() {
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * set to loud
	 */
	public void setToLoud() {
		volume = Volume.HIGH;
	}
}
