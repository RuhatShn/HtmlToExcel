package HtmlToExcel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ExcelViewJava implements MouseMotionListener,MouseListener {
	
	private JFrame mainFrame;
	private JPanel titlePanel;
	private JPanel titleButtonPanel;
	private JPanel mainPanel;
	private JLabel fileLabel;
	private JButton exitButton,hideButton;
	private JButton fileChooserButton,exportExcelButton;
	
	private String choosingFileName="Seçilen Dosya";
	private String xmlFindTag;
	private static int dragX=0;
	private static int dragY=0;
	private int pressedMouseX;
	private int pressedMouseY;
	private File selectedFile;
	
	
	ExcelControllerJava controller=new ExcelControllerJava();
	
	GridBagConstraints gbc=new GridBagConstraints();

	public void displayFrame() throws ParserConfigurationException, SAXException, IOException
	{
		mainFrame=new JFrame();
		
		mainFrame.setUndecorated(true);
		
		mainFrame.setSize(300, 120);
		
		displayTitlePanel();
		
		displayMainPanel();
		
		mainFrame.setVisible(true);
		
		mainFrame.addMouseMotionListener(this);
		mainFrame.addMouseListener(this);
	}
	
	
	public void displayTitlePanel() throws ParserConfigurationException, SAXException, IOException
	{
		
	//Implement titlePanel that includes exit and hide button
				titlePanel=new JPanel(new GridBagLayout());
				mainFrame.getContentPane().add(titlePanel, BorderLayout.NORTH);
				titlePanel.setBackground(new Color(21,5,11));
				titlePanel.setPreferredSize(new Dimension(300,30));
				
				
	   //-->Implement titleButtonPanel in title panel for contain hide and exit button
				titleButtonPanel=new JPanel(new GridBagLayout());
				titleButtonPanel.setBackground(new Color(21,5,11));
				titleButtonPanel.setPreferredSize(new Dimension(60,30));
				
				gbc.weightx=1.0;
				gbc.weighty=0.0;
				gbc.anchor=GridBagConstraints.FIRST_LINE_END;		
	  			
				titlePanel.add(titleButtonPanel,gbc);
				
				
				//------>>Implement hide button in title panel using with GridBagLayout
				hideButton=new JButton();
				hideButton.setPreferredSize(new Dimension(30,30));
				hideButton.setIcon(new ImageIcon(controller.callFindPath("hideButton")));
				hideButton.setBackground(new Color(21,5,11));
				
				gbc.gridx=0;
				gbc.gridy=0;
				gbc.gridwidth=1;
				gbc.gridheight=1;
				gbc.insets=new Insets(0, 0, 0, 0);
				
				titleButtonPanel.add(hideButton,gbc);
				
				
				//Call minimize status from controller
				hideButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						mainFrame.setState(Frame.ICONIFIED);
						
					}
				});
				
				
				//---->>Implement exit button in title panel using with GridBagLayout
			       exitButton=new JButton();
			       exitButton.setPreferredSize(new Dimension(30,30));
			       exitButton.setIcon(new ImageIcon(controller.callFindPath("exitButton")));
			       exitButton.setBackground(new Color(21,5,11));
			       
			       
			       gbc.gridx=1;
			       gbc.gridy=0;
			       gbc.gridwidth=1;
			       gbc.gridheight=1;
			
			       titleButtonPanel.add(exitButton, gbc);   
			       
			       
			       //Call exit status from Controller
			       exitButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						controller.exitListener();
					}
				});
			       
			       }  
		
		
	
	public void displayMainPanel()
	{
		  //Implement main panel that includes file label,filechooser button and export excel button
		  
		  mainPanel=new JPanel(new GridBagLayout());
		  mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		  mainPanel.setBackground(new Color(21,5,11));
		  mainPanel.setPreferredSize(new Dimension(300,90));
		  
		  
		//---->>Implement fileLabel that specify choosing file name
		  fileLabel=new JLabel(choosingFileName);
		  fileLabel.setBackground(Color.BLACK);
		  
		  gbc.gridx=0;
		  gbc.gridy=0;
		  gbc.gridwidth=2;
		  gbc.gridheight=1;
		  gbc.weightx=0.5;
		  gbc.weighty=0.0;
		  gbc.anchor=GridBagConstraints.CENTER;
		  gbc.insets=new Insets(0, 0, 40, 0);
		  
		  mainPanel.add(fileLabel, gbc);
		  
		  
		//---->>Implement fileChooser button in main panel using with GridBagLayout 
		  fileChooserButton =new JButton("Seç");
		  fileChooserButton.setBackground(Color.BLACK);
		  fileChooserButton.setPreferredSize(new Dimension(70, 20));
		  
		  gbc.gridx=0;
		  gbc.gridy=1;
		  gbc.gridwidth=1;
		  gbc.gridheight=1;
		  gbc.weightx=0.5;
		  gbc.weighty=0.0;
		  gbc.insets=new Insets(0, 0, 10, 0);
		  
		  mainPanel.add(fileChooserButton, gbc);
		  
		  fileChooserButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				controller.chooserListener(fileChooserButton, fileLabel, selectedFile, choosingFileName);
				
			}
		});
		  
		//---->>Implement excelExport button in main panel using with GridBagLayout 
		  exportExcelButton =new JButton("Aktar");
		  exportExcelButton.setBackground(Color.BLACK);
		  exportExcelButton.setPreferredSize(new Dimension(70, 20));
		  
		  gbc.gridx=1;
		  gbc.gridy=1;
		  gbc.gridwidth=1;
		  gbc.gridheight=1;
		  gbc.weightx=0.5;
		  gbc.weighty=0.0;
		  
		  mainPanel.add(exportExcelButton, gbc);
		  
		  exportExcelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				controller.exportListener();
				
			}
		});
		  
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		pressedMouseX=e.getX();
		pressedMouseY=e.getY();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		int currentMouseLocX=e.getX();
		int currentMouseLocY=e.getY();
		
		dragX=dragX+currentMouseLocX-pressedMouseX;
		dragY=dragY+currentMouseLocY-pressedMouseY;
		
		mainFrame.setBounds(dragX, dragY, 300, 120);
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
