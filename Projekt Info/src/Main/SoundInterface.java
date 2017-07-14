package Main;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.util.WaveData;

import static org.lwjgl.openal.AL10.*;

public class SoundInterface {
	
	public void init(){
		
		try {
			AL.create();
		} catch (LWJGLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void playSound(String File) {
		
		try {
			
			WaveData sound = WaveData.create(new BufferedInputStream(new FileInputStream(File)));
			
			int buffer = alGenBuffers();
			alBufferData(buffer, sound.format , sound.data, sound.samplerate);
			sound.dispose();
			int source = alGenSources();
			alSourcei(source, AL_BUFFER, buffer);
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
}
