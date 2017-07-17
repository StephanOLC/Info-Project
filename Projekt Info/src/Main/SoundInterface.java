package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.openal.Audio; 
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

class SoundInterface {
		
	 List<Audio> audiolist;
	 
	 public void init(){
		 
		 audiolist = new ArrayList<Audio>();
		 
		 
	 }
	 
	 public int loadAudio(String path, String fileformat){
		 
		 
		 try {
			Audio audionew = AudioLoader.getAudio(fileformat, ResourceLoader.getResourceAsStream(path));
			int index =  audiolist.size();
			audiolist.add(index, audionew);
			return index;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		 return -1;
		 
	 }
	 
	 public void playAudio(int index){
		 
		 audiolist.get(index).playAsSoundEffect(1.0f, 1.0f, false);
	 
	 }
	 
	 public void playAudio(int index, float pitch, float gain, boolean loop){
		 
		 audiolist.get(index).playAsSoundEffect(pitch, gain, loop);
		 
	 }
	 
	 public void stopaudio(int index){
		 
		 audiolist.get(index).stop();
		 
	 }
	
}
