package automation;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import resources.Screenshot;

public class DataEntry {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		JFrame frmOpt = new JFrame(); // We are declaring the frame
		frmOpt.setAlwaysOnTop(true);// This is the line for displaying it above all windows

		Thread.sleep(1000);
		String s = "<html>Enter Row number for Applicant";

		JLabel label = new JLabel(s);
		JTextPane jtp = new JTextPane();
		jtp.setSize(new Dimension(480, 10));
		jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
		label.setFont(new Font("Arial", Font.BOLD, 26));
		UIManager.put("OptionPane.minimumSize", new Dimension(700, 300));
		UIManager.put("TextField.font", new FontUIResource(new Font("Verdana", Font.BOLD, 20)));
		// Getting Input from user

		String option = JOptionPane.showInputDialog(frmOpt, label);

		int excelrow = Integer.parseInt(option);
		int row = excelrow -1;
		
		
		double randomNumOne, randomNumTwo, randomNumThree, randomNumFour;
		
		String randomNumRange, efsCvScoreRange;

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\main\\resources\\Excel\\Fusion_GDS_TestCustomers.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);

		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("Test");

		// Storing random numbers as separate variable

		ArrayList<String> efsCvScoreList = new ArrayList<String>();

		for (int x = 2; x <= 7; x++) {
			efsCvScoreRange = sheet.getRow(row).getCell(x).getStringCellValue();
			efsCvScoreList.add(efsCvScoreRange);	
			
		}
		System.out.println(efsCvScoreList);
		
		String lastname = efsCvScoreList.get(0);
		String firstname = efsCvScoreList.get(1);
		String address = efsCvScoreList.get(2);
		String city = efsCvScoreList.get(3);
		String prov = efsCvScoreList.get(4);
		String postalcode = efsCvScoreList.get(5);
		
		
		
		int phone = (int) sheet.getRow(row).getCell(8).getNumericCellValue();
		System.out.println(phone);

		String dob = sheet.getRow(row).getCell(9).getStringCellValue();
		System.out.println(dob);

	
		
		
		
	}

}
