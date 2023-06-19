package com.E_POC2.TestCases;


import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.E_POC2.DataBaseConfiguration.DataBaseConnection;
import com.E_POC2.PageObject.PowerBIDashBoard;
import com.E_POC2.TestBase.TestBase;
import com.E_POC2.TestBase.TestDataManipulation;
import com.E_POC2.Utility.Demo_ExtentReport;
import com.E_POC2.Utility.EmailOfTestExecution;
import com.E_POC2.Utility.JiraPolicy;
import com.E_POC2.Utility.ReadFromExcel;
import com.E_POC2.Utility.setResultToExcel;

public class PowerBIDashBoardTest extends TestBase{

	DataBaseConnection dbCon=new DataBaseConnection();

	static List<String> dropDownHeading=new ArrayList<>();
	static List<String> testData=new ArrayList<>();
	static List<String> dropDownData=new ArrayList<>();
	static List<String> prevdropDownData=new ArrayList<>();

	static List<String> mnList=new ArrayList<>();
	static List<String> mnPerList=new ArrayList<>();
	static List<String> mnXpathHeading=new ArrayList<>();
	static List<String> mnDataXpath=new ArrayList<>();

	
	static List<String> lowAttendanceNamesList=new ArrayList<>();
	static List<String> lowAttendanceperList=new ArrayList<>();
	static List<String> lowAttendanceXpathHeading=new ArrayList<>();
	static List<String> lowAttendanceDataXpath=new ArrayList<>();
	/*
	static List<String> gridHeadingList=new ArrayList<>();
	static List<String> gridContentList=new ArrayList<>();
	static List<String> gridXpathHeading=new ArrayList<>();
	static List<String> gridDataXpath=new ArrayList<>();

	static List<String> charHeadingList=new ArrayList<>();
	static List<String> charContentList=new ArrayList<>();
	static List<String> charXpathHeading=new ArrayList<>();
	static List<String> charDataXpath=new ArrayList<>();
	
	static List<String> MarksHeadingList=new ArrayList<>();
	static List<String> MarksContentList=new ArrayList<>();
	static List<String> MarksXpathHeading=new ArrayList<>();
	static List<String> MarksDataXpath=new ArrayList<>();
*/

	static List<String> queryDBDataList=new ArrayList<>();
	static List<String> queryHeading=new ArrayList<>();
	static List<String> queriesList=new ArrayList<>();
	HashMap<String,String> dashBoard_DataPerVisuals;
	HashMap<String,String> dataBase_DataPerVisuals;


	static int dataColCount=0;
	static Object[][] data1;
	static Object[][] data;

