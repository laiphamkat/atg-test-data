import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Page
import katalon.testops.dashboard.DashboardPage
import katalon.testops.visualtesting.VisualTestRunDetail
import katalon.testops.visualtesting.VisualTestRunPage

def signInPage = Page.nav(SignInPage)

def headerBar = Page.nav(HeaderBar)

def homePage = Page.nav(HomePage)

def dashBoardPage = Page.nav(DashboardPage)

def visualTestDashboard = Page.nav(VisualTestRunPage)

def visualTestDetailPage = Page.nav(VisualTestRunDetail)

'1. Login to TO as admin account'
signInPage.enterCredential(GlobalVariable.owner_mail, GlobalVariable.owner_pass).clickSignIn()

headerBar
		.clickOrganization()
		.selectOrg('AI_VST_PRO')

'2. Select visual testing project'
dashBoardPage.selectProject('PRO_VST')
//visualTestDashboard.accessDetailsVSTProject('72', '72')
headerBar.isVisualTestMenuDisplay().clickVisualTesting()

'3. Observes visual testing dashboard is displayed'
visualTestDashboard.isVisualTestDashboardDisplay(true).accessDetailsTestRunById(testRunID)

'3. Select Visual testing tab'
headerBar.clickVisualTesting()

'4. Select latest Visual baseline Collections'
visualTestDashboard
		.selectVisualBaselineCollectionsTab()
		.selectLatestBaselineCollection()
		.verifyPixelSensitivityComponent()