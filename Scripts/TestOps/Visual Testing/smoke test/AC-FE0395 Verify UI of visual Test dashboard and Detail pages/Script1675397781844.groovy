import internal.GlobalVariable as GlobalVariable
import katalon.common.HeaderBar as HeaderBar
import katalon.common.HomePage as HomePage
import katalon.common.SignInPage as SignInPage
import katalon.fw.lib.Page as Page
import katalon.testops.visualtesting.VisualTestRunDetail as VisualTestRunDetail
import katalon.testops.visualtesting.VisualTestRunPage as VisualTestRunPage
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.Keys as Keys

def signInPage = Page.nav(SignInPage)

def headerBar = Page.nav(HeaderBar)

def homePage = Page.nav(HomePage)

def visualTestDashboard = Page.nav(VisualTestRunPage)

def visualTestDetailPage = Page.nav(VisualTestRunDetail)

'1. Login to TO as admin account'
signInPage.enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

headerBar.clickProjectDropdown()

'2. Select visual testing project'
homePage.selectOrg(GlobalVariable.vstProjectPro).selectRecentProject('PRO_VST')

headerBar.isVisualTestMenuDisplay().clickVisualTesting()

'3. Observes visual testing dashboard is displayed'
visualTestDashboard.isVisualTestDashboardDisplay(true).accessDetailsTestRunById(testRunID)

'4. Verify Element of Visual testing is displayed'
visualTestDetailPage.isStatusBarDisplay(true)

visualTestDetailPage.isObjectStatusDisplay(true)

'5. Verify Image thumbnail of Visual Testing is displayed'
visualTestDetailPage.inputSearchStatus('passed')

visualTestDetailPage.isCheckpointThumbnailDisplay('passed')


