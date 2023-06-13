package katalon.my.subscriptionmanagement

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import katalon.fw.lib.BasePage

public class SubscriptionManagementPage extends BasePage<SubscriptionManagementPage> {


	//Your Subscription Section
	def platformCurrentPlan = xpath("//h3[text()='Your Subscription']/following::div//h3")
	def platformResultUsage = xpath("//div[text()='Usage']/following-sibling::div/b")
	def platformExpiredDate = xpath("//p[text()='Expiry Date']/following-sibling::div/b")
	def platformOrgQuota 	= xpath("//div[p[text()='Monthly Test Results']]/following-sibling::div/div")
	def upgradeBtn = id("subscriptions.testops_platform.platform_subscription_info.upgrade.btn")
	def ellipsisBtn = id("subscriptions.testops_platform.platform_subscription_info.cancel_trial.btn")
	def cancelTrialOption = xpath("//li[@role='menuitem' and text() = 'Cancel Trial']")
	def productBillingCycle = { String product -> xpath("//div[div[contains(text(),'${product}')]]/div[2]") }
	def productQuota = { String product -> xpath("//div[div[contains(text(),'${product}')]]/div[3]") }
	def productExpiryDate = { String product -> xpath("//div[div[contains(text(),'${product}')]]/div[4]") }
	//Offering Section
	def totalCheckoutPrice = xpath("//div[contains(@class,'MuiBox-root')]/h2")
	def productYourSubscriptionSection = { String product -> return "//*[text()='Product']/ancestor::div[2]//div[text()='${product}']"}
	def productNameYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}")}
	def billingCycleYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}//following::div[1]")}
	def quantityYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}//following::div[2]")}
	def expiryDateYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}//following::div[3]")}
	def ellipsisProductsYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}//following::div[4]//div[contains(@class, 'jss')]")}
	def ellipsisProductsTurnOnYourSubscription = { String product -> return xpath("${productYourSubscriptionSection(product)}//following::div[5]//div[contains(@class, 'jss')]")}
	def turnOnAutoRenewalYourSubscription = xpath("//div[contains(@class,'MuiPopover-paper')]//li[text()='Turn on Auto-Renewal']")
	def turnOffAutoRenewalYourSubscription = xpath("//div[contains(@class,'MuiPopover-paper')]//li[text()='Turn off Auto-Renewal']")
	def buyMoreYourSubscription = xpath("//div[contains(@class,'MuiPopover-paper')]//li[text()='Buy more']")
	def productSection = { String product -> return "//div[@role='tabpanel']/div[contains(@class, 'MuiBox-root') and descendant::p[contains(text(),'${product}')]]" }
	def platformSection = "//div[@role='tabpanel']/div[contains(@class, 'MuiBox-root') and descendant::div[@role='button']]"
	def platformBillingCycleToggle =  xpath("${platformSection}//input[@type='checkbox']")
	def platformBillingCycleTogglePoint = xpath("${platformSection}//span[input]")
	def platformcheckoutPrice = xpath("${platformSection}//h6")
	def platformPlanDropdown = xpath("//div[@role='tabpanel']/div[contains(@class, 'MuiBox-root')]/descendant::div[@role='button']")
	def platformPlanOption = { String plan -> return xpath("//ul/li[text()='${plan}']") }
	def quotaInput = { String product -> return xpath("${productSection(product)}//input[@type='number']") }
	def billingCycleToggle = { String product -> return xpath("${productSection(product)}//input[@type='checkbox']") }
	def billingCycleTogglePoint = { String product -> return xpath("${productSection(product)}//span[input]") }
	def listedPrice = { String product -> return xpath("${productSection(product)}//p[contains(text(),'/per')]") }
	def checkoutPrice = { String product -> return xpath("${productSection(product)}//h6") }
	def totalDiscountPrice = xpath("//p[text()='Discount']//following::p")
	def toastMessage = xpath("//*[@id = 'notistack-snackbar']")
	def closeToastMessage = xpath("//*[@id = 'notistack-snackbar']/following::div/button")
	def buttonTurnOffAutoRenewalPlatform = xpath("//div[@id='popover-dropdown-toggle']//following::li[text()='Turn off Auto-Renewal']")
	def buttonTurnOnAutoRenewalPlatform = xpath("//button[text()='Turn on Auto-Renewal']")
	//stater package
	def boxPackage = { String packageName -> xpath("//*[contains (@class, 'MuiGridList-root')]//span[contains (text(), '$packageName')]/parent::div")}
	def selectPackageButton = { String packageName -> xpath("//*[text() = '$packageName']/following-sibling::button[text() = 'Select']")}
	def deselectPackageButton = { String packageName -> xpath("//*[text() = '$packageName']/following-sibling::button[text() = 'Deselect']")}
	def textSelectedPackage = { String packageName -> xpath("//*[contains (text(), '$packageName')]/following-sibling::p[text()= 'Selected']")}
	def textExclusivePricePackage = { String packageName -> xpath("//*[contains (text(), '$packageName')]/following-sibling::p[text()= 'Exclusive price']")}

	public SubscriptionManagementPage verifyTextUIVisible(String textVerify) {
		WebUI.verifyElementVisible(text(textVerify))
		return this
	}

	public SubscriptionManagementPage verifyTextUINotVisible(String textVerify) {
		WebUI.verifyElementNotPresent(text(textVerify), 10)
		return this
	}

	public SubscriptionManagementPage verifyTextUIHaveElementVisible(String textVerify, String element) {
		WebUI.verifyElementVisible(xpath("//$element[text()='$textVerify']"))
		return this
	}

	public SubscriptionManagementPage verifyButtonUIVisible(String btnName) {
		WebUI.verifyElementVisible(btn(btnName))
		WebUI.verifyElementClickable(btn(btnName))
		return this
	}

	public SubscriptionManagementPage verifyButtonUIDisable(String btnName) {
		WebUI.verifyElementNotClickable(btn(btnName))
		return this
	}

	public SubscriptionManagementPage verifyQuotaInputUIVisible(String productName) {
		WebUI.verifyElementVisible(quotaInput(productName))
		return this
	}

	public SubscriptionManagementPage verifyToggleUIKatalonPlatformVisible() {
		WebUI.verifyElementVisible(platformBillingCycleTogglePoint)
		WebUI.verifyElementClickable(platformBillingCycleTogglePoint)
		return this
	}

	public SubscriptionManagementPage verifyContentDiscountBanner(String textVerify) {
		def textValue = WebUI.getText(xpath("//p[text()='Get a']"))
		WebUI.verifyEqual(textValue, textVerify)
		return this
	}

	public SubscriptionManagementPage verifyToggleUIProductVisible(String productName) {
		WebUI.verifyElementVisible(billingCycleTogglePoint(productName))
		return this
	}

	public SubscriptionManagementPage verifyToggleKatalonPlatformCurrentBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(platformBillingCycleTogglePoint, "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		if(isToggleChecked == false) {
			WebUI.verifyEqual("Annual", value)
		}
		else
		{
			WebUI.verifyEqual("Monthly", value)
		}
		return this
	}

	public SubscriptionManagementPage verifyToggleProductCurrentBillingCycle(String productName,String value) {
		String classAttribute = WebUI.getAttribute(billingCycleTogglePoint("$productName"), "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		if(isToggleChecked == false) {
			WebUI.verifyEqual("Annual", value)
		}
		else
		{
			WebUI.verifyEqual("Monthly", value)
		}
		return this
	}

	public SubscriptionManagementPage verifyCurrentPlatformTier(String currentPlan) {
		WebUI.verifyElementVisible(platformCurrentPlan)
		WebUI.verifyElementText(platformCurrentPlan, currentPlan)
		return this
	}

	public SubscriptionManagementPage verifyPlatformTestResultQuota(String quota) {
		WebUI.verifyElementVisible(platformResultUsage)
		WebUI.verifyElementText(platformResultUsage, quota)
		return this
	}

	public SubscriptionManagementPage verifyPlatformExpiredDate(String Date) {
		WebUI.verifyElementVisible(platformExpiredDate)
		WebUI.verifyElementText(platformExpiredDate, Date)
		return this
	}

	public SubscriptionManagementPage verifyPlatformOrganizationQuota(String orgQuota) {
		WebUI.verifyElementVisible(platformOrgQuota)
		String actualOrgQuota = WebUI.getText(platformOrgQuota).replaceAll("\u00a0"," ").replace("\n", "")
		WebUI.verifyMatch(actualOrgQuota, orgQuota, false)
		return this
	}

	public SubscriptionManagementPage verifyPlatformUpgradeButtonVisible() {
		WebUI.verifyElementVisible(platformOrgQuota)
		return this
	}

	public SubscriptionManagementPage verifyPlatformEllipsisButton() {
		WebUI.verifyElementVisible(ellipsisBtn)
		WebUI.click(ellipsisBtn)
		WebUI.verifyElementVisible(cancelTrialOption)
		WebUI.click(id('popover-dropdown-toggle'))
		return this
	}

	public SubscriptionManagementPage verifyCurrentPlatformDropdownSelected(String currentPlan = "") {
		WebUI.verifyElementVisible(platformPlanDropdown)
		WebUI.verifyElementText(platformPlanDropdown, currentPlan)
		return this
	}

	public SubscriptionManagementPage inputKSEQuota(String value) {
		def productQuotaObj = quotaInput('Katalon Studio Enterprise')
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.sendKeys(productQuotaObj, value)
		sleepALittleTime()
		return this
	}

	public SubscriptionManagementPage verifyKSEQuotaInput(String expectedValue) {
		def productQuotaObj = quotaInput('Katalon Studio Enterprise')
		def quotaInput = WebUI.getAttribute(productQuotaObj,"value").toString()
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.verifyEqual(quotaInput,expectedValue)
		return this
	}

	public SubscriptionManagementPage setKSEBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(billingCycleTogglePoint('Katalon Studio Enterprise'), "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		boolean isMonthlyCycle = value.equals("Monthly")
		if(isToggleChecked != isMonthlyCycle) {
			WebUI.click(billingCycleToggle('Katalon Studio Enterprise'))
		}
		return this
	}

	public SubscriptionManagementPage verifyKSEListedPrice(String expectedListPrice) {
		def productListedPriceObj = listedPrice('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(productListedPriceObj)
		WebUI.verifyElementText(productListedPriceObj, expectedListPrice)
		return this
	}

	public SubscriptionManagementPage verifyKSECheckoutPrice(String expectedCheckoutPrice) {
		def checkoutPriceObj = checkoutPrice('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(checkoutPriceObj)
		WebUI.verifyElementText(checkoutPriceObj, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage inputKREQuota(String value) {
		def productQuotaObj = quotaInput('Katalon Runtime Engine')
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.sendKeys(productQuotaObj, value)
		sleepALittleTime()
		return this
	}

	public SubscriptionManagementPage verifyKREQuotaInput(String expectedValue) {
		def productQuotaObj = quotaInput('Katalon Runtime Engine')
		def quotaInput = WebUI.getAttribute(productQuotaObj,"value").toString()
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.verifyEqual(quotaInput,expectedValue)
		return this
	}

	public SubscriptionManagementPage setKREBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(billingCycleTogglePoint('Katalon Runtime Engine'), "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		boolean isMonthlyCycle = value.equals("Monthly")
		if(isToggleChecked != isMonthlyCycle) {
			WebUI.click(billingCycleToggle('Katalon Runtime Engine'))
		}
		return this
	}

	public SubscriptionManagementPage verifyKREListedPrice(String expectedListPrice) {
		def productListedPriceObj = listedPrice('Katalon Runtime Engine')
		WebUI.verifyElementVisible(productListedPriceObj)
		WebUI.verifyElementText(productListedPriceObj, expectedListPrice)
		return this
	}

	public SubscriptionManagementPage verifyKRECheckoutPrice(String expectedCheckoutPrice) {
		def checkoutPriceObj = checkoutPrice('Katalon Runtime Engine')
		WebUI.verifyElementVisible(checkoutPriceObj)
		WebUI.verifyElementText(checkoutPriceObj, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage inputTestCloudQuota(String value) {
		def productQuotaObj = quotaInput('Katalon TestCloud')
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.sendKeys(productQuotaObj, value)
		sleepALittleTime()
		return this
	}

	public SubscriptionManagementPage verifyTestCloudQuotaInput(String expectedValue) {
		def productQuotaObj = quotaInput('Katalon TestCloud')
		def quotaInput = WebUI.getAttribute(productQuotaObj,"value").toString()
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.verifyEqual(quotaInput,expectedValue)
		return this
	}

	public SubscriptionManagementPage setTestCloudBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(billingCycleTogglePoint('Katalon TestCloud'), "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		boolean isMonthlyCycle = value.equals("Monthly")
		if(isToggleChecked != isMonthlyCycle) {
			WebUI.click(billingCycleToggle('Katalon TestCloud'))
		}
		return this
	}

	public SubscriptionManagementPage verifyTestCloudListedPrice(String expectedListPrice) {
		def productListedPriceObj = listedPrice('Katalon TestCloud')
		WebUI.verifyElementVisible(productListedPriceObj)
		WebUI.verifyElementText(productListedPriceObj, expectedListPrice)
		return this
	}

	public SubscriptionManagementPage verifyTestCloudCheckoutPrice(String expectedCheckoutPrice) {
		def checkoutPriceObj = checkoutPrice('Katalon TestCloud')
		WebUI.verifyElementVisible(checkoutPriceObj)
		WebUI.verifyElementText(checkoutPriceObj, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage inputVTPQuota(String value) {
		def productQuotaObj = quotaInput('Visual Testing Professional')
		WebUI.scrollToElement(productQuotaObj, GlobalVariable.smallSleepTime)
		WebUI.verifyElementVisible(productQuotaObj)
		WebUI.sendKeys(productQuotaObj, value)
		sleepALittleTime()
		return this
	}

	public SubscriptionManagementPage setVTPBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(billingCycleTogglePoint('Visual Testing Professional'), "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		boolean isMonthlyCycle = value.equals("Monthly")
		if(isToggleChecked != isMonthlyCycle) {
			WebUI.click(billingCycleToggle('Visual Testing Professional'))
		}
		return this
	}

	public SubscriptionManagementPage verifyVTPListedPrice(String expectedListPrice) {
		def productListedPriceObj = listedPrice('Visual Testing Professional')
		WebUI.verifyElementVisible(productListedPriceObj)
		WebUI.verifyElementText(productListedPriceObj, expectedListPrice)
		return this
	}

	public SubscriptionManagementPage verifyVTPCheckoutPrice(String expectedCheckoutPrice) {
		def checkoutPriceObj = checkoutPrice('Visual Testing Professional')
		WebUI.verifyElementVisible(checkoutPriceObj)
		WebUI.verifyElementText(checkoutPriceObj, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage setPlatformBillingCycle(String value) {
		String classAttribute = WebUI.getAttribute(platformBillingCycleTogglePoint, "class")
		boolean isToggleChecked = classAttribute.contains("Mui-checked")
		boolean isMonthlyCycle = value.equals("Monthly")
		if(isToggleChecked != isMonthlyCycle) {
			WebUI.click(platformBillingCycleToggle)
		}
		return this
	}

	public SubscriptionManagementPage selectPlatformPlan(String plan) {
		WebUI.verifyElementVisible(platformPlanDropdown)
		WebUI.scrollToElement(platformPlanDropdown,  GlobalVariable.smallSleepTime)
		WebUI.click(platformPlanDropdown)
		WebUI.click(platformPlanOption(plan))
		return this
	}

	public SubscriptionManagementPage verifyPlatformCheckoutPrice(String expectedCheckoutPrice) {
		WebUI.verifyElementVisible(platformcheckoutPrice)
		WebUI.verifyElementText(platformcheckoutPrice, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage verifyCheckoutButtonIsDisable() {
		WebUI.verifyElementNotClickable(btn("Checkout"))
		return this
	}

	public SubscriptionManagementPage verifyCheckoutButtonIsEnable() {
		WebUI.verifyElementClickable(btn("Checkout"))
		return this
	}

	public SubscriptionManagementPage verifyTotalCheckoutPrice(String expectedCheckoutPrice) {
		WebUI.verifyElementVisible(totalCheckoutPrice)
		WebUI.verifyElementText(totalCheckoutPrice, expectedCheckoutPrice)
		return this
	}

	public SubscriptionManagementPage verifyDiscountPrice(String expectedDiscountPrice) {
		WebUI.verifyElementVisible(totalDiscountPrice)
		WebUI.verifyElementText(totalDiscountPrice, expectedDiscountPrice)
		return this
	}

	public SubscriptionManagementPage clickEllipsisProducts(String productName) {
		WebUI.waitForElementPresent(ellipsisProductsYourSubscription("$productName"),5)
		WebUI.click(ellipsisProductsYourSubscription("$productName"))
		return this
	}

	public SubscriptionManagementPage clickEllipsisTurnOnProducts(String productName) {
		WebUI.waitForElementPresent(ellipsisProductsTurnOnYourSubscription("$productName"),5)
		WebUI.click(ellipsisProductsTurnOnYourSubscription("$productName"))
		return this
	}

	public SubscriptionManagementPage clickTurnOffAutoRenewal() {
		WebUI.click(turnOffAutoRenewalYourSubscription)
		return this
	}

	public SubscriptionManagementPage clickTurnOnAutoRenewal() {
		WebUI.click(turnOnAutoRenewalYourSubscription)
		return this
	}

	public SubscriptionManagementPage clickBuyMore() {
		WebUI.click(buyMoreYourSubscription)
		return this
	}

	public SubscriptionManagementPage scrollOnTopSubscriptionPage() {
		def productYourSubscriptionObj = platformCurrentPlan
		WebUI.scrollToElement(productYourSubscriptionObj, GlobalVariable.smallSleepTime)
		return this
	}

	public SubscriptionManagementPage verifyBillingCycleProductOnYourSubscription(String productName,String expectedResult) {
		def actualResult = WebUI.getText(billingCycleYourSubscription("$productName"))
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionManagementPage verifyQuantityProductOnYourSubscription(String productName,String expectedResult) {
		def actualResult = WebUI.getText(quantityYourSubscription("$productName"))
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionManagementPage verifyExpiryDateProductOnYourSubscription(String productName,String expectedResult) {
		def actualResult = WebUI.getText(expiryDateYourSubscription("$productName"))
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionManagementPage clickPlatformTabButton() {
		WebUI.click(btn("Platform"))
		return this
	}

	public SubscriptionManagementPage clickStandaloneTabButton() {
		WebUI.click(btn("Standalone"))
		return this
	}

	public SubscriptionManagementPage clickCheckoutButton() {
		WebUI.click(btn("Checkout"))
		return this
	}

	public SubscriptionManagementPage clickConfirmButton() {
		WebUI.click(btn("Confirm"), FailureHandling.CONTINUE_ON_FAILURE)
		return this
	}

	public SubscriptionManagementPage clickUpgradePlatformButton() {
		WebUI.click(upgradeBtn)
		return this
	}

	public SubscriptionManagementPage anwserSurveyCancelSubscription() {
		WebUI.waitForElementPresent(xpath("//*[@id='survicate-box']//div[@role='button']"),5)
		WebUI.switchToFrame(id("cancellationIframe"), 5)
		WebUI.click(xpath("//*[@id='survicate-box']//div[@role='button']"))
		WebUI.click(xpath("//*[@id='survicate-box']//button[text()='Next']"))
		WebUI.sendKeys(xpath("//*[@id='survicate-box']//textarea"), "abc")
		WebUI.click(xpath("//*[@id='survicate-box']//button[text()='Next']"))
		WebUI.click(xpath("//*[@id='survicate-box']//button[text()='Submit']"), FailureHandling.CONTINUE_ON_FAILURE)
		WebUI.switchToDefaultContent()
		return this
	}

	//starter package
	public SubscriptionManagementPage clickSelectPackageButton(String packageName) {
		WebUI.scrollToElement(boxPackage("$packageName"), GlobalVariable.smallSleepTime)
		WebUI.click(selectPackageButton("$packageName"))
		return this
	}

	public SubscriptionManagementPage clickDeselectPackageButton(String packageName) {
		WebUI.scrollToElement(boxPackage("$packageName"), GlobalVariable.smallSleepTime)
		WebUI.click(deselectPackageButton("$packageName"))
		return this
	}

	public SubscriptionManagementPage clickCloseToastMessage() {
		WebUI.click(closeToastMessage, FailureHandling.CONTINUE_ON_FAILURE)
		return this
	}

	public SubscriptionManagementPage clickPlatformEllipsisButton() {
		WebUI.click(ellipsisBtn)
		return this
	}

	public SubscriptionManagementPage clickTurnOnAutoRenewalPlatformButton() {
		WebUI.click(buttonTurnOnAutoRenewalPlatform)
		return this
	}

	public SubscriptionManagementPage clickTurnOffAutoRenewalPlatformButton() {
		WebUI.click(buttonTurnOffAutoRenewalPlatform)
		return this
	}

	public SubscriptionManagementPage verifyPackageUIBeforeSelect(String packageName) {
		WebUI.scrollToElement(boxPackage("$packageName"), GlobalVariable.smallSleepTime)
		def opacity = WebUI.getCSSValue(boxPackage("$packageName"), 'opacity')
		def borderColor = WebUI.getCSSValue(boxPackage("$packageName"), 'border-color')
		def selected = WebUI.getCSSValue(textExclusivePricePackage("$packageName"),'background-color')
		WebUI.verifyEqual(opacity, '1')
		WebUI.verifyEqual(borderColor, 'rgb(219, 221, 229)')
		WebUI.verifyEqual(selected, 'rgba(251, 192, 45, 1)')
		return this
	}

	public SubscriptionManagementPage verifyAfterClickSelectPackage(String packageName) {
		WebUI.scrollToElement(boxPackage("$packageName"), GlobalVariable.smallSleepTime)
		def opacityPackage = WebUI.getCSSValue(boxPackage("$packageName"), 'opacity')
		def borderColorPackage = WebUI.getCSSValue(boxPackage("$packageName"), 'border-color')
		def selectedPackage = WebUI.getCSSValue(textSelectedPackage("$packageName"),'background-color')
		WebUI.verifyEqual(opacityPackage, '1')
		WebUI.verifyEqual(borderColorPackage, 'rgb(15, 132, 97)')
		WebUI.verifyEqual(selectedPackage, 'rgba(15, 132, 97, 1)')
		return this
	}

	public SubscriptionManagementPage verifyMessagePopupTurnOffAutoRenewal(String productName) {
		WebUI.waitForElementPresent(toastMessage, 5)
		def actualResult = WebUI.getText(toastMessage)
		def expectedResult = "Auto-renewal for\n $productName \nhas been turned off successfully! You can turn it on again any time."
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionManagementPage verifyMessagePopupTurnOnAutoRenewal(String productName) {
		def actualResult = WebUI.getText(toastMessage)
		def expectedResult = "Auto-renewal for\n $productName \nhas been turned on successfully!"
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionManagementPage verifyButtonTurnOffAutoRenewalVisible() {
		WebUI.verifyElementVisible(turnOffAutoRenewalYourSubscription)
		WebUI.verifyElementClickable(turnOffAutoRenewalYourSubscription)
		return this
	}

	public SubscriptionManagementPage verifyButtonTurnOnAutoRenewalVisible() {
		WebUI.verifyElementVisible(turnOnAutoRenewalYourSubscription)
		WebUI.verifyElementClickable(turnOnAutoRenewalYourSubscription)
		return this
	}

	public SubscriptionManagementPage verifyVTPquota(String expectedQuota) {
		WebUI.verifyElementVisible(productQuota("Visual Testing Professional"))
		String actualOrgQuota = WebUI.getText(productQuota("Visual Testing Professional")).replaceAll("\u00a0"," ").replace("\n", "")
		WebUI.verifyMatch(actualOrgQuota, expectedQuota, false)
		return this
	}

}
