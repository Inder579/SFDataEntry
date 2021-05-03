package automation;

import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import resources.BrowserDriver;

public class Fusion extends BrowserDriver {

	public static int attemptNo = 0;
	public String screenShotPathforInterestRate;
	public WebDriver driver;
	int cvScore, BehaviourScore;
	public String ActualIncome, appType, loanType, splloanType, Province, ApplicationID, MorgagePayment;
	public String TotalIncomeAmount, IntRate, cabKey, qlaStrategy, applicationType;
	public double TotalIncome, RemainingIncome, TotalDebt, ExpectedQLA, ExpInt, SPLltv, Maxltv, HomeEquity, PropertyVal;

	String lowefs, highefs, Prov, provinceGroup, bkStrategy, ps = "", code = null, propertyType = "",
			propertyLocation = "";
	double lef, hef, calRemIn, QLA, InterestRate, remIn, remInNaPrev, remInNaAfter, LtvMax, ActualMaxHA, ExpectedMaxHA;
	int fcol, lcol, col, coldiff, rowNum, RiskGroup, SPLTotalDebt, lastNumRow, bkDecreaseAmount;
	String stringSplit[], Strategy;

	String lastname, firstname, address, city, prov, postalcode, dob, clprod, loanpurpose, hearabout, Referral,
			livingsituation, email, lengthofstay;

	String phone, loanamount, landlordname, landlordnumber, Employername, Employerposition, Incomeamt, Incomefreq,
			Employmentstatus, Supervisorname, Supervisornumber, lengthofemployment, previousemployer,
			lengthpreviousemployer, preferedLang;

	@BeforeTest
	public void initialize1() throws IOException {

		driver = browser();

	}

	@Test()
	public void m1() throws Exception {

		// Login as Admin
		loginAsAdmin();

		// Login as FSR-Application
		// loginAsFSR();
		Thread.sleep(4000);

	}

	public void loginAsAdmin() throws InterruptedException, IOException {
		 driver.get(prop.getProperty("sfUrl"));
		// driver.get("https://goeasy--goeasyqasb.lightning.force.com/lightning/r/genesis__Applications__c/a5y1h0000002pngAAA/view");
		//driver.get("https://goeasy--goeasyqasb.my.salesforce.com/");
		Thread.sleep(2000);
		WebDriverWait waitLoad = new WebDriverWait(driver, 360, 0000);
		waitLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("username"))));
		// driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(decodeString(prop.getProperty("AdminEmail")));
		driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("AdminEmailFusion"));
		Thread.sleep(2000);
		// driver.findElement(By.cssSelector(prop.getProperty("password"))).sendKeys(decodeString(prop.getProperty("AdminPassword")));
		driver.findElement(By.cssSelector(prop.getProperty("password")))
				.sendKeys(prop.getProperty("AdminPasswordFusion"));
		driver.findElement(By.xpath(prop.getProperty("clicklogin"))).click();

		// https://goeasy--goeasyqasb.lightning.force.com/lightning/r/genesis__Applications__c/a5y1h0000002C2NAAU/view
		System.out.println("Logged in As Admin");

		data();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='100%'");
		
		WebDriverWait wait2 = new WebDriverWait(driver, 360, 0000);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("iframe2"))));
		WebElement iframe2 = driver.findElement(
				By.xpath("//*[@id=\"brandBand_2\"]/div/div/div[2]/div/div/div/force-aloha-page/div/iframe"));
		driver.switchTo().frame(iframe2);

		// newappicant
		WebDriverWait newappicantwait = new WebDriverWait(driver, 360, 0000);
		newappicantwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("newappicant"))));
		driver.findElement(By.xpath(prop.getProperty("newappicant"))).click();

		// clproduct
		WebDriverWait clproductwait = new WebDriverWait(driver, 360, 0000);
		clproductwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("clproduct"))));
		driver.findElement(By.xpath(prop.getProperty("clproduct"))).click();

		// searchcl
		WebDriverWait searchclwait = new WebDriverWait(driver, 360, 0000);
		searchclwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("searchcl"))));
		driver.findElement(By.xpath(prop.getProperty("searchcl"))).sendKeys(clprod);
		Thread.sleep(3000);
		WebDriverWait selectclwait = new WebDriverWait(driver, 360, 0000);
		selectclwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("selectcl"))));
		driver.findElement(By.xpath(prop.getProperty("selectcl"))).click();

		// loanamount

		WebDriverWait loanamountwait = new WebDriverWait(driver, 360, 0000);
		loanamountwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("loanamount"))));
		driver.findElement(By.xpath(prop.getProperty("loanamount"))).sendKeys(loanamount);

		// loanpurpose
		WebDriverWait purposewait = new WebDriverWait(driver, 360, 0000);
		purposewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("purpose"))));
		driver.findElement(By.xpath(prop.getProperty("purpose"))).click();
		WebDriverWait searchlpwait = new WebDriverWait(driver, 360, 0000);
		searchlpwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("searchlp"))));
		driver.findElement(By.xpath(prop.getProperty("searchlp"))).sendKeys(loanpurpose);
		Thread.sleep(4000);
		WebDriverWait selectlpwait = new WebDriverWait(driver, 360, 0000);
		selectlpwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("selectlp"))));
		driver.findElement(By.xpath(prop.getProperty("selectlp"))).click();

		//preferedLang
		WebDriverWait preferedLangwait = new WebDriverWait(driver, 360, 0000);
		preferedLangwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("preferedLang"))));
		Select pl = new Select(driver.findElement(By.xpath(prop.getProperty("preferedLang"))));
		pl.selectByVisibleText(preferedLang);
		
		// hearabout
		WebDriverWait hearaboutwait = new WebDriverWait(driver, 360, 0000);
		hearaboutwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("hearabout"))));
		Select hb = new Select(driver.findElement(By.xpath(prop.getProperty("hearabout"))));
		hb.selectByVisibleText(hearabout);
		if (hearabout.contains("Referral")) {
			Thread.sleep(2000);
			WebDriverWait Referralwait = new WebDriverWait(driver, 360, 0000);
			Referralwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Referral"))));
			driver.findElement(By.xpath(prop.getProperty("Referral"))).sendKeys(Referral);
		}
		// 
		WebDriverWait residentwait = new WebDriverWait(driver, 360, 0000);
		residentwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("resident"))));
		Select residentfr = new Select(driver.findElement(By.xpath(prop.getProperty("resident"))));
		residentfr.selectByVisibleText("Yes");
