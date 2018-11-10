package ca.drgame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Levels.Level;
import Levels.LevelProgression;
import ca.drgame.entities.HeartStatus;
import ca.drgame.entities.Player;
import ca.graphics.Screen;
import ca.graphics.SpriteSheet;

public class Game extends Canvas implements Runnable {
    
	public Player player;
	public boolean running = false;
	public int tickcount = 0;
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME = "Test Game";
	private BufferedImage img = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	private int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[216];

	
	private Screen screen;
	public InputHandler handler;
	public Level level;
	public LevelProgression levelprogress;
	
	private JFrame frame;

	private static final long serialVersionUID = 1L;
	
	public Game()
	{
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		
		frame = new JFrame(NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this,BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	
	private synchronized void start()
	{
	 running = true;
	 new Thread(this).start();	
	}
	
	private synchronized void stop()
	{
	 running = false;
	}
	
	public void init()
	{
		
		int index=0;
		
		for(int r=0;r<6;r++) {
			for(int g=0;g<6;g++) {
				for(int b=0;b<6;b++) {
		                 
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);
					
					colours[index++] = rr << 16 | gg << 8 | bb;
				}
			}
		}
		
		
		screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("/spritesheet.png"));
		handler = new InputHandler(this);
		levelprogress = new LevelProgression();
		level = levelprogress.levels.get(0);
		player = new Player(level,0,0,handler);
		screen.setHealthOffset(player.getMaxhealth());
		level.addEntity(player);
		level.setPlayer(player);
	}

	public void run() {
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 60D;

        int ticks = 0;
        int frames = 0;

        long lastTimer = System.currentTimeMillis();
        double delta = 0;

        init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;

            while (delta >= 1) {
                ticks++;
                tick();
                checkEnd();
                delta -= 1;
                shouldRender = true;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
     
                frames = 0;
                ticks = 0;
            }
        }
    }

	public void tick()
	{
		tickcount ++;
		level.tick();
	
	}
	
	public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        int xOffset = player.x - (screen.width / 2);
        int yOffset = player.y - (screen.height / 2);

        level.renderTiles(screen, xOffset, yOffset);
        level.renderEntities(screen);
        renderPlayerHealth(screen);

        for (int y = 0; y < screen.height; y++) {
            for (int x = 0; x < screen.width; x++) {
                int colourCode = screen.pixels[x + y * screen.width];
                if (colourCode < 255)
                    pixels[x + y * WIDTH] = colours[colourCode];
            }
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();
    }

	
	
	public void checkEnd() {
		
		if (this.level.isFinished()==true)
		{
			if (this.levelprogress.getCurrentLevel().equals(this.levelprogress.lastlevel))
			{
				JOptionPane.showMessageDialog(null,"We are done!");
				System.exit(1);
			}
			this.player = null;
			this.handler = null;
			JOptionPane.showMessageDialog(null,"You did it! Let's go to next");
			this.level.getEntities().clear();
			this.levelprogress.goToNext();
			this.level = levelprogress.getCurrentLevel();
			this.level.setLevelStatus(false);
			this.handler = new InputHandler(this);
			player = new Player(level,0,0,handler);
			level.addEntity(player);
			level.setPlayer(player);
		}
		
	}
	
	  public void renderPlayerHealth(Screen screen)
	    {
	    	for(int i=0;i<player.getHearts().size();i++)
	    	{
	    		player.getHearts().get(i).render(screen);
	    	}
	    }
	
	
	
	public static void main(String[] args)
	{
		new Game().start();
	}
	
	

}
