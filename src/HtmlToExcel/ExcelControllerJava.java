package HtmlToExcel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ExcelControllerJava  {
	
	private JFrame mainFrame;
	private JButton fileChooserButton;
	private JLabel fileLabel;
	private String choosingFileName,xmlFindTag;
	private File selectedFile;

	ExcelModelJava model=new ExcelModelJava();
	
	
	//Call exitButton from View and then transmit to Model class 
	public void exitListener()
	{
		model.exitAction();
	}

	
	public JFrame minimizeListener(JFrame mainFrame)
	{
		this.mainFrame=mainFrame;
		
		return model.minimizeAction(mainFrame);
	}
	
	public void chooserListener(JButton fileChooserButton,JLabel fileLabel,File selectedFile,String choosingFileName)
	{
		this.fileChooserButton=fileChooserButton;
		this.fileLabel=fileLabel;
		this.choosingFileName=choosingFileName;
		this.selectedFile=selectedFile;
		
		model.chooserAction(fileChooserButton, fileLabel,selectedFile, choosingFileName);
		
	}
	
	public void exportListener()
	{
//		this.selectedFile=selectedFile;
		model.exportAction();
		
	}
	
	public String callFindPath(String xmlFindTag) throws ParserConfigurationException, SAXException, IOException
	{
		this.xmlFindTag=xmlFindTag;
		
		return model.findFilePath(xmlFindTag);
	}
	
}	

