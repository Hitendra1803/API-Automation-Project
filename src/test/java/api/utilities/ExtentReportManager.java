package api.utilities;


import java.text.SimpleDateFormat;

import org.apache.poi.hpsf.Date;
import org.testng.ITestContext;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	String reportName;
	
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		reportName = "Test-Report- "+timeStamp+".html";
		
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
		
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("Pet Store Users API");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Application", "Pet Store User API");
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "Hitendra");
		
	}
	
	
	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.PASS, "Test Passed");
		
	}
	
	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.FAIL, "Test Failed");
		extentTest.log(Status.FAIL, result.getThrowable().getMessage());
		
	}
	
	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.createNode(result.getName());
		extentTest.log(Status.SKIP, "Test Skipped");
		extentTest.log(Status.SKIP, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		extentReports.flush();
	}
	

}