//
		WebDriverWait citizenwait = new WebDriverWait(driver, 360, 0000);
		citizenwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("citizen"))));
		Select citizenfr = new Select(driver.findElement(By.xpath(prop.getProperty("citizen"))));
		citizenfr.selectByVisibleText("Citizen");
		

		// App deatils
		WebDriverWait firstnamewait = new WebDriverWait(driver, 360, 0000);
		firstnamewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("firstname"))));
		driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(firstname);

		WebDriverWait lastnamewait = new WebDriverWait(driver, 360, 0000);
		lastnamewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("lastname"))));
		driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(lastname);

		WebDriverWait dobwait = new WebDriverWait(driver, 360, 0000);
		dobwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("dob"))));
		driver.findElement(By.xpath(prop.getProperty("dob"))).sendKeys(dob);
		driver.findElement(By.xpath(prop.getProperty("firstnameclick"))).click();
		
		//married
				WebDriverWait marriedwait = new WebDriverWait(driver, 360, 0000);
				marriedwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("married"))));
				Select marriedfr = new Select(driver.findElement(By.xpath(prop.getProperty("married"))));
				marriedfr.selectByVisibleText("Married");

		// String phonenumber = Integer.toString(phone);
		WebDriverWait phonewait = new WebDriverWait(driver, 360, 0000);
		phonewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("phone"))));
		driver.findElement(By.xpath(prop.getProperty("phone"))).sendKeys(phone);

		// livingsituation

		if (livingsituation.equalsIgnoreCase("Rent")) {
			WebDriverWait livingsituationwait = new WebDriverWait(driver, 360, 0000);
			livingsituationwait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("livingsituationRent"))));
			driver.findElement(By.xpath(prop.getProperty("livingsituationRent"))).click();
		} else if (livingsituation.contains("Own Home")) {
			WebDriverWait livingsituationwait = new WebDriverWait(driver, 360, 0000);
			livingsituationwait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("livingsituationOwn"))));
			driver.findElement(By.xpath(prop.getProperty("livingsituationOwn"))).click();
		} else if (livingsituation.contains("Rent-Free / Living with Friends/Family")) {
			WebDriverWait livingsituationwait = new WebDriverWait(driver, 360, 0000);
			livingsituationwait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("livingsituationFree"))));
			driver.findElement(By.xpath(prop.getProperty("livingsituationFree"))).click();
		}

		// email
		WebDriverWait emailwait = new WebDriverWait(driver, 360, 0000);
		emailwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("email"))));
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email);

		WebDriverWait addresswait = new WebDriverWait(driver, 360, 0000);
		addresswait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("address"))));
		driver.findElement(By.xpath(prop.getProperty("address"))).sendKeys(address);

		WebDriverWait citywait = new WebDriverWait(driver, 360, 0000);
		citywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("city"))));
		driver.findElement(By.xpath(prop.getProperty("city"))).sendKeys(city);

		WebDriverWait provwait = new WebDriverWait(driver, 360, 0000);
		provwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("prov"))));
		Select province = new Select(driver.findElement(By.xpath(prop.getProperty("prov"))));
		province.selectByVisibleText(prov);

		WebDriverWait postalcodewait = new WebDriverWait(driver, 360, 0000);
		postalcodewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("postalcode"))));
		driver.findElement(By.xpath(prop.getProperty("postalcode"))).sendKeys(postalcode);

		WebDriverWait lengthofstaywait = new WebDriverWait(driver, 360, 0000);
		lengthofstaywait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("lengthofstay"))));
		Select length = new Select(driver.findElement(By.xpath(prop.getProperty("lengthofstay"))));
		length.selectByVisibleText(lengthofstay);

		// landlordname, landlordnumber
		if (livingsituation.contains("Rent")) {
			WebDriverWait landlordnamewait = new WebDriverWait(driver, 360, 0000);
			landlordnamewait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("landlordname"))));
			driver.findElement(By.xpath(prop.getProperty("landlordname"))).sendKeys(landlordname);

			WebDriverWait landlordnumberwait = new WebDriverWait(driver, 360, 0000);
			landlordnumberwait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("landlordnumber"))));
			driver.findElement(By.xpath(prop.getProperty("landlordnumber"))).sendKeys(landlordnumber);
		}
		// Employername, Employerposition
		WebDriverWait Employernamewait = new WebDriverWait(driver, 360, 0000);
		Employernamewait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Employername"))));
		driver.findElement(By.xpath(prop.getProperty("Employername"))).sendKeys(Employername);

		WebDriverWait Employerpositionwait = new WebDriverWait(driver, 360, 0000);
		Employerpositionwait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Employerposition"))));
		driver.findElement(By.xpath(prop.getProperty("Employerposition"))).sendKeys(Employerposition);

		// Incomeamt
		WebDriverWait Incomeamtwait = new WebDriverWait(driver, 360, 0000);
		Incomeamtwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Incomeamt"))));
		driver.findElement(By.xpath(prop.getProperty("Incomeamt"))).sendKeys(Incomeamt);

		// Incomefreq
		WebDriverWait Incomefreqwait = new WebDriverWait(driver, 360, 0000);
		Incomefreqwait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Incomefreq"))));
		Select incomefr = new Select(driver.findElement(By.xpath(prop.getProperty("Incomefreq"))));
		incomefr.selectByVisibleText(Incomefreq);

		// Employmentstatus
		WebDriverWait Employmentstatuswait = new WebDriverWait(driver, 360, 0000);
		Employmentstatuswait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Employmentstatus"))));
		Select empst = new Select(driver.findElement(By.xpath(prop.getProperty("Employmentstatus"))));
		empst.selectByVisibleText(Employmentstatus);

		// Supervisorname, Supervisornumber
		WebDriverWait Supervisornamewait = new WebDriverWait(driver, 360, 0000);
		Supervisornamewait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Supervisorname"))));
		driver.findElement(By.xpath(prop.getProperty("Supervisorname"))).sendKeys(Supervisorname);

		WebDriverWait Supervisornumberwait = new WebDriverWait(driver, 360, 0000);
		Supervisornumberwait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Supervisornumber"))));
		driver.findElement(By.xpath(prop.getProperty("Supervisornumber"))).sendKeys(Supervisornumber);

		// lengthofemployment
		WebDriverWait lengthofemploymentwait = new WebDriverWait(driver, 360, 0000);
		lengthofemploymentwait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("lengthofemployment"))));
		Select lempy = new Select(driver.findElement(By.xpath(prop.getProperty("lengthofemployment"))));
		lempy.selectByVisibleText(lengthofemployment);

		// previousemployer,lengthpreviousemployer
		if (lengthofemployment.contains("Less than 6 months") || lengthofemployment.contains("6-12 Months")) {
			Thread.sleep(1000);
			WebDriverWait previousemployerwait = new WebDriverWait(driver, 360, 0000);
			previousemployerwait.until(
					ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("previousemployer"))));
			driver.findElement(By.xpath(prop.getProperty("previousemployer"))).sendKeys(previousemployer);

			WebDriverWait lengthpreviousemployerwait = new WebDriverWait(driver, 360, 0000);
			lengthpreviousemployerwait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath(prop.getProperty("lengthpreviousemployer"))));
			Select prlempy = new Select(driver.findElement(By.xpath(prop.getProperty("lengthpreviousemployer"))));
			prlempy.selectByVisibleText(lengthpreviousemployer);
		}

	}

	public void data() throws IOException, InterruptedException {
		JFrame frmOpt = new JFrame(); // We are declaring the frame
		frmOpt.setAlwaysOnTop(true);// This is the line for displaying it above all windows

		Thread.sleep(1000);
		String s = "<html>Enter Row number for Applicant";

		JLabel label = new JLabel(s);
		JTextPane jtp = new JTextPane();
		jtp.setSize(new Dimension(480, 10));
		jtp.setPreferredSize(new Dimension(480, jtp.getPreferredSize().height));
		label.setFont(new Font("Arial", Font.BOLD, 20));
		UIManager.put("OptionPane.minimumSize", new Dimension(500, 200));
		UIManager.put("TextField.font", new FontUIResource(new Font("Verdana", Font.BOLD, 20)));
		// Getting Input from user

		String option = JOptionPane.showInputDialog(frmOpt, label);

		int excelrow = Integer.parseInt(option);
		int row = excelrow - 1;

		String efsCvScoreRange;

		
		File file = new File(
				System.getProperty("user.dir") + "\\src\\main\\resources\\Excel\\Fusion_GDS_TestCustomers_new.xlsx");

		FileInputStream inputStream = new FileInputStream(file);

		Workbook workbook = new XSSFWorkbook(inputStream);

		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheet("GDS");

		// Storing random numbers as separate variable

		ArrayList<String> efsCvScoreList = new ArrayList<String>();

		for (int x = 9; x <= 14; x++) {
			efsCvScoreRange = sheet.getRow(row).getCell(x).getStringCellValue();
			efsCvScoreList.add(efsCvScoreRange);

		}
		System.out.println(efsCvScoreList);

		lastname = efsCvScoreList.get(0);
		firstname = efsCvScoreList.get(1);
		address = efsCvScoreList.get(2);
		city = efsCvScoreList.get(3);
		String province = efsCvScoreList.get(4);
		postalcode = efsCvScoreList.get(5);

		phone = sheet.getRow(row).getCell(15).getStringCellValue();
		dob = sheet.getRow(row).getCell(16).getStringCellValue();

		loanamount = sheet.getRow(row).getCell(2).getStringCellValue();
		String ls = sheet.getRow(row).getCell(17).getStringCellValue();
		email = sheet.getRow(row).getCell(18).getStringCellValue();

		if (ls.equalsIgnoreCase("Rent")) {
			livingsituation = "Rent";
		} else if (ls.contains("Own")) {
			livingsituation = "Own Home";
		} else if (ls.contains("Rent-Free")) {
			livingsituation = "Rent-Free / Living with Friends/Family";
		}

		if (province.equals("ON")) {
			prov = "ON - Ontario";
		} else if (province.equals("AB")) {
			prov = "AB - Alberta";
		} else if (province.equals("BC")) {
			prov = "BC - British Columbia";
		} else if (province.equals("MB")) {
			prov = "MB - Manitoba";
		} else if (province.equals("NB")) {
			prov = "NB - New Brunswick";
		} else if (province.equals("NL")) {
			prov = "NL - Newfoundland and Labrador";
		} else if (province.equals("NS")) {
			prov = "NS - Nova Scotia";
		} else if (province.equals("NT")) {
			prov = "NT - Northwest Territories";
		} else if (province.equals("NU")) {
			prov = "NU - Nunavut";
		} else if (province.equals("PE")) {
			prov = "PE - Prince Edward Island";
		} else if (province.equals("QC")) {
			prov = "QC - Quebec";
		} else if (province.equals("SK")) {
			prov = "SK - Saskatchewan";
		} else if (province.equals("YT")) {
			prov = "YT - Yukon";
		}

		String clp = sheet.getRow(row).getCell(1).getStringCellValue();

		if (clp.contains("Personal")) {
			clprod = "Personal Loan";
		} else if (clp.contains("Pay 360")) {
			clprod = "Pay 360";
		} else if (clp.contains("Mogo")) {
			clprod = "Mogo";
		} else if (clp.contains("Payb")) {
			clprod = "Paybright";
		}

		// loanpurpose
		String lp = sheet.getRow(row).getCell(3).getStringCellValue();
		preferedLang=sheet.getRow(row).getCell(4).getStringCellValue();

		if (lp.contains("Bills and Living")) {
			loanpurpose = "Bills and Living Expenses";
		} else if (lp.contains("Home Repair")) {
			loanpurpose = "Home Repair";
		} else if (lp.contains("Leisure Purposes")) {
			loanpurpose = "Travel and Leisure";
		} else if (lp.contains("Other")) {
			loanpurpose = "Other";
		} else if (lp.contains("Auto Repair")) {
			loanpurpose = "Auto Repair";
		} else if (lp.contains("Debt Consolidation")) {
			loanpurpose = "Debt Consolidation";
		} else if (lp.contains("Education Expenses")) {
			loanpurpose = "Education Expenses";
		} else if (lp.contains("Health Care Expenses")) {
			loanpurpose = "Health Care Expenses";
		} else if (lp.contains("Purchase an Item")) {
			loanpurpose = "Purchase an Item";
		}

		// Hear about us?
		String hear = sheet.getRow(row).getCell(5).getStringCellValue();

		if (hear.contains("Canada Drives")) {
			hearabout = "Canada Drives";
		} else if (hear.contains("DirectMail/Letter")) {
			hearabout = "Direct Mail/Letter";
		} else if (hear.contains("EasyHome")) {
			hearabout = "EasyHome";
		} else if (hear.contains("Email")) {
			hearabout = "Email";
		} else if (hear.contains("Flyer")) {
			hearabout = "Flyer";
		} else if (hear.contains("Newspaper print")) {
			hearabout = "Newspaper print advertisement";
		} else if (hear.contains("Online advertisement")) {
			hearabout = "Online advertisement";
		} else if (hear.contains("Radio")) {
			hearabout = "Radio";
		} else if (hear.contains("Referral")) {
			hearabout = "Referral";
			Referral = sheet.getRow(row).getCell(6).getStringCellValue();
		} else if (hear.contains("TV")) {
			hearabout = "TV";
		} else if (hear.contains("Walk in")) {
			hearabout = "Walk in/Retail sign";
		}

		// Length Of Stay At Current Address
		String length = sheet.getRow(row).getCell(19).getStringCellValue();

		if (length.contains("6-12 Months")) {
			lengthofstay = "6-12 Months";
		} else if (length.contains("Less than 6 months")) {
			lengthofstay = "Less than 6 months";
		} else if (length.contains("1 Year")) {
			lengthofstay = "1 Year";
		} else if (length.contains("2 Year")) {
			lengthofstay = "2 Years";
		} else if (length.contains("3 Years")) {
			lengthofstay = "3 Years";
		} else if (length.contains("4 Years")) {
			lengthofstay = "4 Years";
		} else if (length.contains("5+ Years")) {
			lengthofstay = "5+ Years";
		}

		// landlordname
		if (ls.contains("Rent")) {
			landlordname = sheet.getRow(row).getCell(20).getStringCellValue();
			landlordnumber = sheet.getRow(row).getCell(21).getStringCellValue();
		}

		// Employername
		Employername = sheet.getRow(row).getCell(22).getStringCellValue();
		Employerposition = sheet.getRow(row).getCell(23).getStringCellValue();
		Incomeamt = sheet.getRow(row).getCell(24).getStringCellValue();

		// Incomefreq
		String incomef = sheet.getRow(row).getCell(25).getStringCellValue();

		if (incomef.equalsIgnoreCase("Weekly")) {
			Incomefreq = "WEEKLY";
		} else if (incomef.contains("Bi-Weekly")) {
			Incomefreq = "BI-WEEKLY";
		} else if (incomef.contains("Semi-Monthly")) {
			Incomefreq = "SEMI-MONTHLY-PD";
		} else if (incomef.contains("Monthly")) {
			Incomefreq = "MONTHLY";
		}

		// Employmentstatus
		String Employsts = sheet.getRow(row).getCell(26).getStringCellValue();

		if (Employsts.contains("FULL-TIME")) {
			Employmentstatus = "Full-Time";
		} else if (Employsts.contains("PART-TIME")) {
			Employmentstatus = "Part-Time";
		} else if (Employsts.contains("SELF-EMPLOYED")) {
			Employmentstatus = "Self-Employed";
		} else if (Employsts.contains("SEASONAL")) {
			Employmentstatus = "Seasonal";
		} else if (Employsts.contains("UNEMPLOYED")) {
			Employmentstatus = "Unemployed (EI)";
		} else if (Employsts.contains("DISABILITY")) {
			Employmentstatus = "Disability";
		} else if (Employsts.contains("Maternity Leave (EI)")) {
			Employmentstatus = "Maternity Leave (EI)";
		} else if (Employsts.contains("Retired (Pension)")) {
			Employmentstatus = "Retired (Pension)";
		} else if (Employsts.contains("Social Assistance (Welfare)")) {
			Employmentstatus = "Social Assistance (Welfare)";
		}

		// Supervisorname, Supervisornumber
		Supervisorname = sheet.getRow(row).getCell(27).getStringCellValue();
		Supervisornumber = sheet.getRow(row).getCell(28).getStringCellValue();

		// lengthofemployment
		String lenghtofemp = sheet.getRow(row).getCell(29).getStringCellValue();
		if (lenghtofemp.contains("Less than 6 months")) {
			lengthofemployment = "Less than 6 months";
		} else if (lenghtofemp.contains("6-12 Months")) {
			lengthofemployment = "6-12 Months";
		} else if (lenghtofemp.contains("1 Year")) {
			lengthofemployment = "1 Year";
		} else if (lenghtofemp.contains("2 Years")) {
			lengthofemployment = "2 Years";
		} else if (lenghtofemp.contains("3 Years")) {
			lengthofemployment = "3 Years";
		} else if (lenghtofemp.contains("4 Years")) {
			lengthofemployment = "4 Years";
		} else if (lenghtofemp.contains("5+ Years")) {
			lengthofemployment = "5+ Years";
		}
		if (lengthofemployment.contains("Less than 6 months") || lengthofemployment.contains("6-12 Months")) {
			// previousemployer,lengthpreviousemployer
			previousemployer = sheet.getRow(row).getCell(30).getStringCellValue();

			String lengthpv = sheet.getRow(row).getCell(31).getStringCellValue();

			if (lengthpv.contains("6-12 Months")) {
				lengthpreviousemployer = "6-12 Months";
			} else if (lengthpv.contains("Less than 6 months")) {
				lengthpreviousemployer = "Less than 6 months";
			} else if (lengthpv.contains("1 Year")) {
				lengthpreviousemployer = "1 Year";
			} else if (lengthpv.contains("2 Years")) {
				lengthpreviousemployer = "2 Years";
			} else if (lengthpv.contains("3 Years")) {
				lengthpreviousemployer = "3 Years";
			} else if (lengthpv.contains("4 Years")) {
				lengthpreviousemployer = "4 Years";
			} else if (lengthpv.contains("5+ Years")) {
				lengthpreviousemployer = "5+ Years";
			}

		}

	}

	public void loginAsFSR() throws InterruptedException {
		Thread.sleep(5000);
		WebDriverWait waitSetup = new WebDriverWait(driver, 360, 0000);
		waitSetup.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("Setup"))));
		driver.findElement(By.xpath(prop.getProperty("Setup"))).click();
		Thread.sleep(4000);
		WebDriverWait waitLoad = new WebDriverWait(driver, 360, 0000);
		waitLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("clicksetup"))));
		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("clicksetup"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, 360, 0000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("clickfsr"))));

		driver.findElement(By.xpath(prop.getProperty("clickfsr"))).click();
		Thread.sleep(3000);
		WebDriverWait wait1 = new WebDriverWait(driver, 360, 0000);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("fsrmenubutton"))));
		driver.findElement(By.xpath(prop.getProperty("fsrmenubutton"))).click();
		Thread.sleep(3000);
		WebDriverWait wait2 = new WebDriverWait(driver, 360, 0000);
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("userdetail"))));
		driver.findElement(By.xpath(prop.getProperty("userdetail"))).click();
		Thread.sleep(3000);
		WebDriverWait wait3 = new WebDriverWait(driver, 360, 0000);
		wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("fsrloginbutton"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(prop.getProperty("fsrloginbutton"))).click();
		Thread.sleep(2000);
		System.out.println("Logged in As FSR");
	}

}
/*
 * WebDriverWait wait = new WebDriverWait(driver, 360, 0000);
 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.
 * getProperty("iframe1")))); WebElement iframe1= driver.findElement(By.xpath(
 * "//*[@id=\"brandBand_2\"]/div/div/div/div/force-aloha-page/div/iframe"));
 * 
 * //*[@id="brandBand_2"]/div/div/div[2]/div/div/div/force-aloha-page/div/iframe
 * //*[@id="brandBand_2"]/div/div/div/div/div/div/force-aloha-page/div/iframe
 * driver.switchTo().frame(iframe1); System.out.println("Switched Iframe1");
 */
