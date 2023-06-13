import external.services.RecurlyService
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.CheckoutDetailPage
import katalon.my.subscriptionmanagement.PaymentStatusPage
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
					
 'Subscription Manangement can cancel Katalon Platform subscription successfully'
 Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									 .clickPlatformEllipsisButton()
									 .clickTurnOffAutoRenewalPlatformButton()
									 .clickConfirmButton()
									 .anwserSurveyCancelSubscription()
									 .verifyMessagePopupTurnOffAutoRenewal('Katalon Platform Premium')
									 .clickCloseToastMessage()
 
 'Subscription Manangement can reactivate Katalon Platform subscription successfully'
 Page.nav(SubscriptionManagementPage).clickTurnOnAutoRenewalPlatformButton()
									 .clickConfirmButton()
									 .verifyMessagePopupTurnOnAutoRenewal('Katalon Platform Premium')
									 .clickCloseToastMessage()
						 
'Subscription Manangement can cancel KSE subscription successfully'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon Studio Enterprise (per-User)')
						 			.clickTurnOffAutoRenewal()
									.clickConfirmButton()
									.anwserSurveyCancelSubscription()
									.verifyMessagePopupTurnOffAutoRenewal('Katalon Studio Enterprise (per-User)')
									.clickCloseToastMessage()

'Subscription Manangement can reactivate KSE subscription successfully'
Page.nav(SubscriptionManagementPage).clickEllipsisTurnOnProducts('Katalon Studio Enterprise (per-User)')
									.verifyButtonTurnOnAutoRenewalVisible()
									.clickTurnOnAutoRenewal()
									.clickConfirmButton()
									.verifyMessagePopupTurnOnAutoRenewal('Katalon Studio Enterprise (per-User)')
									.clickCloseToastMessage()
									
'Subscription Manangement can cancel KRE subscription successfully'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon Runtime Engine (Floating)')
									.clickTurnOffAutoRenewal()
									.clickConfirmButton()
									.anwserSurveyCancelSubscription()
									.verifyMessagePopupTurnOffAutoRenewal('Katalon Runtime Engine (Floating)')
									.clickCloseToastMessage()

'Subscription Manangement can reactivate KRE subscription successfully'
Page.nav(SubscriptionManagementPage).clickEllipsisTurnOnProducts('Katalon Runtime Engine (Floating)')
									.verifyButtonTurnOnAutoRenewalVisible()
									.clickTurnOnAutoRenewal()
									.clickConfirmButton()
									.verifyMessagePopupTurnOnAutoRenewal('Katalon Runtime Engine (Floating)')
									.clickCloseToastMessage()
									
'Subscription Manangement can cancel Katalon TestCloud subscription successfully'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.clickEllipsisProducts('Katalon TestCloud')
									.clickTurnOffAutoRenewal()
									.clickConfirmButton()
									.anwserSurveyCancelSubscription()
									.verifyMessagePopupTurnOffAutoRenewal('Katalon TestCloud')
									.clickCloseToastMessage()

'Subscription Manangement can reactivate Katalon TestCloud subscription successfully'
Page.nav(SubscriptionManagementPage).clickEllipsisTurnOnProducts('Katalon TestCloud')
									.verifyButtonTurnOnAutoRenewalVisible()
									.clickTurnOnAutoRenewal()
									.clickConfirmButton()
									.verifyMessagePopupTurnOnAutoRenewal('Katalon TestCloud')
 


