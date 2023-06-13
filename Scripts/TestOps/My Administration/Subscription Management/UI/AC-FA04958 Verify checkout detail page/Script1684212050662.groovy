import com.kms.katalon.util.CryptoUtil

import katalon.common.MySignInPage
import katalon.common.SeedingData
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.CheckoutDetailPage
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.services.MyAccountService
import katalon.services.UserService as UserService

'Data Setup'
Page.nav(UserService).initRequestObject().createWithoutStatusCheck(String.format('{"email":"%s","password":"%s"}', username, 
        CryptoUtil.decode(CryptoUtil.getDefault(password))))

Page.nav(SeedingData).setUpKatOneToken(username, password)

Page.nav(MyAccountService).initRequestObject().getAccountId(accountName).setDeleteURL().deleteWithoutStatusCheck()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

Page.nav(WelcomePage).clickCreateANewAccount()

Page.nav(CreateNewAccountPopUp).inputAccountName(accountName)
							   .inputOrganizationtName(organizationName)
							   .inputProjectName(projectName)
							   .clickCreate()
							   .sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()
						 .sleepALittleTime()

Page.nav(SubscriptionManagementPage).clickPlatformTabButton()
									.selectPlatformPlan("Premium 1 - 3,500 Test Results")
									.verifyPlatformCheckoutPrice("\$399")
									.inputKSEQuota("1")
									.setKSEBillingCycle("Annual")
									.inputKREQuota("1")
									.setKREBillingCycle("Annual")
									.inputTestCloudQuota("1")
									.setTestCloudBillingCycle("Annual")
									.verifyCheckoutButtonIsEnable()
									.verifyTotalCheckoutPrice("\$5,846")
									.clickCheckoutButton()

Page.nav(CheckoutDetailPage).inputCardNumber("4242424242424242")
							.inputCardExpireDate("1230")
							.inputCVC("123")
							.inputCardHolder("User Payment")
							.clickSavePaymentMethod()
							.sleepALittleTime()
							.verifySavedCardNumber("Visa **** 4242")
							.verifySavedCardExpiredDate("Expires 12/2030")
							.verifyCardLogoExist()
							
Page.nav(CheckoutDetailPage).inputAddressLine1("Brougham")
							.inputCity("Melbourne")
							.inputCountry("Australia")
							.inputState("North Alderline")
							.inputPostalCode("3000")
							.inputBusinessName("Aeon")
							.clickSaveBillingInfo()
							.clickCheckoutButton()



							
