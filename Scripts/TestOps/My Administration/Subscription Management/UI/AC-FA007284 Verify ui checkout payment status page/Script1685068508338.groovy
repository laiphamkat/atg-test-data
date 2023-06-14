import external.services.RecurlyService
import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.subscriptionmanagement.CheckoutDetailPage
import katalon.my.subscriptionmanagement.PaymentStatusPage
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.testops.services.LoginService
import katalon.utility.DateTimeUtility as DateTimeUtility

'Data Setup'
Credential user = Page.nav(Credential)
					.getCredentials()
					.withRole('Owner')
					.inAccount('CE New Customer')
					.getFirst()
					
'Clean data subscription'
Page.nav(RecurlyService).terminateAllAccountSubscriptions(user.accountId)					
										
'Get api token'
Page.nav(LoginService).loginWithEncryptedPwd(user.email, user.pwd).getToken()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().verifySuccessfullySignIn()

'Select account'
Page.nav(WelcomePage).selectAccount(user.accountId).sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()
						 .sleepALittleTime()
						 
'Subscription Manangement Page and input quota'
Page.nav(SubscriptionManagementPage).clickPlatformTabButton()
						 .selectPlatformPlan("Premium 1 - 3,500 Test Results")
						 .inputKSEQuota("1")
						 .setKSEBillingCycle("Annual")
						 .inputKREQuota("1")
						 .setKREBillingCycle("Annual")
						 .inputTestCloudQuota("1")
						 .setTestCloudBillingCycle("Annual")
						 .sleep(10)
						 .clickCheckoutButton()

'Navigate to Checkout Detail Page and click Checkout'
Page.nav(CheckoutDetailPage).clickCheckoutButton()
						    .sleepALittleTime()
						 
'Verify Payment Processing page show data correctly'
'Order Summary Product section'
Page.nav(PaymentStatusPage).verifyTextOnPaymentStatusPage('Keep track of your purchases',"h2")
						   .verifyTextOnPaymentStatusPage('Keep track of your purchases in the payment status. You will receive an email when the payment completes.',"h3")
						   .verifyTextOnPaymentStatusPage('Order Summary',"h2")
						   .verifyTextOnPaymentStatusPage('Product',"th")
						   .verifyTextOnPaymentStatusPage('Description',"th")
						   .verifyTextOnPaymentStatusPage('Pay Amount (USD)',"th")
						   .verifyTextOnPaymentStatusPage('Payment Status',"th")
						   .verifyButtonBackToSubscriptionManagement()
						   .verifyQuotaKatalonPlatform("3500")
						   .verifyQuotaKSE("1")
						   .verifyQuotaKRE("1")
						   .verifyQuotaKatalonTC("1")
						   
'Order Summary Description section'
def currentDate = DateTimeUtility.currentDay("MMMM dd, yyyy")
def nextYear = DateTimeUtility.nextYear("MMMM dd, yyyy")
Page.nav(PaymentStatusPage).verifyDescriptionKatalonPlatform("Annual","$currentDate - $nextYear")
							.verifyDescriptionKSE("Annual","$currentDate - $nextYear")
							.verifyDescriptionKRE("Annual","$currentDate - $nextYear")
							.verifyDescriptionKatalonTC("Annual","$currentDate - $nextYear")
							
'Order Summary Payment Status section when in processing'
Page.nav(PaymentStatusPage).verifyPaymentStatusKatalonPlatform("Processing")
							.verifyPaymentStatusKSE("Processing")
							.verifyPaymentStatusKRE("Processing")
							.verifyPaymentStatusTC("Processing")
							.sleep(30)

'Order Summary Payment Status section when paid successfully'
Page.nav(PaymentStatusPage).verifyPaymentStatusKatalonPlatform("Paid")
							.verifyPaymentStatusKSE("Paid")
							.verifyPaymentStatusKRE("Paid")
							.verifyPaymentStatusTC("Paid")
							.clickBackSubscriptionManagementButton()
							.sleep(2)
							
def nextYearYourSubscription = DateTimeUtility.nextYear("MMM d, yyyy")				
'Subscription Manangement Page and data licenses users purchased on Your Subscription'
Page.nav(SubscriptionManagementPage).scrollOnTopSubscriptionPage()
									.verifyBillingCycleProductOnYourSubscription("Katalon Studio Enterprise (per-User)", "Annual")
									.verifyQuantityProductOnYourSubscription("Katalon Studio Enterprise (per-User)","1 License(s)")
									.verifyExpiryDateProductOnYourSubscription("Katalon Studio Enterprise (per-User)",nextYearYourSubscription)
									.verifyBillingCycleProductOnYourSubscription("Katalon Runtime Engine (Floating)", "Annual")
									.verifyQuantityProductOnYourSubscription("Katalon Runtime Engine (Floating)","1 License(s)")
									.verifyExpiryDateProductOnYourSubscription("Katalon Runtime Engine (Floating)",nextYearYourSubscription)
									.verifyBillingCycleProductOnYourSubscription("Katalon TestCloud", "Annual")
									.verifyQuantityProductOnYourSubscription("Katalon TestCloud","1 Session(s)")
									.verifyExpiryDateProductOnYourSubscription("Katalon TestCloud",nextYearYourSubscription)
								