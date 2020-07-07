package HtmlToExcel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class ExcelExport {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
	{
		
		ExcelViewJava view=new ExcelViewJava();
		view.displayFrame();
		
	}
	
}
