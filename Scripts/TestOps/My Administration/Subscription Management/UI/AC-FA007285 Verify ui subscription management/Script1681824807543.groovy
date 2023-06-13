import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.services.MyAccountService
import katalon.testops.services.LoginService
import katalon.utility.DateTimeUtility as DateTimeUtility


'Data Setup'
Credential user = Page.nav(Credential)
					.getCredentials()
					.withRole('Owner')
					.inAccount('CE New Customer')
					.getFirst()

'Get api token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).getToken()

'Delete existing account if exist'
Page.nav(MyAccountService).initRequestObject().getAccountId(accountName).setDeleteURL().deleteWithoutStatusCheck()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

Page.nav(WelcomePage).clickCreateANewAccount()

Page.nav(CreateNewAccountPopUp).inputAccountName(accountName).inputOrganizationtName(organizationName).inputProjectName(
    projectName).clickCreate().sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()

'Verify UI Your Subscription Section'
Page.nav(SubscriptionManagementPage).verifyTextUIHaveElementVisible("Subscription Management", "h2")
									.verifyTextUIHaveElementVisible("Your Subscription", "h3")
									.verifyCurrentPlatformTier('Katalon Platform Premium 1 - Trial\nIf you need more support, do not hesitate to contact your dedicated Customer Success Manager directly or email success@katalon.com.')
									.verifyPlatformUpgradeButtonVisible()
									.verifyPlatformEllipsisButton()
									.verifyPlatformTestResultQuota('0/3500')
									.verifyPlatformExpiredDate(DateTimeUtility.next30Days())
									.verifyPlatformOrganizationQuota('1 Organization(s)/ 5 Project(s)/ Unlimited User(s)')
									.verifyVTPquota('Unlimited Checkpoint Images')
									.verifyTextUIVisible("Product")
									.verifyTextUIVisible("Billing Cycle")
									.verifyTextUIVisible("Quantity")
									.verifyTextUIVisible("Expiry Date")
									.verifyPlatformUpgradeButtonVisible()
									.verifyPlatformEllipsisButton()
																		
'Verify UI - Banner'
Page.nav(SubscriptionManagementPage).verifyButtonUIVisible("Platform")
									.clickPlatformTabButton()
									.verifyTextUIHaveElementVisible("Best offers we have for you", "h3")
									.verifyTextUIHaveElementVisible("Starter Plan", "p")
									.verifyTextUIHaveElementVisible("30% discount", "span")
									.verifyTextUIHaveElementVisible("Get great deal on your first few licenses.", "p")
									.verifyTextUIHaveElementVisible("• Perfect for getting started with Katalon", "p")
									.verifyTextUIHaveElementVisible("• Available for annual subscriptions only", "p")
									.verifyTextUIHaveElementVisible("• Applicable for new customers", "p")

'Verify UI the Katalon Standalone - KSE'
Page.nav(SubscriptionManagementPage).verifyTextUIVisible("TEST AUTHORING")
									.verifyTextUIVisible("An IDE to automate testing in different platforms, deployable in multiple operating systems.")
									.verifyQuotaInputUIVisible('Katalon Studio Enterprise')
									.verifyKSEQuotaInput('0')
									.verifyKSEListedPrice("\$1,999 /per License")
									.verifyToggleUIProductVisible('Katalon Studio Enterprise')
									.verifyKSECheckoutPrice("\$0")
									
'Verify UI the Katalon Standalone - KRE'
Page.nav(SubscriptionManagementPage).verifyTextUIVisible("TEST EXECUTION")
									.verifyTextUIVisible("A local, non-UI, execution engine that can integrate with CI/CD pipelines.")
									.verifyQuotaInputUIVisible('Katalon Runtime Engine')
									.verifyKREQuotaInput('0')
									.verifyKREListedPrice("\$1,599 /per License")
									.verifyToggleUIProductVisible('Katalon Runtime Engine')
									.verifyKRECheckoutPrice("\$0")
																		
'Verify UI the Katalon Platform'
Page.nav(SubscriptionManagementPage).clickPlatformTabButton()
									.verifyTextUIVisible("TEST MANAGEMENT")
									.verifyCurrentPlatformDropdownSelected()
									.verifyTextUIVisible("A collaborative platform to manage your tests, executions, releases, reports, & integrations.")
									.verifyTextUIHaveElementVisible(" Different benefits based on Platform tier","div")
									.verifyToggleUIKatalonPlatformVisible()
									.verifyPlatformCheckoutPrice("\$0")									

'Verify UI the Katalon Platform - KSE'
Page.nav(SubscriptionManagementPage).verifyTextUIVisible("TEST AUTHORING")
									.verifyTextUIVisible("An IDE to automate testing in different platforms, deployable in multiple operating systems.")
									.verifyQuotaInputUIVisible('Katalon Studio Enterprise')
									.verifyKSEQuotaInput('0')
									.verifyKSEListedPrice("\$1,999 /per License")
									.verifyToggleUIProductVisible('Katalon Studio Enterprise')
									.verifyKSECheckoutPrice("\$0")
									
'Verify UI the Katalon Platform - KRE'
Page.nav(SubscriptionManagementPage).verifyTextUIVisible("TEST EXECUTION")
									.verifyTextUIVisible("A local, non-UI, execution engine that can integrate with CI/CD pipelines.")
									.verifyQuotaInputUIVisible('Katalon Runtime Engine')
									.verifyKREQuotaInput('0')
									.verifyKREListedPrice("\$1,599 /per License")
									.verifyToggleUIProductVisible('Katalon Runtime Engine')
									.verifyKRECheckoutPrice("\$0")

'Verify UI the Katalon Platform - Katalon TestCloud'
Page.nav(SubscriptionManagementPage).verifyTextUIVisible("TEST EXECUTION")
									.verifyTextUIVisible("Execute your tests on cloud-based environments with thousands of desktop & mobile browsers.")
									.verifyQuotaInputUIVisible('Katalon TestCloud')
									.verifyTestCloudListedPrice("\$1,849 /per Session")
									.verifyToggleUIProductVisible('Katalon TestCloud')
									
'Verify UI the Katalon Platform - VTP'
Page.nav(SubscriptionManagementPage).verifyTextUINotVisible("ADD-ON")
									.verifyTextUINotVisible("An AI-powered service to test UI of applications.")

									
'Verify UI the Checkout section'
Page.nav(SubscriptionManagementPage).verifyTextUIHaveElementVisible("Total (Net price)","p")
									.verifyTextUIHaveElementVisible("Discount","p")
									.verifyButtonUIDisable("Checkout")

