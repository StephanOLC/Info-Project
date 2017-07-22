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
	private float x, y;
	private Song playingSong;
	private List<Song> defaultTheme;

	public MusicPlayer(){
		
		super(0,0);
		init();
		
	}
	
	private void init(){
		
		musictheme = "";
		
		defaultTheme = new ArrayList<Song>();
		defaultTheme.add(new Song((0*60 + 44)*1000, SoundInterface.loadSound("Audio/Startbildschirm.wav")));
		defaultTheme.add(new Song((0*60 + 22)*1000, SoundInterface.loadSound("Audio/test.wav")));
		
	}
	
	public void run(){
		
		while(!close){
			
			long runningtime = System.currentTimeMillis() - starttime;
			
			setPosition(x, y);
			
			if(playingSong != null){

				if(playingSong.length < runningtime){
					
					playnextSong();
					
				}
				
			}else{
				
				playnextSong();
				
			}
			
		}
		
	}
	
	public void move(float x, float y){
		
		this.x += x;
		this.y += y;
		
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
			
			stopSoundeffect();
			playingSong = defaultTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
			
		}else{
			
			stopSoundeffect();
			songnumber = 0;
			playingSong = defaultTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
		}
		
		System.out.println("Now Playing defaultTheme: " + songnumber);
		
	}
	
	public void setMusicTheme(String theme){
		
		musictheme = theme;
		songnumber = -1;
		
	}
	
	public void requestclose(){
		
		close = true;
		
	}
	
}
