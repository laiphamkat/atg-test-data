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

'Verify Your Subscription Section'
Page.nav(SubscriptionManagementPage).verifyCurrentPlatformTier('Katalon Platform Premium 1 - Trial\nIf you need more support, do not hesitate to contact your dedicated Customer Success Manager directly or email success@katalon.com.')
									.verifyPlatformTestResultQuota('0/3500')
									.verifyPlatformExpiredDate(DateTimeUtility.next30Days())
									.verifyPlatformOrganizationQuota('1 Organization(s)/ 5 Project(s)/ Unlimited User(s)')
									.verifyPlatformUpgradeButtonVisible()
									.verifyPlatformEllipsisButton()