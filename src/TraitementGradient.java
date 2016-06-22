import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.ArrayList;


public class TraitementGradient {
	
	private BufferedImage imm;
	
	public TraitementGradient(File f, int l) {
		
		List<Integer> redfiltered =new ArrayList<Integer>();
		List<Integer> greenfiltered =new ArrayList<Integer>();
		List<Integer> bluefiltered=new ArrayList<Integer>();
		List<Integer> list = new ArrayList<Integer>();
        ImageToPixel impx =null;
        
        try {
        	impx=new ImageToPixel(f);
        }catch(Exception e) {
        	 e.printStackTrace();
        }
		
        int nbcolonnes=impx.getimagewidth();
        int nblignes=impx.getimageheight();
      //  System.out.println(impx.getredtab().size());
        if (l==3)
        {
        redfiltered=Gradient.gradientxy(impx.getredtab(),nbcolonnes, nblignes);
        greenfiltered=Gradient.gradientxy(impx.getgreentab(), nbcolonnes, nblignes);
        bluefiltered=Gradient.gradientxy(impx.getbluetab(), nbcolonnes, nblignes);
        }
        if (l==2)
        {
        redfiltered=Gradient.gradienty(impx.getredtab(),nbcolonnes, nblignes);
        greenfiltered=Gradient.gradienty(impx.getgreentab(), nbcolonnes, nblignes);
        bluefiltered=Gradient.gradienty(impx.getbluetab(), nbcolonnes, nblignes);
        }
        if (l==1)
        {
        redfiltered=Gradient.gradientx(impx.getredtab(),nbcolonnes, nblignes);
        greenfiltered=Gradient.gradientx(impx.getgreentab(), nbcolonnes, nblignes);
        bluefiltered=Gradient.gradientx(impx.getbluetab(), nbcolonnes, nblignes);
        }
        
        
        
        for (int i=0;i<nblignes;i++)
        {
        	for (int j=0;j<nbcolonnes;j++)
        		list.add(65536*redfiltered.get(i*nbcolonnes+j)+256*greenfiltered.get(i*nbcolonnes+j)+bluefiltered.get(i*nbcolonnes+j));
        }
        
        //System.out.println("Type = "+impx.gettype());
        imm=new BufferedImage(nbcolonnes,nblignes,impx.gettype());
        
        for (int i=0;i<nblignes;i++)
        {
        	for(int j=0;j<nbcolonnes;j++)
        		imm.setRGB(j, i, list.get(i*nbcolonnes+j));
        }
	}
        
        public BufferedImage getimm() {
        	
        	return this.imm;
        }
        
        
        
	

}
