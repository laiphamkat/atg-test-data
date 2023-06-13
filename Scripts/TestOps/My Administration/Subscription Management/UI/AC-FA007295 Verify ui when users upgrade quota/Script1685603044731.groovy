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
					.inAccount('All products paid')
					.getFirst()
										
'Get api token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).getToken()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'Select account'
Page.nav(WelcomePage).selectAccount(user.accountId).sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()
						 .sleepALittleTime()

'Verify can Upgrade Katalon Platform Plan'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickUpgradePlatformButton()
									.verifyCurrentPlatformDropdownSelected("Premium 2 - 10,000 Test Results")
																		
'Verify can Upgrade KSE quota'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon Studio Enterprise (per-User)')
									.clickBuyMore()
									.verifyKSEQuotaInput('1')
									
'Verify can Upgrade KRE quota'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon Runtime Engine (Floating)')
									.clickBuyMore()
									.verifyKREQuotaInput('1')
																		
'Verify can Upgrade Katalon TestCloud quota'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon TestCloud')
									.clickBuyMore()
									.verifyTestCloudQuotaInput('1')								