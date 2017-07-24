package MusicPlayer;

import java.util.ArrayList;
import java.util.List;

import graphicObjects.SoundSource;
import Main.SoundInterface;

public class MusicPlayer extends SoundSource implements Runnable {
	
	private String musictheme;
	private boolean close, forcenextsong;
	private long starttime;
	private int songnumber;
	private float x, y;
	private Song playingSong;
	private List<Song> defaultTheme, bossTheme, levelTheme, gameOverTheme;

	public MusicPlayer(){
		
		super(0,0);
		init();
		
	}
	
	private void init(){
		
		musictheme = "";
		forcenextsong = false;
		close = false;
		
		defaultTheme = new ArrayList<Song>();
		defaultTheme.add(new Song((2*60 + 44)*1000, SoundInterface.loadSound("Audio/Startbildschirm.wav")));
		defaultTheme.add(new Song((1*60 + 20)*1000, SoundInterface.loadSound("Audio/Raum1.wav")));

		bossTheme = new ArrayList<Song>();
		
		levelTheme = new ArrayList<Song>();
		levelTheme.add(new Song((0*60 + 13)*1000, SoundInterface.loadSound("Audio/Ende.wav")));
		levelTheme.add(new Song((1*60 + 22)*1000, SoundInterface.loadSound("Audio/Win.wav")));
		
		gameOverTheme = new ArrayList<Song>();
		
		
	}
	
	public void run(){
		
		while(!close){
			
			long runningtime = System.currentTimeMillis() - starttime;
			
			setPosition(x, y);
			
			if(playingSong != null){

				if(playingSong.length < runningtime || forcenextsong){
					
					forcenextsong = false;
					
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
		
		starttime = System.currentTimeMillis();
		
		switch(musictheme){
		
		case "bossTheme":
			
			bossTheme();
			
			break;
		
		case "levelTheme":
			
			levelTheme();
			
			break;
			
		case "gameOverTheme":
			
			gameOverTheme();
			
			break;
		
		default:
			
			defaultTheme();
		
		}
		
	}
	
	private void bossTheme(){
		
		songnumber++;
		
		if(songnumber < defaultTheme.size() || songnumber < 0){
			
			stopSoundeffect();
			playingSong = bossTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
			
		}else{
			
			stopSoundeffect();
			songnumber = 0;
			playingSong = bossTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
		}
		
		System.out.println("Now Playing bossTheme: " + songnumber);
		
	}
	
	private void levelTheme(){
		
		songnumber++;
		
		if(songnumber < defaultTheme.size() || songnumber < 0){
			
			stopSoundeffect();
			playingSong = levelTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
			
		}else{
			
			stopSoundeffect();
			songnumber = 0;
			playingSong = levelTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
		}
		
		System.out.println("Now Playing levelTheme: " + songnumber);
		
	}
	
	private void gameOverTheme(){
		
		songnumber++;
		
		if(songnumber < defaultTheme.size() || songnumber < 0){
			
			stopSoundeffect();
			playingSong = gameOverTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
			
		}else{
			
			stopSoundeffect();
			songnumber = 0;
			playingSong = gameOverTheme.get(songnumber);
			playSoundeffect(playingSong.soundbuffer);
			
		}
		
		System.out.println("Now Playing gameOverTheme: " + songnumber);
		
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
	
	public void setMusicTheme(String theme, boolean forcenextsong){
		
		musictheme = theme;
		songnumber = -1;
		this.forcenextsong = forcenextsong;
		
	}
	
	public void requestclose(){
		
		close = true;
		
	}
	
}
