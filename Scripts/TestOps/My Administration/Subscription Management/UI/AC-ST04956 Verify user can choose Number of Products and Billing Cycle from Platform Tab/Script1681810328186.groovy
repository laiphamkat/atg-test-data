import external.services.PricingCalculationService
import external.services.RecurlyService
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.enums.Subscriptions
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.testops.services.LoginService

'Data Setup'
Credential user = Page.nav(Credential)
					.getCredentials()
					.withRole('Owner')
					.inAccount('CE New Customer')
					.getFirst()
										
'Get api token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).getToken()

'Clean data subscription'
Page.nav(RecurlyService).terminateAllAccountSubscriptions(user.accountId)

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'Select account'
Page.nav(WelcomePage).selectAccount(user.accountId).sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()

'User wants to choose the Katalon Platform - Platform'
Page.nav(SubscriptionManagementPage).clickPlatformTabButton()
									.verifyCheckoutButtonIsDisable()
									.selectPlatformPlan("Premium 1 - 3,500 Test Results")
									.sleep(10)
									.verifyPlatformCheckoutPrice("\$399")
									.setPlatformBillingCycle("Monthly")
									.verifyPlatformCheckoutPrice("\$41")
									
'User wants to choose the Katalon Platform - KSE'
Page.nav(SubscriptionManagementPage).inputKSEQuota("1")		
									.setKSEBillingCycle("Annual")
 									.verifyKSEListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KSE_PERUSER_ANNUAL)} /per License")
									.verifyKSECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KSE_PERUSER_ANNUAL, 1)}")
									.setKSEBillingCycle("Monthly")
 									.verifyKSEListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KSE_PERUSER_MONTHLY)} /per License")
									.verifyKSECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KSE_PERUSER_MONTHLY, 1)}")
									
Page.nav(SubscriptionManagementPage).inputKREQuota("1")
									.setKREBillingCycle("Annual")
									.verifyKREListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KRE_FLOATING_ANNUAL)} /per License")
									.verifyKRECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KRE_FLOATING_ANNUAL, 1)}")
									.setKREBillingCycle("Monthly")
 									.verifyKREListedPrice("\$${Page.nav(PricingCalculationService).getListedPrice(Subscriptions.KRE_FlOATING_MONTHLY)} /per License")
									.verifyKRECheckoutPrice("\$${Page.nav(PricingCalculationService).getPreviewPrice(user.accountId, Subscriptions.KRE_FlOATING_MONTHLY, 1)}")
									
'User wants to choose the Katalon Platform - Test Cloud'
Page.nav(SubscriptionManagementPage).inputTestCloudQuota("1")
									.verifyTestCloudListedPrice("\$1,849 /per Session")
									.verifyTestCloudCheckoutPrice("\$1,849")
									.setTestCloudBillingCycle("Monthly")
									.verifyTestCloudListedPrice("\$192 /per Session")
									.verifyTestCloudCheckoutPrice("\$192")
