package katalon.my.subscriptionmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage
import katalon.utility.DateTimeUtility

public class PaymentStatusPage extends BasePage<PaymentStatusPage> {
	def productSection = { String product -> return "//td[contains(text(),'${product}')]" }
	def productName = { String product -> return xpath("${productSection(product)}") }
	def productDescription = { String product -> return xpath("${productSection(product)}//following::td[1]") }
	def productPayAmount = { String product -> return xpath("${productSection(product)}//following::td[2]") }
	def productPaymentStatus = { String product -> return xpath("${productSection(product)}//following::td[3]") }

	//Information Quota
	public PaymentStatusPage verifyTextOnPaymentStatusPage(String str,String element) {
		WebUI.verifyElementVisible(xpath("//$element[text() = '$str']"))
		return this
	}

	public PaymentStatusPage verifyButtonBackToSubscriptionManagement() {
		WebUI.verifyElementVisible(link("Back to Subscription Management"))
		return this
	}

	public PaymentStatusPage verifyQuotaKatalonPlatform(String value) {
		def quotaProduct = WebUI.getText(productName('Katalon Platform'))
		String expectedText = "Katalon Platform\n$value Test Results"
		WebUI.verifyEqual(quotaProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyQuotaKSE(String value) {
		def quotaProduct = WebUI.getText(productName('Katalon Studio Enterprise (per-User)'))
		String expectedText = "Katalon Studio Enterprise (per-User)\n$value License(s)"
		WebUI.verifyEqual(quotaProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyQuotaKRE(String value) {
		def quotaProduct = WebUI.getText(productName('Katalon Runtime Engine (Floating)'))
		String expectedText = "Katalon Runtime Engine (Floating)\n$value License(s)"
		WebUI.verifyEqual(quotaProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyQuotaKatalonTC(String value) {
		def quotaProduct = WebUI.getText(productName('Katalon TestCloud Per Session'))
		String expectedText = "Katalon TestCloud Per Session\n$value Session(s)"
		WebUI.verifyEqual(quotaProduct, expectedText)
		return this
	}

	public PaymentStatusPage verifyQuotaKatalonVTP(String value) {
		def quotaProduct = WebUI.getText(productName('Visual Testing Professional'))
		String expectedText = "Visual Testing Professional\n$value Checkpoint Images"
		WebUI.verifyEqual(quotaProduct, expectedText)
		return this
	}

	//Description
	public PaymentStatusPage verifyDescriptionKatalonPlatform(String billingCycle, String dateBillingCycle) {
		def descriptionProduct = WebUI.getText(productDescription('Katalon Platform'))
		String expectedText = "Bill $billingCycle\n$dateBillingCycle"
		WebUI.verifyEqual(descriptionProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyDescriptionKSE(String billingCycle, String dateBillingCycle) {
		def descriptionProduct = WebUI.getText(productDescription('Katalon Studio Enterprise (per-User)'))
		String expectedText = "Bill $billingCycle\n$dateBillingCycle"
		WebUI.verifyEqual(descriptionProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyDescriptionKRE(String billingCycle, String dateBillingCycle) {
		def descriptionProduct = WebUI.getText(productDescription('Katalon Runtime Engine (Floating)'))
		String expectedText = "Bill $billingCycle\n$dateBillingCycle"
		WebUI.verifyEqual(descriptionProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyDescriptionKatalonTC(String billingCycle, String dateBillingCycle) {
		def descriptionProduct = WebUI.getText(productDescription('Katalon TestCloud Per Session'))
		String expectedText = "Bill $billingCycle\n$dateBillingCycle"
		WebUI.verifyEqual(descriptionProduct, expectedText)
		return this
	}

	public PaymentStatusPage verifyDescriptionKatalonVTP(String billingCycle, String dateBillingCycle) {
		def descriptionProduct = WebUI.getText(productDescription('Visual Testing Professional'))
		String expectedText = "Bill $billingCycle\n$dateBillingCycle"
		WebUI.verifyEqual(descriptionProduct, expectedText)
		return this
	}

	//Payment Status
	public PaymentStatusPage verifyPaymentStatusKatalonPlatform(String expectedText) {
		def statusProduct = WebUI.getText(productPaymentStatus('Katalon Platform'))
		println "$statusProduct"
		WebUI.verifyEqual(statusProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyPaymentStatusKSE(String expectedText) {
		def statusProduct = WebUI.getText(productPaymentStatus('Katalon Studio Enterprise (per-User)'))
		WebUI.verifyEqual(statusProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyPaymentStatusKRE(String expectedText) {
		def statusProduct = WebUI.getText(productPaymentStatus('Katalon Runtime Engine (Floating)'))
		WebUI.verifyEqual(statusProduct,expectedText)
		return this
	}

	public PaymentStatusPage verifyPaymentStatusTC(String expectedText) {
		def statusProduct = WebUI.getText(productPaymentStatus('Katalon TestCloud Per Session'))
		WebUI.verifyEqual(statusProduct, expectedText)
		return this
	}

	public PaymentStatusPage verifyPaymentStatusKatalonVPT(String expectedText) {
		def statusProduct = WebUI.getText(productPaymentStatus('Visual Testing Professional'))
		WebUI.verifyEqual(statusProduct, expectedText)
		return this
	}
	
	public PaymentStatusPage clickBackSubscriptionManagementButton() {
		WebUI.click(xpath("//a[text()='Back to Subscription Management']"))
		return this
	}
}