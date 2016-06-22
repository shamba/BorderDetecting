import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

import net.miginfocom.swing.MigLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;




public class IHM extends JFrame {

	private JPanel contentPane;
	private JFrame imageoriginale;
	private JFrame imagefiltered;
	private File f;
	private int a;
	private static Dimension d;
	private static int dimx;
	private static int dimy;
	private int width;
	private int height;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				d=Toolkit.getDefaultToolkit().getScreenSize();
				dimx=d.width;
				dimy=d.height;
				try {
					  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					} catch(Exception e) {
					  System.out.println("Error setting native LAF: " + e);
					}
				try {
					IHM frame = new IHM();
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IHM() {
		setTitle("FILTRAGE GRADIENT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Filtrage Gradient");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("D\u00E9tection des contours dans une image");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		
		JButton btnNewButton = new JButton("Charger Image ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				BufferedImage im=null;
				ImageIcon imc =null;
				JLabel jl =new JLabel();
				JFileChooser js =new JFileChooser();
				int s=js.showOpenDialog(IHM.this);
				if (s==js.APPROVE_OPTION)
				{
				 f = js.getSelectedFile();
				  
				}
				try {
					im=ImageIO.read(f);
				}catch (Exception e)
				{
					e.printStackTrace();
				}
				width=im.getWidth();
				height=im.getHeight();
				if(height>400)
				{
					double divfactor=(double)((double)height/(double)400);
					width=(int) (width/divfactor);
					height=400;
					//System.out.println("height ="+height+"  width = "+width+ " div = "+divfactor );
					imc=new ImageIcon(resizedImage(im,width,height,im.getType()));
					
				}
				else
				{
				imc=new ImageIcon(im);
				}
				jl.setSize(width,height);
				jl.setIcon(imc);
				
				imageoriginale=new JFrame("Image Originale");
				imageoriginale.setLocationRelativeTo(IHM.this);
				imageoriginale.setSize(width+15, height+35);
				imageoriginale.getContentPane().setSize(width, height);
				
				imageoriginale.getContentPane().add(jl,BorderLayout.CENTER);
				imageoriginale.setVisible(true);
			}
		});
		
		ButtonGroup buttongroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Filtrage Horizontal");
		rdbtnNewRadioButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdbtnNewRadioButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				a=1;
				
			}
			
		});
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Filtrage Vertical");
		rdbtnNewRadioButton_1.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdbtnNewRadioButton_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				a=2;
				
			}
			
		});
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Filtrage total");
		rdbtnNewRadioButton_2.setFont(new Font("Dialog", Font.PLAIN, 14));
		rdbtnNewRadioButton_2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				a=3;
				
			}
			
		});
		
		buttongroup.add(rdbtnNewRadioButton);
		buttongroup.add(rdbtnNewRadioButton_1);
		buttongroup.add(rdbtnNewRadioButton_2);
		
		JButton btnNewButton_1 = new JButton("Filtrer");
		btnNewButton_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				TraitementGradient tg = new TraitementGradient(f,a);
				final BufferedImage imm= tg.getimm();
				ImageIcon immc = null;
				int h= imm.getHeight();
			    int w=imm.getWidth();
				if (h>400)
				{
					double divfactor=(double)h/(double)400;
					w=(int) (w/divfactor);
					h=400;
					immc=new ImageIcon(resizedImage(imm,w,h,imm.getType()));
				}
				else 
				{
					immc=new ImageIcon(imm);
				}
			
				JLabel jl = new JLabel();
				jl.setIcon(immc);
				jl.setSize(width,height);
				imagefiltered= new JFrame("Image Filterée");
				JMenuBar menubar = new JMenuBar();
				JMenu save = new JMenu("Save");
				JMenuItem menuitem = new JMenuItem("Save");	
				save.add(menuitem);
				menuitem.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						JFileChooser savefile = new JFileChooser();
						savefile.setDialogTitle("Enregistrer Image filtrée");
						int n=savefile.showSaveDialog(null);
						if (n==JFileChooser.APPROVE_OPTION)
						{
							File f = null;
							String ext = null;
							try {
								f=new File(savefile.getSelectedFile().toString());
								ext=Files.getFileExtension(savefile.getSelectedFile().toString());
								
							}catch(Exception e)
							{
								JOptionPane.showMessageDialog(savefile, "Veuillez rentrer un non au fichier (format .jpg .bmp .png .tif).", "Fichier format", JOptionPane.INFORMATION_MESSAGE);
							}
							try {
								ImageIO.write(imm, ext, f);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}
					}
					
					
					
				});
				menubar.add(save);
				imagefiltered.getContentPane().add(menubar,"North");
				imagefiltered.setSize(w+15,h+45);
				imagefiltered.setLocationRelativeTo(IHM.this);
				imagefiltered.getContentPane().add(jl);
				imagefiltered.setVisible(true);
				
			}
		
			
		});
		
		JLabel lblAuteurSattanadane = new JLabel("Auteur : SATTANADANE Ganesh");
		lblAuteurSattanadane.setFont(new Font("Tahoma", Font.ITALIC, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(134)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addComponent(lblNewLabel_1)))
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(rdbtnNewRadioButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(rdbtnNewRadioButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
					.addComponent(rdbtnNewRadioButton_2)
					.addGap(22))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(132, Short.MAX_VALUE)
					.addComponent(lblAuteurSattanadane)
					.addGap(137))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(152)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addGap(31)
					.addComponent(btnNewButton)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_2)
						.addComponent(rdbtnNewRadioButton_1))
					.addGap(18)
					.addComponent(btnNewButton_1)
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(lblAuteurSattanadane))
		);
		contentPane.setLayout(gl_contentPane);
	}
	public BufferedImage resizedImage(BufferedImage originalImage, int width, int height, int type)
	   {
		   
		   BufferedImage resizedim = new BufferedImage(width, height,type);
		   Graphics2D g= resizedim.createGraphics();
		   g.drawImage(originalImage,0,0,width,height,null);
		   g.dispose();
		   return resizedim;
		   
	   }
}
