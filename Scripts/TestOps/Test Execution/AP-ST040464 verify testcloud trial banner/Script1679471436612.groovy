import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable
import katalon.common.HeaderBar
import katalon.common.HomePage
import katalon.common.SignInPage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.testops.testexecution.TestCloudLicenseBanner
import katalon.testops.testexecution.TestRunCalendarPage
import katalon.testops.testexecution.TestRunListPage as TestRunListPage


KeywordUtil.markFailedAndStop("This test case should be executed manually")

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.withRole(role)
	.getFirst()
	
'User navigates to TestOps Page'
Page.nav(SignInPage).enterCredential().clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to TestOps Page with org has a trial <= 10 days left'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

'User navigates to Executions page'
Page.nav(HeaderBar).clickExecutions()

'A trial user has <= 10 days left (0, 5, 10)'
Page.nav(TestCloudLicenseBanner)
	.clickLearnMoreTCLink()
	.switchTab(1)
	.verifyCurrentURL('https://katalon.com/resources-center/blog/testcloud-release')
	.switchTab(0)
	.clickContactAnExpertLink()
	.switchTab(2)
	.verifyCurrentURL('https://katalon.chilipiper.com/router/inbound_router_meetings')
	.switchTab(0)

'User navigates to TestOps Page with org has a expired trial'
Page.nav(TestRunCalendarPage).navigateTo(user.teamId, user.projectId)

Page.nav(HomePage).waitUntilPageDisplayed()

'User navigates to Executions page'
Page.nav(HeaderBar).clickExecutions()

'A trial user has expired'
Page.nav(TestCloudLicenseBanner)
	.clickTestCloudLicenseLink()
	.switchTab(3)
	.verifyCurrentURL('https://katalon.com/pricing')
	.switchTab(0)
	.clickContactSaleLink()
	.switchTab(4)
	.verifyCurrentURL('https://katalon.chilipiper.com/router/inbound_router_meetings')
	.switchTab(0)

'Verify the banner is NOT displayed at all tabs after clicking icon X of the banner'
Page.nav(TestCloudLicenseBanner).clickCloseBanner()
Page.nav(TestRunListPage).clickTestRunCalendarTab()
Page.nav(TestCloudLicenseBanner).verifyBannerNotPresent()
Page.nav(TestRunCalendarPage).clickTestRunListTab()
Page.nav(TestCloudLicenseBanner).verifyBannerNotPresent()
Page.nav(TestRunCalendarPage).clickApplicationRepositoryTab()
Page.nav(TestCloudLicenseBanner).verifyBannerNotPresent()

'Verify the banner is displayed at all tabs at Execution page after loging out'
Page.nav(HeaderBar).clickAvatar().clickSignOut()
Page.nav(SignInPage).enterCredential(user.teamId, user.projectId).clickSignIn()
Page.nav(HomePage).waitUntilPageDisplayed()
Page.nav(HeaderBar).clickExecutions()
Page.nav(TestRunListPage).clickTestRunCalendarTab()
Page.nav(TestCloudLicenseBanner).verifyBannerPresent()
Page.nav(TestRunCalendarPage).clickTestRunListTab()
Page.nav(TestCloudLicenseBanner).verifyBannerPresent()
Page.nav(TestRunCalendarPage).clickApplicationRepositoryTab()

