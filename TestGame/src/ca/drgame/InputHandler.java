package ca.drgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputHandler implements KeyListener {
	
	
	InputHandler(Game game)
	{
		game.addKeyListener(this);
	}
   
	public class Key	{
		private boolean pressed = false;
		private int numTimesPressed = 0;
		
		public int getTimesPressed()
		{
			return numTimesPressed;
		}
		
	    public void toggle(boolean isPressed)
	    {
	    	pressed=isPressed;
	    	if(pressed) numTimesPressed++;
	    }
	    
	    public boolean isPressed()
	    {
	    	return pressed;
	    }
		
	}
	
    List<Key> keys = new ArrayList<Key>();
    
    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key ranged = new Key();
    public Key melee = new Key();
    public Key jump = new Key();
	
	public void keyPressed(KeyEvent e) {
		
		toggleKey(e.getKeyCode(),true); 
	}

	
	public void keyReleased(KeyEvent e) {
		
		toggleKey(e.getKeyCode(),false); 
		
	}


	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public void toggleKey(int keycode, boolean isPressed)
	{
		if (keycode==KeyEvent.VK_W || keycode==KeyEvent.VK_UP) {up.toggle(isPressed);}
		if (keycode==KeyEvent.VK_S || keycode==KeyEvent.VK_DOWN) {down.toggle(isPressed);}
		if (keycode==KeyEvent.VK_A || keycode==KeyEvent.VK_LEFT) {left.toggle(isPressed);}
		if (keycode==KeyEvent.VK_D || keycode==KeyEvent.VK_RIGHT) {right.toggle(isPressed);}
		//if (keycode==KeyEvent.VK_SPACE || keycode==KeyEvent.VK_RIGHT) {jump.toggle(isPressed);}
		if (keycode==KeyEvent.VK_Q || keycode==KeyEvent.VK_RIGHT) {melee.toggle(isPressed);}
		if (keycode==KeyEvent.VK_E || keycode==KeyEvent.VK_RIGHT) {ranged.toggle(isPressed);}
		
	}

}

