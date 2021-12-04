package io.github.riniwtz.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class Audio {
    private final HashMap<String, File> audioMap = new HashMap<>();
    private Clip clip;

    public Audio() {
        audioMap.put("auth_success", new File(Objects.requireNonNull(getClass().getResource("/io/github/riniwtz/tts/authentication_success.wav")).getFile()));
        audioMap.put("invalid_token", new File(Objects.requireNonNull(getClass().getResource("/io/github/riniwtz/tts/invalid_token.wav")).getFile()));
        audioMap.put("chinese_music", new File(Objects.requireNonNull(getClass().getResource("/io/github/riniwtz/music/xuehuepiaopiao.wav")).getFile()));
        audioMap.put("screenshot", new File(Objects.requireNonNull(getClass().getResource("/io/github/riniwtz/sound/screenshot.wav")).getFile()));

        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play(String key, boolean loop) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioMap.get(key));
            clip.open(audioStream);
            if (loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

}
