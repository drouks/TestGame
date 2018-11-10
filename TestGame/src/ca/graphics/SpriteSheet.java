package ca.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
public String path;
public int width;
public int height;
public int[] pixeldata;

public SpriteSheet(String path)
{
	BufferedImage img = null;
	
	try {
		img = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	if (img==null) return;
	
	this.path = path;
	this.width = img.getWidth();
	this.height = img.getHeight();
	
	pixeldata = img.getRGB(0, 0, width, height, null, 0, width);
	
	for(int i=0;i<pixeldata.length;i++)
	{
	  pixeldata[i] = (pixeldata[i] & 0xff)/64;
	}
	
	
	
	
	
}

}
