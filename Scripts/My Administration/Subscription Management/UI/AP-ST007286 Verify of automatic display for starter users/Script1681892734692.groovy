import com.kms.katalon.util.CryptoUtil

import katalon.common.MySignInPage
import katalon.common.SeedingData
import katalon.common.WelcomePage
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.services.MyAccountService
import katalon.services.UserService
import katalon.utility.DateTimeUtility as DateTimeUtility


'Data Setup'
Page.nav(UserService).initRequestObject().createWithoutStatusCheck(String.format('{"email":"%s","password":"%s"}', username, 
        CryptoUtil.decode(CryptoUtil.getDefault(password))))

Page.nav(SeedingData).setUpKatOneToken(username, password)

Page.nav(MyAccountService).initRequestObject().getAccountId(accountName).setDeleteURL().deleteWithoutStatusCheck()

'User signs in to administration page'
Page.nav(MySignInPage).enterCredential(username, password).clickSignIn().verifySuccessfullySignIn()

Page.nav(WelcomePage).clickCreateANewAccount()

Page.nav(CreateNewAccountPopUp).inputAccountName(accountName).inputOrganizationtName(organizationName).inputProjectName(
    projectName).clickCreate().sleepALittleTime()

'Navigate to Subscription Manangement Page'					
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()

'Tab Platform'
'Verify default quota for new users - Platform'
Page.nav(SubscriptionManagementPage).verifyCheckoutButtonIsEnable()
									.verifyCurrentPlatformTier("Premium 1 - 3,500 Test Results")
									.verifyPlatformCheckoutPrice("\$399")
									
'Verify default quota for new users - KSE'
Page.nav(SubscriptionManagementPage).verifyKatalonStudioEnterpriseQuotaInput("1")			
 									.verifyKatalonStudioEnterpriseListedPrice("\$1999 /per License")
									.verifyKatalonStudioEnterpriseCheckoutPrice("\$1,999")

'Verify default quota for new users - KRE'
Page.nav(SubscriptionManagementPage).verifyKatalonRuntimeEngineQuotaInput("1")
									.verifyKatalonRuntimeEngineListedPrice("\$1599 /per License")
									.verifyKatalonRuntimeEngineCheckoutPrice("\$1,599")
																		
'Tab Standalone'			
'Verify default quota for new users - KSE'
Page.nav(SubscriptionManagementPage).clickStandaloneTabButton()
									.verifyKatalonStudioEnterpriseQuotaInput("1")
									.verifyKatalonStudioEnterpriseListedPrice("\$1999 /per License")
									.verifyKatalonStudioEnterpriseCheckoutPrice("\$1,999")

'Verify default quota for new users - KRE'
Page.nav(SubscriptionManagementPage).verifyKatalonRuntimeEngineQuotaInput("1")
									.verifyKatalonRuntimeEngineListedPrice("\$1599 /per License")
									.verifyKatalonRuntimeEngineCheckoutPrice("\$1,599")
																											