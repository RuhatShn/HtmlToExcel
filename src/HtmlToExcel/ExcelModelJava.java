package HtmlToExcel;

import java.awt.Frame;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;


public class ExcelModelJava {

	private JFrame mainFrame;
	private JButton fileChooserButton;
	private JLabel fileLabel;
	private String choosingFileName,xmlFindTag;
	private File selectedFile;
	
	
	ExecutorService executorService=Executors.newFixedThreadPool(2);
	
	public void exitAction()
	{
		System.exit(0);
	}
	
	
	public JFrame minimizeAction(JFrame mainFrame)
	{
		this.mainFrame=mainFrame;
		
		mainFrame.setState(Frame.ICONIFIED);
		
		return mainFrame ;
	}
	
	
	public void chooserAction(JButton fileChooserButton,JLabel fileLabel,File selectedFile,String choosingFileName )
	{
		this.fileChooserButton=fileChooserButton;
		this.fileLabel=fileLabel;
		this.choosingFileName=choosingFileName;
		this.selectedFile=selectedFile;
		
		executorService.submit(chooseFile);
	}
	
	
	Runnable chooseFile=()->
	{
		JFileChooser chooser=null;
		
		try 
		{
			chooser=new JFileChooser(findFilePath("htmlFile"));
		}
		catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		chooser.setFileFilter(new FileNameExtensionFilter("html extension", "html"));
		chooser.showSaveDialog(null);
		
		
		selectedFile=chooser.getSelectedFile();
		choosingFileName=selectedFile.getName().split("\\.")[0];
		
		fileLabel.setText(choosingFileName);
		
	};
	
	
	public void exportAction()
	{
		executorService.submit(exportFile);
	}
	
	Runnable exportFile=()->
	{
	   
        ArrayList<String> al=new ArrayList<String>(); 
		
	    String pathname=selectedFile.getAbsolutePath().replace("\\", "\\\\");
	 
		File input=new File(pathname);
		
		Document htmlFile;
		
		try {
			htmlFile = Jsoup.parse(input, "ISO-8859-9");
			
			String tagName = "";
			
			for(int i=0;i<=16;i++)
			{
				if(i==2)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String companyName=tagName.substring(12);
					al.add(companyName);
				}
				
				else if(i==3)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String companyNo=tagName.substring(16);
					al.add(companyNo);
				}
				
				else if(i==5)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverName=tagName.substring(11);
					al.add(recieverName);
				}
				
				else if(i==6)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverAdress=tagName.substring(6);
					al.add(recieverAdress);
				}
				
				else if(i==7)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverStreet=tagName.substring(8);
					al.add(recieverStreet);
				}
				
				else if(i==8)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverProvince=tagName.substring(10);
					al.add(recieverProvince);
				}
				
				else if(i==9)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverNo=tagName.substring(11);
					al.add(recieverNo);
				}
				
				else if(i==10)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverMobileNo=tagName.substring(13);
					al.add(recieverMobileNo);
				}
				
				else if(i==11)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String recieverPostCode=tagName.substring(10);
					al.add(recieverPostCode);
				}
				
				else if(i==13)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String sellCode=tagName.substring(11);
					al.add(sellCode);
				}
				
				else if(i==14)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String payInfo=tagName.substring(21);
					al.add(payInfo);
				}
				
				else if(i==15)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String groupDeliverCode=tagName.substring(19);
					al.add(groupDeliverCode);
				}
				
				else if(i==16)
				{
					tagName=htmlFile.getElementsByIndexEquals(i).text();
					String info=tagName.substring(6);
					al.add(info);
				}
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		 String excelPath="C:\\Users\\abcda\\Desktop\\ExcelAktar\\KargoTakip.xlsx";
			
			FileInputStream fis;
			try {
				fis = new FileInputStream(new File(findFilePath("excelFile")));
				
				XSSFWorkbook wb=new XSSFWorkbook(fis);
				
				XSSFSheet sheet=wb.getSheetAt(0);
				
				int rowCount=sheet.getLastRowNum()+1;
				
				Row row=sheet.createRow(rowCount);
				
				int columnCount=0;
				
				for(String str:al)
				{
					Cell cell=row.createCell(columnCount++);
					
					cell.setCellValue(str);
				}
				
				FileOutputStream fos;
				
					fos = new FileOutputStream(findFilePath("excelFile"));
					
					wb.write(fos);
					wb.close();
					fos.close();
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SAXException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		};
		
		
	public String findFilePath(String xmlFindTag) throws ParserConfigurationException, SAXException, IOException
	{
		this.xmlFindTag=xmlFindTag;
		
		File pathFile=new File("../exportExcel/excelExport.xml");
		
		DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		
		DocumentBuilder dBuilder=dbf.newDocumentBuilder();
		
		org.w3c.dom.Document doc = dBuilder.parse(pathFile);
		
		String fileAndIcon=doc.getElementsByTagName(xmlFindTag).item(0).getTextContent();
		
		System.out.println(fileAndIcon);
		
		return fileAndIcon;
	}
}
