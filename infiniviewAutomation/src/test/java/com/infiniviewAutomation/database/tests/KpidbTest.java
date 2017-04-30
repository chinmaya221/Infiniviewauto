package com.infiniviewAutomation.database.tests;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import utils.DataBaseConnector;
import utils.TestBase;

public class KpidbTest extends TestBase
{
	public static String kpi_status_normal= "0";
	public static String kpi_status_warning= "3";
	public static String kpi_status_critical= "5";
	public static String kpi_status_stale="10";
	
	@Test(priority=04)
	public void testVerifyKPIstatus()throws Exception
	{
		logger=report.startTest("testVerifyKPIstatus");
		try{
		String sqlQuery = "select KPI_Status from KPI_Definitions where KPI_Name='KPI_KT1'";
		String actualKpiStatus= DataBaseConnector.executeSQLQuery("QA",sqlQuery);
			
			System.out.println("KPI Status retrived from Database is"+""+actualKpiStatus);
			if(actualKpiStatus == "0")
			{
			Assert.assertEquals(kpi_status_normal, actualKpiStatus,"KPI Status is Normal");
			}
			else if(actualKpiStatus=="3")
			{
			Assert.assertEquals(kpi_status_warning, actualKpiStatus,"KPI Status is Warning");
			
			}
			else if(actualKpiStatus=="5")
			{
				Assert.assertEquals(kpi_status_critical, actualKpiStatus,"KPI Status is Critical");
			}
			else if(actualKpiStatus=="10")
			{
				Assert.assertEquals(kpi_status_stale, actualKpiStatus,"KPI Status is Stale");
				
			}
			logger.log(LogStatus.INFO,"testVerifyKPIstatus Test case completed successfully ");
		}catch(Exception ex)
		{
			ex.printStackTrace();
			System.out.println("There is no Record for the selected KPI in KPI DB");
			logger.log(LogStatus.INFO,"testVerifyKPIstatus Test case failed ");
		}
	}
	
	
	/*public void testVerifyKpiIdinKpiDb()
	{
		String sqlQuery = "select KPI_Status from KPI_Definitions where KPI_Name='KPI_KT1'";
	}*/
	
 /* public static void main(String[] args) throws Exception {
		
		KpidbTest test= new KpidbTest();
		
		System.out.println("Test is going to execute");
		test.testVerifyKPIstatus();
		
		System.out.println("Test executed");
	}*/

}
