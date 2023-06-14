import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page


'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()

'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential(user.email, user.pwd).clickSignIn()
					.sleep(2) // Wait for the web page applies new changes
Page.nav(HomePage).waitUntilPageDisplayed()

'User select Org'
Page.nav(HeaderBar).clickOrganization()
Page.nav(HomePage).selectOrg(user.orgName)

'At Home page, user select Project'
Page.nav(HomePage).selectProject(user.projectName)

'Verify Katalon logo displayed on the left of conner of Org section'
Page.nav(HeaderBar).verifyKatalonLogoDisplayed()

'Verify Dashboard tab is displayed and actived'
Page.nav(HeaderBar).verifyDashboardTabDisplayed()
					.verifyDashboardTabActived()

'Verify Planing tab is displayed => Click to Planning tab => Planning tab is actived, Planning page is redirected'
Page.nav(HeaderBar).verifyPlanningTabDisplayed()
					.clickPlanning()
					.verifyPlanningTabActived()

'Verify Tests tab is displayed => Click to Tests tab => Tests tab is actived, Tests page is redirected'
Page.nav(HeaderBar).verifyTestsTabDisplayed()
					.clickTests()
					.verifyTestsTabActived()

'Verify Executions tab is displayed => click to Executions tab => Execution tab is actived, Execution page is redriected to'
Page.nav(HeaderBar).verifyExecutionsTabDisplayed()
					.clickExecutions()
					.verifyExecutionsTabActived()

'Verify Reports tab is displayed => click to Reports tab => Reports tab is actived, Reports page is redriected to'
Page.nav(HeaderBar).verifyReportsTabDisplayed()
					.clickReports()
					.verifyReportsTabActived()

'Verify Visual Testing tab is displaed => Click to Visual Tesing tab => Visual Testing page is actived, VS page is redirected to'
Page.nav(HeaderBar).verifyVisualTestingTabDisplayed()
					.clickVisualTesting()
					.verifyVisualTestingTabActived()

'Configurations tab is displayed => Click to Configurations tab => Configurations is actived, Configurations page is redirected to'
Page.nav(HeaderBar).verifyConfigurationsTabDisplayed()
					.clickConfigurations()
					.verifyConfigurationsTabActived()

'Verify download ks icon display and enable to click'
Page.nav(HeaderBar).verifyDownloadIconDisplayed()
					.verifyDownloadIconClickable()

'Verify notification icon is displayed'
Page.nav(HeaderBar).verifyNotificationIconDisplayed()

'Verify recent work information icon displayed and able to click'
Page.nav(HeaderBar).verifyRecentWorkIconDisplayed()
					.verifyRecentWorkIconClickable()

'Verify setting icon display and able to click'
Page.nav(HeaderBar).verifySettingIconDisplayed()
					.verifySettingIconClickable()

'Verify profile icon displayed correctly'
Page.nav(HeaderBar).verifyProfileIconDisplayed()
