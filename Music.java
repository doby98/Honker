package javaProject_poker;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
//import javax.sound.sampled.FloatControl;

public class Music {
	
	public void playMusic() {
		File bgm;
		AudioInputStream stream;
		AudioFormat format;
		DataLine.Info info;
		
		bgm = new File("Addict1 (Instrumental) - NEFFEX (online-audio-converter.com).wav");
		
		Clip clip;
		
		try {
			stream = AudioSystem.getAudioInputStream(bgm);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
			
//			clip = AudioSystem.getClip();
//			clip.open(stream);
            
//            FloatControl volumeControl = (FloatControl)clip.getControl(FloatControl.Type.VOLUME);
//            volumeControl.setValue(10.0f);
            
            clip.open(stream);
            clip.loop(10);
            clip.start();
            
		} catch (Exception e) {
			System.out.println("[ERROR] 알 수 없는 에러입니다. 관리자에게 문의하세요.");
			e.printStackTrace();
		}
	}
}
