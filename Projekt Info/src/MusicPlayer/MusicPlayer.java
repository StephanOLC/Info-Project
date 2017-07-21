package MusicPlayer;

import java.util.ArrayList;
import java.util.List;

import graphicObjects.SoundSource;
import Main.SoundInterface;

public class MusicPlayer extends SoundSource implements Runnable {
	
	private String musictheme;
	private boolean close;
	private long starttime;
	private int songnumber;
	private Song playingSong;
	private List<Song> defaultTheme;

	public MusicPlayer(){
		
		super(0,0);
		init();
		
	}
	
	private void init(){
		
		musictheme = "";
		
		defaultTheme = new ArrayList<Song>();
		defaultTheme.add(new Song((2*60 + 44)*1000, SoundInterface.loadSound("Audio/Startbildschirm.wav")));
		defaultTheme.add(new Song((1*60 + 22)*1000, SoundInterface.loadSound("Audio/Win.wav")));
		
	}
	
	public void run(){
		
		while(!close){
			
			long runningtime = System.currentTimeMillis() - starttime;
			
			if(playingSong != null){
				
				if(playingSong.length < runningtime){
					
					playnextSong();
					
				}
				
			}else{
				
				playnextSong();
				
			}
			
		}
		
	}
	
	private void playnextSong(){
		
		switch(musictheme){
		
		default:
			
			defaultTheme();
		
		}
		
		starttime = System.currentTimeMillis();
		
	}
	
	private void defaultTheme(){
		
		songnumber++;
		if(songnumber < defaultTheme.size()){
			
			playingSong = defaultTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
		}
		
		
	}
	
	public void setMusicTheme(String theme){
		
		musictheme = theme;
		songnumber = -1;
		
	}
	
	public void requestclose(){
		
		close = true;
		
	}
	
}
