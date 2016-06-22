import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;


public class ImageToPixel {

	  private List<Integer> redtab;
	  private List<Integer> greentab;
	  private List<Integer> bluetab;
	  private int imageheight;
	  private int imagewidth;
	  private  int type;
	
	
	public ImageToPixel(File ImageFile) throws IOException {
		
		 BufferedImage image = ImageIO.read(ImageFile);
		   imagewidth=image.getWidth();
		   imageheight=image.getHeight();
		   type = image.getType();
		  int clr;
		  
		    redtab=new ArrayList<Integer>();
		    greentab=new ArrayList<Integer>();
		    bluetab=new ArrayList<Integer>();
		    
		  for (int y=0;y<imageheight;y++)
		  {
			  for (int x=0;x<imagewidth;x++)
			  {
				  clr=image.getRGB(x,y);
				  redtab.add((clr & 0x00ff0000) >> 16);
				  greentab.add((clr & 0x0000ff00) >> 8);
				  bluetab.add(clr & 0x000000ff);  	  
			  }
		  }
	
	}
	 public List<Integer> getredtab() {
		  
		  return this.redtab;
		  
		  
	  }
	  public List<Integer> getgreentab(){
		  
		  
		  return this.greentab;
	  }
	  public List<Integer> getbluetab(){
		  
		  return this.bluetab;  
	  }
	  
	 public int getimageheight()
	 {
		 return this.imageheight;
		 
	 }
	 public int getimagewidth()
	 {
		 
		 return this.imagewidth;
	 }
	public int gettype(){
		
		return type;
		
	}
}
