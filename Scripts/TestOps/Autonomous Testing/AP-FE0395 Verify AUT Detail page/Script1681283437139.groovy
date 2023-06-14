import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

'''
Steps:
Login to TestOps => Go to  Autonomous Testing page  => Go to an AUT 
Validation points: 
Verify Name of AUT, edit icon are displayed 
Verify description is displayed 
Verify Domain statistic table ( Application Domain(s), Activation Date, Last Data Received Date)
Verify Installation Steps for Katalon AI seciton: 
      + Verify description : Please complete following steps for AI Katalon to generate user journey map and test cases.
      + Verify step 1displays: Activate Traffic Agent 
              + Description 
              + Client code 
              + Copy icon 
      + Verify step 2 is displayed: Add Test Environment(s)
               + Description 
               + Test env table ( ID, Environment Name, Username) 
               + Add Test env button 
    => Click to Add Test Environment button => Verify add test env popup is displayed 
                 + Environment Name
                 + Environment URL
                 + Username (Optional)
                 + Password (Optional)
                 + Set As Default Test Environment checkbox 
                 + Cancel button is enable 
                 + Add button is disabled 
   => Click Cancel button 
        + Verify step 3 is displayed: Link Project(s)
                 + Description 
                 + Project table : Project, Script Repo 
                 + Link project button is enabled 
                 => Click Link project button => Verify add link project popup displayed ( project drop down, script repository dropdown, add button is disabled, cancel button is enabled)
'''