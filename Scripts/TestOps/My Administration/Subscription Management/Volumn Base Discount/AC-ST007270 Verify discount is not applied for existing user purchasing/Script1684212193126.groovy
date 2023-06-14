import katalon.common.MySignInPage
import katalon.common.WelcomePage
import katalon.fw.lib.Credential
import katalon.fw.lib.Page
import katalon.my.account.AccountHomePage
import katalon.my.subscriptionmanagement.SubscriptionManagementPage

'1. Org have at least 1 new product (KSE per-User, v.v..) and didnt have volume base subscription before'
'2. From Subscription Manangement Page, try to buy more products'
'3. There is not any discount applied'

'Pre-condition: Find a user to log in'
Credential user = Page.nav(Credential)
	.getCredentials()
	.forThisTestCase()
	.getFirst()
					
'User navigates to My Admin Page'					
Page.nav(MySignInPage).enterCredential(user.email, user.pwd).clickSignIn().sleep(2)

'2. User selects the account'
Page.nav(WelcomePage).selectAccount(user.accountId).sleep(5)

'3. Click nav to Subscribe Subscription page'
Page.nav(AccountHomePage).clickSubscribeToKatalonProducts()

Page.nav(SubscriptionManagementPage).clickEllipsisProducts("Katalon Studio Enterprise (per-User)")
									.clickBuyMore()
									.verifyDiscountPrice("- \$0")
									
Page.nav(SubscriptionManagementPage).clickEllipsisProducts("Katalon Runtime Engine (Floating)")
									.clickBuyMore()
									.verifyDiscountPrice("- \$0")

Page.nav(SubscriptionManagementPage).clickEllipsisProducts("Katalon TestCloud")
									.clickBuyMore()
									.verifyDiscountPrice("- \$0")