/*
 * WebDriverWait wait2 = new WebDriverWait(driver, 360, 0000);
 * Thread.sleep(5000);
 * wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.
 * getProperty("iframe2")))); WebElement iframe2=
 * driver.findElement(By.xpath("//*[@id=\"deal-dashboard-iframe\"]"));
 * driver.switchTo().frame(iframe2); System.out.println("Switched Iframe2");
 * Thread.sleep(5000); WebDriverWait waitSetup = new WebDriverWait(driver, 360,
 * 0000);
 * waitSetup.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.
 * getProperty("Loan")))); //
 * driver.findElement(By.xpath(prop.getProperty("Loan"))).click();
 * 
 * Thread.sleep(5000);
 * 
 * driver.findElement(By.xpath(prop.getProperty("dots"))).click();
 * 
 * WebDriverWait waitSetup2 = new WebDriverWait(driver, 360, 0000);
 * waitSetup2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.
 * getProperty("OfferSelection"))));
 * driver.findElement(By.xpath(prop.getProperty("OfferSelection"))).click();
 * 
 * driver.switchTo().defaultContent(); driver.switchTo().frame(iframe1);
 * System.out.println("Switched Iframe1"); WebElement iframe3=
 * driver.findElement(By.xpath("//*[@id=\"top-level-dialog\"]/iframe"));
 * driver.switchTo().frame(iframe3); System.out.println("Switched Iframe3");
 * 
 * WebDriverWait waitOfferdetails = new WebDriverWait(driver, 360, 0000);
 * waitOfferdetails.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
 * (prop.getProperty("Offerdetails"))));
 * driver.findElement(By.xpath(prop.getProperty("Offerdetails"))).click();
 * 
 * //Total Income WebDriverWait totalincome = new WebDriverWait(driver, 360,
 * 0000);
 * totalincome.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop
 * .getProperty("TotalIncomeFusion")))); String
 * totalincomeFusion=driver.findElement(By.xpath(prop.getProperty(
 * "TotalIncomeFusion"))).getText();
 * System.out.println("Total Income ="+totalincomeFusion); //Total Debt String
 * totaldebtFusion=driver.findElement(By.xpath(prop.getProperty(
 * "TotalDebtFusion"))).getText();
 * System.out.println("Total Debt ="+totaldebtFusion); //UplStrategyFusion
 * String UplStrategyFusion=driver.findElement(By.xpath(prop.getProperty(
 * "UplStrategyFusion"))).getText();
 * System.out.println("UplStrategyFusion ="+UplStrategyFusion);
 * 
 * 
 * 
 * 
 * 
 * Thread.sleep(5000); driver.close();
 */
