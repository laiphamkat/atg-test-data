import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.account.CreateNewAccountPopUp
import katalon.my.subscriptionmanagement.SubscriptionManagementPage
import katalon.services.MyAccountService
import katalon.testops.services.LoginService

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

'Verify UI when users able to click on package Platform'
'Verify UI when users selected package Platform 1 Starter'
Page.nav(SubscriptionManagementPage).clickPlatformTabButton()
									.verifyPackageUIBeforeSelect('Package 1')
									.clickSelectPackageButton('Package 1')
									.verifyAfterClickSelectPackage('Package 1')
									//Katalon Platform
									.verifyCurrentPlatformDropdownSelected("Premium 1 - 3,500 Test Results")
									.verifyToggleKatalonPlatformCurrentBillingCycle("Annual")
									.verifyPlatformCheckoutPrice("\$399")
									//Katalon Studio per-User
									.verifyKSEQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Studio Enterprise',"Annual")
									.verifyKSECheckoutPrice("\$1,999")
									//Katalon Runtime Engine
									.verifyKREQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Runtime Engine',"Annual")
									.verifyKRECheckoutPrice("\$1,599")
									.clickDeselectPackageButton('Package 1')
									//Katalon TestCloud
									.verifyTestCloudQuotaInput('0')
									.verifyTestCloudListedPrice("\$1,849 /per Session")
									.verifyToggleProductCurrentBillingCycle('Katalon TestCloud',"Annual")
									.verifyTestCloudCheckoutPrice("\$0")
									
'Verify UI when users selected package Platform 2 Starter'
Page.nav(SubscriptionManagementPage).verifyPackageUIBeforeSelect('Package 2')
									.clickSelectPackageButton('Package 2')
									.verifyAfterClickSelectPackage('Package 2')
									//Katalon Platform
									.verifyCurrentPlatformDropdownSelected("Premium 1 - 3,500 Test Results")
									.verifyToggleKatalonPlatformCurrentBillingCycle("Annual")
									.verifyPlatformCheckoutPrice("\$399")
									//Katalon Studio per-User
									.verifyKSEQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Studio Enterprise',"Annual")
									.verifyKSECheckoutPrice("\$1,999")
									//Katalon TestCloud
									.verifyTestCloudQuotaInput('1')
									.verifyTestCloudListedPrice("\$1,849 /per Session")
									.verifyToggleProductCurrentBillingCycle('Katalon TestCloud',"Annual")
									.verifyTestCloudCheckoutPrice("\$1,849")
									
'Verify UI when users able to click on package Standalone'
'Verify UI when users selected package Standalone package 1 Starter'
Page.nav(SubscriptionManagementPage).clickStandaloneTabButton()
									.verifyPackageUIBeforeSelect('Package 1')
									.clickSelectPackageButton('Package 1')
									.verifyAfterClickSelectPackage('Package 1')
									//Katalon Studio per-User
									.verifyKSEQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Studio Enterprise',"Annual")
									.verifyKSECheckoutPrice("\$1,999")
									//Katalon Runtime Engine
									.verifyKREQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Runtime Engine',"Annual")
									.verifyKRECheckoutPrice("\$1,599")
									
'Verify UI when users selected package Standalone package 2 Starter'
Page.nav(SubscriptionManagementPage).clickStandaloneTabButton()
									.verifyPackageUIBeforeSelect('Package 2')
									.clickSelectPackageButton('Package 2')
									.verifyAfterClickSelectPackage('Package 2')
									//Katalon Studio per-User
									.verifyKSEQuotaInput('2')
									.verifyToggleProductCurrentBillingCycle('Katalon Studio Enterprise',"Annual")
									.verifyKSECheckoutPrice("\$3,998")
									//Katalon Runtime Engine
									.verifyKREQuotaInput('0')
									.verifyToggleProductCurrentBillingCycle('Katalon Runtime Engine',"Annual")
									.verifyKRECheckoutPrice("\$0")
									
'Verify UI when users selected package Standalone package 3 Starter'
Page.nav(SubscriptionManagementPage).clickStandaloneTabButton()
									.verifyPackageUIBeforeSelect('Package 3')
									.clickSelectPackageButton('Package 3')
									.verifyAfterClickSelectPackage('Package 3')
									//Katalon Studio per-User
									.verifyKSEQuotaInput('1')
									.verifyToggleProductCurrentBillingCycle('Katalon Studio Enterprise',"Annual")
									.verifyKSECheckoutPrice("\$1,999")
									//Katalon Runtime Engine
									.verifyKREQuotaInput('0')
									.verifyToggleProductCurrentBillingCycle('Katalon Runtime Engine',"Annual")
									.verifyKRECheckoutPrice("\$0")

