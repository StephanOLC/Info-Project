package ingameObjects;

import java.util.ArrayList;

import gameLogic.Vektor;
import gameLogic.World;
import graphicObjects.TextureObject;

public class Lever extends TextureObject implements IngameObject {
	
	private boolean activated;
	private World world;
	private Vektor position;
	private ArrayList<Device> deviceList;
	
	public Lever(Vektor position, World world){
		super(position.getX(), position.getY(), "Graphics/Schalter/Schalter1.png", "Lever" ,world.getInterface());
		this.position = position;
		this.world = world;
		deviceList = new ArrayList<Device>();
	}

	@Override
	public char getTeam() {
		return 'l';
	}

	@Override
	public Vektor getPosition() {
		return position;
	}
	
	@Override
	public void tick() {
		collision(world.detectCollissionType(position));
		
	}
	
	public boolean getActivated(){
		return activated;
	}
	
	public void addDevice(Device device){
		deviceList.add(device);
	}

	public void activateDevices(){
		for(Device device : deviceList){
			device.activate(activated);
		}
	}

	public void collision(ArrayList<Integer> collisions){
		boolean switched = false;
		for(int effect : collisions){
			switch (effect){
				case 0: world.deathNote(this);
					break;
			
				default: switched = true;
				break;
			}
		}
		if(switched){
			activated = !activated;
		}
	}

	@Override
	public void updateGraphic() {
		setPosition(position.getX(), position.getY());
		
	}

}