	@JiraPolicy(logTicketReady = true)
	@Test
	public void loginAndDropDownSelection() throws InterruptedException, ClassNotFoundException, SQLException, IOException {

		Demo_ExtentReport e = new Demo_ExtentReport();

		//  dbCon.dBConnection();

		data=ReadFromExcel.readExcelData(config.getTestCasesSheet());

		//	testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);

		/*dataColCount=testData.get(1).split(">").length;
		System.out.println("data.length : "+data.length);
		data1=new Object[dataColCount][data.length-1];*/

		driver.get(baseUrl);

		PowerBIDashBoard pBIDashBoard=new PowerBIDashBoard(driver);

		Thread.sleep(3000);
		pBIDashBoard.getEmailId().sendKeys(config.getEmail());
		pBIDashBoard.getEmailIdFrameSubmit().click();

		Thread.sleep(6000);
		pBIDashBoard.getPassword().sendKeys(config.getPassword());
		pBIDashBoard.getPasswordAndStaySignInButton().click();
		Thread.sleep(3000);
		pBIDashBoard.getPasswordAndStaySignInButton().click();

		Thread.sleep(6000);
		Thread.sleep(6000);

		//getRespectiveTestDataList1(testData);
		//dropDownSelection1(driver,dropDownHeading,dropDownData);

		//Object[][] data1=ReadFromExcel.readExcelData(config.getTestCasesSheet());
		// TestBase.getSortedDataOfExcel(data1);

		/*	
	//	String[] checkBoxTitle=new String[]{"Institute_name","Course_name","semester","Subject_name","Teacher Name","Student_name"};
	//	String[] expData=new String[]{"G P Mumbai","Computer Engineering","Semester 1","Engineering Drawing","V V Marathe","Diya D"};

		Object[][] data=ReadFromExcel.readExcelData(config.getSheet_Name4());
		List<Object> dropDownHeading=new ArrayList<>();
		List<Object> dropDowndata=new ArrayList<>();
		int count=0;
	//	System.out.println("Data : "+data);
		//dropDownSelection(driver,checkBoxTitle[i], expData[i]);
	    for(int i=0;i<data.length;i++)
	    {
	    	for(int j=0;j<data[i].length;j++)
		    {
		    	if(i==0)
		    		dropDownHeading.add(data[i][j]);
		    	else
		    		dropDowndata.add(data[i][j]);

		    }
	    	if(i!=0)
	    	{
	    		count=0;
	    		System.out.println("H : "+dropDownHeading);
	    		System.out.println("H : "+dropDowndata);

	    		while(count!=dropDowndata.size()) 
	    		{
	    		dropDownSelection(driver,(String)dropDownHeading.get(count), (String)dropDowndata.get(count));
	    		count++;
	    		}
	    		dropDowndata.clear();
	    	}

	    }*/

	}
	
	@Test(dependsOnMethods ="loginAndDropDownSelection")
	public void monthPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		System.out.println("into month test case..............");
		dropDownHeading.clear(); 
		dropDownData.clear();
		queryHeading.clear();
		queryDBDataList.clear();
		
		
		
		testData.clear();

		testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);
		getRespectiveTestDataList1(testData, 0, dropDownHeading, dropDownData);
		if(!prevdropDownData.equals(dropDownData))
		dropDownSelection1(driver,dropDownHeading,dropDownData);

		testData.clear();
		prevdropDownData.clear();

		testData=TestDataManipulation.getSortedTestDataOfExcel(data,4);
		getRespectiveTestDataList1(testData, 0, mnXpathHeading, mnDataXpath);

		System.out.println("Month Performance........");
	//	List<WebElement> monthName=driver.findElements(By.cssSelector("svg[name$='Line and stacked column chart'] text[class='setFocusRing']"));
		List<WebElement> monthName=driver.findElements(By.cssSelector(mnDataXpath.get(0)));


		//System.out.println("List Of Months "+monthName);
		for(WebElement ele : monthName)
		{
			System.out.println("Month_Text : "+ele.getText());
			mnList.add(ele.getText());

		}

		String Percentage_month="";
		List<WebElement> monthPer=driver.findElements(By.cssSelector(mnDataXpath.get(1)));
			for(WebElement ele : monthPer)
		{
			System.out.println("Per_Text : "+ele.getText());
			String per=ele.getText().substring(0, ele.getText().indexOf("%"));
			mnPerList.add(per);

		}

			dashBoard_DataPerVisuals=listToHashMap(mnList, mnPerList);

		System.out.println("MnList : "+mnList);
		System.out.println("PerList : "+mnPerList);

		testData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,5);
		getRespectiveTestDataList1(testData, 0, queryHeading, queriesList);

		dbCon.dBConnection(queriesList.get(0),"attendance_month","attendance");
		//Collections.sort(DataBaseConnection.visualNamesList);
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
	   dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);

		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(1, 7, "Dashboard Data: "+dashBoard_DataPerVisuals+" matched with "
				+" Database data : "+dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(1, 8, "Pass");
		setResultToExcel.setOutputToExcel(1, 9, "No Defect");

		prevdropDownData.addAll(dropDownData);
		dashBoard_DataPerVisuals.clear();
		dataBase_DataPerVisuals.clear();


		System.out.println("Assert Successfull.....");

	}


	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 1)
	public void lowAttendancePerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		dropDownHeading.clear(); 
		dropDownData.clear();
		queryHeading.clear();
		queryDBDataList.clear();
		
	
        testData.clear();

		testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);
		getRespectiveTestDataList1(testData, 1, dropDownHeading, dropDownData);
	//	if(!prevdropDownData.equals(dropDownData))
		dropDownSelection1(driver,dropDownHeading,dropDownData);

		testData.clear();
		prevdropDownData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,4);
		getRespectiveTestDataList1(testData, 1, lowAttendanceXpathHeading, lowAttendanceDataXpath);
		
		System.out.println("Low Attendance Performance........");
	//	List<WebElement> monthName=driver.findElements(By.cssSelector("svg[name$='Line and stacked column chart'] text[class='setFocusRing']"));
		List<WebElement> lowAttandanceName=driver.findElements(By.cssSelector(lowAttendanceDataXpath.get(0)));
		


		//System.out.println("List Of Months "+monthName);
		for(WebElement ele : lowAttandanceName)
		{
			System.out.println("LowAttendance_Text : "+ele.getText());
			lowAttendanceNamesList.add(ele.getText());
			

		}

		String LowAttendancePercentage="";
		List<WebElement> lowAttandancePer=driver.findElements(By.cssSelector(lowAttendanceDataXpath.get(1)));
			for(WebElement ele : lowAttandancePer)
		{
			System.out.println("LowAttendancePer_Text : "+ele.getText());
			String per=ele.getText().substring(0, ele.getText().indexOf("%"));
			lowAttendanceperList.add(per);
			

		}

			dashBoard_DataPerVisuals=listToHashMap(lowAttendanceNamesList, lowAttendanceperList);

		System.out.println("lowAttendanceNamesList : "+lowAttendanceNamesList);
		System.out.println("lowAttendanceperList : "+lowAttendanceperList);

		testData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,5);
		getRespectiveTestDataList1(testData, 1, queryHeading, queriesList);

		dbCon.dBConnection(queriesList.get(0),"Attendance_Remarks","Percentage");
		//Collections.sort(DataBaseConnection.visualNamesList);
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
	   dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);

		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(2, 7, "Dashboard Data: "+dashBoard_DataPerVisuals+" matched with "
				+" Database data : "+dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(2, 8, "Pass");
		setResultToExcel.setOutputToExcel(2, 9, "No Defect");

		System.out.println("Assert Successfull for low attandance.....");
		prevdropDownData.addAll(dropDownData);
		dashBoard_DataPerVisuals.clear();
		dataBase_DataPerVisuals.clear();

	}
