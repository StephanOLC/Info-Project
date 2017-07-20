package graphicObjects;

import org.lwjgl.openal.AL10;

public class SoundSource {

	private int sourceID;
	
	public SoundSource(int x, int y){
		
		sourceID = AL10.alGenSources();
		AL10.alSourcef(sourceID, AL10.AL_GAIN, 1);
		AL10.alSourcef(sourceID, AL10.AL_PITCH, 1);
		AL10.alSource3f(sourceID, AL10.AL_POSITION, x, y, 0);
		
	}
	
	public void playSoundeffect(int sound){
		
		AL10.alSourcei(sourceID, AL10.AL_BUFFER, sound);
		AL10.alSourcePlay(sourceID);
		
	}
	
	public void deleteSoundsource(){
		
		AL10.alDeleteSources(sourceID);
		
	}
	
}
