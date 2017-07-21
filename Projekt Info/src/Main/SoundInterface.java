package Main;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class SoundInterface {
		
	static List<Integer> bufferedSounds = new ArrayList<Integer>();
	
	public static void init(){
		
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setListenerData(){
		
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
		
		
	}
	
	public static void close(){
		
		for(int buffer  : bufferedSounds){
			
			AL10.alDeleteBuffers(buffer);
			
		}
		
		AL.destroy();
		
	}
	
	public static int loadSound(String path){
		
		int buffer = AL10.alGenBuffers();
		bufferedSounds.add(buffer);
		WaveData waveFile = WaveData.create(path);
		AL10.alBufferData(buffer, waveFile.format, waveFile.data, waveFile.samplerate);
		waveFile.dispose();
		return buffer;
		
		
	}
	
}