/*
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 2)
	public void GirdPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		dropDownHeading.clear(); 
		dropDownData.clear();
		queryHeading.clear();
		queryDBDataList.clear();
		testData.clear();

		testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);
		getRespectiveTestDataList1(testData, 2, dropDownHeading, dropDownData);
		//	if(!prevdropDownData.equals(dropDownData))
		dropDownSelection1(driver,dropDownHeading,dropDownData);

		testData.clear();
		prevdropDownData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,4);
		getRespectiveTestDataList1(testData, 2, gridXpathHeading, gridDataXpath);

		System.out.println("Grid Performance........");
		//	List<WebElement> monthName=driver.findElements(By.cssSelector("svg[name$='Line and stacked column chart'] text[class='setFocusRing']"));
		List<WebElement> gridList=driver.findElements(By.cssSelector(gridDataXpath.get(0)));

		//////////////////		
		Thread.sleep(3000);
		//  div[role=columnheader]:nth-of-type(3)
		//  div[role=gridcell]:nth-of-type(3)	   
		// div[role=gridcell]:nth-of-type(4) :nth-child(1)
		//List<WebElement> gridList=driver.findElements(By.cssSelector("div[role=columnheader]"));


		for(int j=2;j<=gridList.size();j++)
		{
			//	WebElement gridEleHeading=driver.findElement(By.cssSelector("div[role=columnheader]:nth-of-type("+j+")"));
			WebElement gridEleHeading=driver.findElement(By.cssSelector(""+gridDataXpath.get(0)+":nth-of-type("+j+")"));

			String str=gridEleHeading.getText().trim().replace(" ", "_");
			gridHeadingList.add(str);

			// WebElement gridEleContent=driver.findElement(By.cssSelector("div[role=gridcell]:nth-of-type("+j+") :nth-child(1)"));
			//gridContentList.add(gridEleContent.getText()); 	
			if(j==4) {

				//WebElement gridEleContent=driver.findElement(By.cssSelector("div[role=gridcell]:nth-of-type("+j+") :nth-child(1)"));
				WebElement gridEleContent=driver.findElement(By.cssSelector(""+gridDataXpath.get(1)+":nth-of-type("+j+") :nth-child(1)"));
				gridContentList.add(gridEleContent.getText());

				System.out.println("GridContentList4 : "+gridEleContent.getText());
			}
			else 
			{	
				WebElement gridEleContent=driver.findElement(By.cssSelector(""+gridDataXpath.get(1)+":nth-of-type("+j+")"));
			//	gridContentList.add(gridEleContent.getText());
				str=gridEleContent.getText();

				if(str.contains("%")) 
					str=str.replace("%", "");

				gridContentList.add(str);
				System.out.println("GridContentList : "+gridEleContent.getText());
			}
		}
	//	System.out.println("GridHeading : "+gridHeadingList);
	//	System.out.println("GridContent : "+gridContentList);



		///////////////////////
		dashBoard_DataPerVisuals=listToHashMap(gridHeadingList, gridContentList);
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);

	//	System.out.println("gridHeadingList : "+gridHeadingList);
	//	System.out.println("gridContentList : "+gridContentList);

		testData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,5);
		getRespectiveTestDataList1(testData, 2, queryHeading, queriesList);

		dbCon.dBConnection(queriesList.get(0),"Student_Name","Subject_Name","Marks_Obtained","Total_Marks",
				"Attendance_percentage","Remark","Enrollment_No");
		//Collections.sort(DataBaseConnection.visualNamesList);
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);
		dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		//setResultToExcel.setOutputToExcel(3, 7, "Dashhboard data matched with Database data");
		setResultToExcel.setOutputToExcel(3, 7, "Dashhboard Data: "+dashBoard_DataPerVisuals+" matched with "
				+" Database data : "+dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(3, 8, "Pass");
		setResultToExcel.setOutputToExcel(3, 9, "No Defect");

		System.out.println("Assert Successfull for grid performance.....");
		prevdropDownData.addAll(dropDownData);
		dashBoard_DataPerVisuals.clear();
		dataBase_DataPerVisuals.clear();

	}

	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 3)
	public void characteristicPerformance() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		dropDownHeading.clear(); 
		dropDownData.clear();
		queryHeading.clear();
		queryDBDataList.clear();
		testData.clear();

		testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);
		getRespectiveTestDataList1(testData, 3, dropDownHeading, dropDownData);
		//	if(!prevdropDownData.equals(dropDownData))
		dropDownSelection1(driver,dropDownHeading,dropDownData);

		testData.clear();
		prevdropDownData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,4);
		getRespectiveTestDataList1(testData, 3, charXpathHeading, charDataXpath);

		System.out.println("Char Performance........");
		//	List<WebElement> monthName=driver.findElements(By.cssSelector("svg[name$='Line and stacked column chart'] text[class='setFocusRing']"));
		//List<WebElement> gridList=driver.findElements(By.cssSelector(charDataXpath.get(1)));

		//////////////////		
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.cssSelector(charDataXpath.get(0))));
		List<WebElement> charList=driver.findElements(By.cssSelector(charDataXpath.get(1)));
		//   System.out.println("Size of List : "+charList.size());
		List<String> charListCmp=new ArrayList<>();
		String str="\"";
		int count=0;
		for(WebElement ele : charList)
		{
			charListCmp.add(ele.getText());
			str=str+ele.getText();
			if(count!=(charList.size()-1))
				str=str+",";
			if(count==charList.size()-1)
				str=str+"\"";
			count++;
		}
		System.out.println("str : "+str);
		charContentList.add(str);
		System.out.println("Char_List : "+charContentList);
		driver.switchTo().defaultContent();

		charHeadingList.add("Exam_Remark");

		///////////////////////

		dashBoard_DataPerVisuals=listToHashMap(charHeadingList, charContentList);
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);


		testData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,5);
		getRespectiveTestDataList1(testData, 3, queryHeading, queriesList);

		dbCon.dBConnection(queriesList.get(0),"Exam_Remark");
		//Collections.sort(DataBaseConnection.visualNamesList);
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//////////////////
		List<String> visualDataListTest=new ArrayList<>();
		
		String st=DataBaseConnection.visualDataList.get(0).replaceAll("\"", "");
		String[] st_arr=st.split(",");
		String[] st_arr1=new String[st_arr.length];
		String str1 ="\"";
		for(int i=0;i<st_arr.length;i++)
		{
			int count1=0;
			for(String charCont : charListCmp) 
			{
				if(st_arr[i].equals(charCont))	
				{		
                     st_arr1[count1]=st_arr[i];
                     break;
				}
				
				count1++;
			}
		}
		int count2=0;
		for(int i=0;i<st_arr1.length;i++)
		{		
			str1=str1+st_arr1[i];
			if(count2!=(st_arr1.length-1))
				str1=str1+",";
			if(count2==st_arr1.length-1)
				str1=str1+"\"";
			count2++;
		}
		
      System.out.println("str1 : "+str1);
      DataBaseConnection.visualDataList.clear();
      DataBaseConnection.visualDataList.add(str1);
		/////////////////
       // if(str1!=null)      
        //	dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, visualDataListTest);
        //else	
		dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		//setResultToExcel.setOutputToExcel(3, 7, "Dashhboard data matched with Database data");
		setResultToExcel.setOutputToExcel(4, 7, "Dashhboard Data: "+dashBoard_DataPerVisuals+" matched with "
				+" Database data : "+dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(4, 8, "Pass");
		setResultToExcel.setOutputToExcel(4, 9, "No Defect");

		System.out.println("Assert Successfull for char performance.....");
		prevdropDownData.addAll(dropDownData);
		dashBoard_DataPerVisuals.clear();
		dataBase_DataPerVisuals.clear();

	}
	
	@Test(dependsOnMethods ="loginAndDropDownSelection",priority = 4)
	public void Marks_Obtained() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		//getRespectiveTestDataList1(testData);
		dropDownHeading.clear(); 
		dropDownData.clear();
		queryHeading.clear();
		queryDBDataList.clear();
		testData.clear();

		//Reading dropdown data column
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,3);
		getRespectiveTestDataList1(testData, 4, dropDownHeading, dropDownData);
		//	if(!prevdropDownData.equals(dropDownData))
		dropDownSelection1(driver,dropDownHeading,dropDownData);

		//Reading xpath column
		testData.clear();
		prevdropDownData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,4);
		getRespectiveTestDataList1(testData, 4, charXpathHeading, charDataXpath);

		System.out.println("Char Performance........");
		//	List<WebElement> monthName=driver.findElements(By.cssSelector("svg[name$='Line and stacked column chart'] text[class='setFocusRing']"));
		//List<WebElement> gridList=driver.findElements(By.cssSelector(charDataXpath.get(1)));

		//////////////////		
	
		List<WebElement> charList=driver.findElements(By.cssSelector(charDataXpath.get(1)));
		//   System.out.println("Size of List : "+charList.size());
		
		///////////////////////

		dashBoard_DataPerVisuals=listToHashMap(charHeadingList, charContentList);
		System.out.println("dashBoard_DataPerVisuals : "+dashBoard_DataPerVisuals);


		testData.clear();
		testData=TestDataManipulation.getSortedTestDataOfExcel(data,5);
		getRespectiveTestDataList1(testData, 3, queryHeading, queriesList);

		dbCon.dBConnection(queriesList.get(0),"Exam_Remark");
		//Collections.sort(DataBaseConnection.visualNamesList);
		System.out.println("Sorted List : "+DataBaseConnection.visualNamesList);

		//////////////////
		
		
		  
		/////////////////
       // if(str1!=null)      
        //	dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, visualDataListTest);
        //else	
		dataBase_DataPerVisuals=listToHashMap(DataBaseConnection.visualNamesList, DataBaseConnection.visualDataList);
		System.out.println("dataBase_DataPerVisuals : "+dataBase_DataPerVisuals);

		assertEquals(dashBoard_DataPerVisuals,dataBase_DataPerVisuals);
		//setResultToExcel.setOutputToExcel(3, 7, "Dashhboard data matched with Database data");
		setResultToExcel.setOutputToExcel(4, 7, "Dashhboard Data: "+dashBoard_DataPerVisuals+" matched with "
				+" Database data : "+dataBase_DataPerVisuals);
		setResultToExcel.setOutputToExcel(4, 8, "Pass");
		setResultToExcel.setOutputToExcel(4, 9, "No Defect");

		System.out.println("Assert Successfull for char performance.....");
		prevdropDownData.addAll(dropDownData);
		dashBoard_DataPerVisuals.clear();
		dataBase_DataPerVisuals.clear();

	}
*/

	public HashMap<String, String> listToHashMap(List<String> v_nameList,List<String> v_dataList)
	{
		HashMap<String,String> map=new HashMap<>();


		for( int i=0;i<v_nameList.size();i++)
		{
			map.put(v_nameList.get(i), v_dataList.get(i));
		}
		//System.out.println("Maps : "+map);
		return map;

	}
	public static void dropDownSelection1(WebDriver driver,List<String> dropDownHeading,List<String> dropDownData) throws InterruptedException
	{
		Thread.sleep(6000);
		List<WebElement> dropDownList;
		String dropDownName="";
		String expVal="";
		int count=0;
		while(count!=dropDownHeading.size()) 
		{
			dropDownName=dropDownHeading.get(count);
			expVal=dropDownData.get(count);
			System.out.println("expVal : "+expVal);
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[@class='slicer-dropdown-menu' and @aria-label='"+dropDownName+"']/i")).click();
			Thread.sleep(3000);
			if(dropDownName.equalsIgnoreCase("Year")) 
			{
				dropDownName=dropDownName.replace("Year", "Date Year");
				dropDownList=driver.findElements(By.xpath("//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span"));
			}
			else
				dropDownList=driver.findElements(By.xpath("//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span"));
			//String selVal="G P Mumbai";
			List<String> textList=new ArrayList<>();
			for(WebElement ele : dropDownList)
			{
				textList.add(ele.getText());
			}
			System.out.println(dropDownName+" List "+textList);

			for(int i=1;i<=dropDownList.size();i++)
			{

				WebElement name=driver.findElement(By.xpath("(//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span)["+i+"]"));
				WebElement nameSel=driver.findElement(By.xpath("(//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span)["+i+"]/preceding-sibling::div"));

				System.out.println("Name : "+name.getText());
				System.out.println("NameSel : "+nameSel.getAttribute("class"));

				if(name.getText().equals(expVal))
				{
					if(!nameSel.getAttribute("class").equals("slicerCheckbox selected"))
					{
						name.click();
						System.out.println("Selected "+expVal);
						break;
					}
					else {
						System.out.println("Already Selected "+expVal);
						break;
					}
				}


			}

			count++;
		}

	}


	public static void dropDownSelection(WebDriver driver,String dropDownName,String expVal) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='slicer-dropdown-menu' and @aria-label='"+dropDownName+"']/i")).click();
		Thread.sleep(3000);

		List<WebElement> dropDownList=driver.findElements(By.xpath("//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span"));
		//String selVal="G P Mumbai";
		List<String> textList=new ArrayList<>();
		for(WebElement ele : dropDownList)
		{
			textList.add(ele.getText());
		}
		System.out.println(dropDownName+" List "+textList);

		for(int i=1;i<=dropDownList.size();i++)
		{

			WebElement name=driver.findElement(By.xpath("(//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span)["+i+"]"));
			WebElement nameSel=driver.findElement(By.xpath("(//div[@class='slicerBody'and @aria-label='"+dropDownName+"' ]//div[@class='slicerItemContainer']/span)["+i+"]/preceding-sibling::div"));

			System.out.println("Name : "+name.getText());
			System.out.println("NameSel : "+nameSel.getAttribute("class"));

			if(name.getText().equals(expVal))
			{
				if(!nameSel.getAttribute("class").equals("slicerCheckbox selected"))
				{
					name.click();
					System.out.println("Selected "+expVal);
					break;
				}
				else {
					System.out.println("Already Selected "+expVal);
					break;
				}
			}


		}


	}

	public static void getRespectiveTestDataList1(List<String> testData,int testCaseRowNum,List<String> headingList,List<String> valueList) throws InterruptedException
	{
		int count=0;
		valueList.clear();
		headingList.clear();
		String dropName;
		//String str1=testDataStr.replace("<", "");
		String[] str=testData.get(testCaseRowNum).split(">>");

		System.out.println("Str.lenght : "+str.length);

		for(int i=0;i<str.length;i++)
		{
			System.out.println("String data : "+str[i]);
			String[] str11=str[i].split("=<<");
			//getRespectiveTestDataList(str11,count);

			for(int j=0;j<str11.length;j++)
			{
				if(j==0) {
					dropName=str11[j].trim();

					if(!dropName.equalsIgnoreCase("Teacher Name"))
						dropName=dropName.replace(" ", "_");
					System.out.println("dropName : "+dropName);
					//dropDownHeading.add(str11[j]);
					headingList.add(dropName);
				}
				else		
					valueList.add(str11[j]);

			}


		}

		System.out.println("tHeading1 : "+headingList);
		System.out.println("TestDataList1 : "+valueList);
		System.out.println("getRespectiveTestDataList1");

		//	System.out.println("TestDataList : "+tdata);

	}

	public static void getRespectiveTestDataList(List<String> testData,Object[][] tdata) throws InterruptedException
	{
		int count=0;

		for(String testDataStr : testData) {

			//String str1=testDataStr.replace("<", "");
			String[] str=testDataStr.split(">");

			System.out.println("Str.lenght : "+str.length);

			for(int i=0;i<str.length;i++)
			{
				System.out.println("String data : "+str[i]);
				String[] str11=str[i].split("=<");
				//getRespectiveTestDataList(str11,count);

				for(int j=0;j<str11.length;j++)
				{
					if(j==0)
					{   
						if(count==0)
							dropDownHeading.add(str11[j]);
					}
					else
					{

						dropDownData.add(str11[j]);


					}

				}


			}
			if(prevdropDownData.equals(dropDownData))
			{

				//calling drop down with theading and ttestdata
				//if(count==0)
				//	dropDownSelection(driver,(String)dropDownHeading.get(count), dropDownData.get(count));

				System.out.println("TestDataList if : "+dropDownData);


			}else {

				//calling other functions.
				//		dropDownSelection(driver,(String)dropDownHeading.get(count), dropDownData.get(count));
				System.out.println("TestDataList else : "+dropDownData);



			}
			prevdropDownData.addAll(dropDownData);
			dropDownData.clear();	
			count++;

		}
		System.out.println("tHeading : "+dropDownHeading);
		System.out.println("TestDataList : "+dropDownData);
		System.out.println("prevTestData : "+prevdropDownData);
		//	System.out.println("TestDataList : "+tdata);

	}



}
