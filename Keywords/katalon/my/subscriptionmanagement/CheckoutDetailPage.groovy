package katalon.my.subscriptionmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage

public class CheckoutDetailPage extends BasePage<CheckoutDetailPage> {

	def cardNumberTxt = xpath("//input[@name='cardnumber']")
	def expiredDateTxt = xpath("//input[@name='exp-date']")
	def cvcTxt = xpath("//input[@name='cvc']")
	def paymentSaveBtn = xpath("//h3[text()='Payment']/following::button[text()='Save']")
	def infoSaveBtn = xpath("//h6[text()='Billing Information']/following::button[text()='Save']")
	def paymentUpdateBtn = xpath("//h3[text()='Payment']/following::button[text()='Update']")
	def cardNumberLbl = xpath("//h3[text()='Payment']/following::h3")
	def cardExpiredLbl = xpath("//h3[text()='Payment']/following::h6")
	def addressFieldTxt = { String field -> return xpath("//h5[text()='${field}']/following::input") }
	def productSection = { String product -> return "//div[p[contains(text(),'${product}')]]" }
	def productName = { String product -> return xpath("${productSection(product)}//p[1]") }
	def productPrice = { String product -> return xpath("${productSection(product)}//p[2]") }
	def productQuality = { String product -> return xpath("${productSection(product)}//p[3]") }
	def productBillingCycle = { String product -> return xpath("${productSection(product)}//p[4]") }
	def pricingInfo = { String section -> return xpath("//div/p[text() = '${section}']/following-sibling::p") }


	public CheckoutDetailPage inputCardNumber(String cardNumber) {
		WebUI.switchToFrame(xpath("//iframe[@title='Secure card number input frame']"), 10)
		WebUI.sendKeys(cardNumberTxt, cardNumber)
		WebUI.switchToDefaultContent()
		return this
	}

	public CheckoutDetailPage inputCardExpireDate(String expiredDate) {
		WebUI.switchToFrame(xpath("//iframe[@title='Secure expiration date input frame']"), 10)
		WebUI.sendKeys(expiredDateTxt, expiredDate)
		WebUI.switchToDefaultContent()
		return this
	}

	public CheckoutDetailPage inputCVC(String cvc) {
		WebUI.switchToFrame(xpath("//iframe[@title='Secure CVC input frame']"), 10)
		WebUI.sendKeys(cvcTxt, cvc)
		WebUI.switchToDefaultContent()
		return this
	}

	public CheckoutDetailPage inputCardHolder(String cardPHolder) {
		WebUI.sendKeys(xpath("//input[@placeholder='Name on card']"), cardPHolder)
		return this
	}

	public CheckoutDetailPage clickSavePaymentMethod() {
		WebUI.click(paymentSaveBtn)
		return this
	}

	public CheckoutDetailPage clickSaveBillingInfo() {
		WebUI.click(infoSaveBtn)
		return this
	}

	public CheckoutDetailPage verifySavedCardNumber(String savedCardNumber) {
		WebUI.verifyElementText(cardNumberLbl, savedCardNumber)
		return this
	}

	public CheckoutDetailPage verifySavedCardExpiredDate(String savedExpiredDate) {
		WebUI.verifyElementText(cardExpiredLbl, savedExpiredDate)
		return this
	}

	public CheckoutDetailPage verifyCardLogoExist() {
		WebUI.verifyElementVisible(xpath("//img[@alt='current-logo']"))
		return this
	}

	public CheckoutDetailPage inputCCEmail(String value) {
		WebUI.sendKeys(addressFieldTxt('CC Email'), value)
		return this
	}

	public CheckoutDetailPage inputAddressLine1(String value) {
		WebUI.sendKeys(addressFieldTxt('Address Line 1*'), value)
		return this
	}

	public CheckoutDetailPage inputAddressLine2(String value) {
		WebUI.sendKeys(addressFieldTxt('Address Line 2'), value)
		return this
	}

	public CheckoutDetailPage inputCity(String value) {
		WebUI.sendKeys(addressFieldTxt('City*'), value)
		return this
	}

	public CheckoutDetailPage inputState(String value) {
		WebUI.sendKeys(addressFieldTxt('State*'), value)
		return this
	}

	public CheckoutDetailPage inputPostalCode(String value) {
		WebUI.sendKeys(addressFieldTxt('Postal Code*'), value)
		return this
	}

	public CheckoutDetailPage inputVAT(String value) {
		WebUI.sendKeys(addressFieldTxt('VAT/GSC ID'), value)
		return this
	}

	public CheckoutDetailPage inputBusinessName(String value) {
		WebUI.sendKeys(addressFieldTxt('Full Business Name*'), value)
		return this
	}

	public CheckoutDetailPage inputCountry(String value) {
		WebUI.click(addressFieldTxt('Country*'))
		WebUI.click(xpath("//ul/li[text()='${value}']"))
		return this
	}

	public CheckoutDetailPage verifyPlatformName(String value) {
		def productName = productName('Katalon Platform')
		WebUI.verifyElementVisible(productName)
		WebUI.verifyElementText(productName, value)
		return this
	}

	public CheckoutDetailPage verifyPlatformPrice(String value) {
		def productPrice = productPrice('Katalon Platform')
		WebUI.verifyElementVisible(productPrice)
		WebUI.verifyElementText(productPrice, value)
		return this
	}

	public CheckoutDetailPage verifyPlatformQuality(String value) {
		def productQuality = productQuality('Katalon Platform')
		WebUI.verifyElementVisible(productQuality)
		WebUI.verifyElementText(productQuality, value)
		return this
	}

	public CheckoutDetailPage verifyPlatformBillingCycle(String value) {
		def productBillingCycle = productBillingCycle('Katalon Platform')
		WebUI.verifyElementVisible(productBillingCycle)
		WebUI.verifyElementText(productBillingCycle, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonStudioEnterpriseName(String value) {
		def productName = productName('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(productName)
		WebUI.verifyElementText(productName, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonStudioEnterprisePrice(String value) {
		def productPrice = productPrice('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(productPrice)
		WebUI.verifyElementText(productPrice, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonStudioEnterpriseQuality(String value) {
		def productQuality = productQuality('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(productQuality)
		WebUI.verifyElementText(productQuality, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonStudioEnterpriseBillingCycle(String value) {
		def productBillingCycle = productBillingCycle('Katalon Studio Enterprise')
		WebUI.verifyElementVisible(productBillingCycle)
		WebUI.verifyElementText(productBillingCycle, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonRuntimeEngineName(String value) {
		def productName = productName('Katalon Runtime Engine')
		WebUI.verifyElementVisible(productName)
		WebUI.verifyElementText(productName, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonRuntimeEnginePrice(String value) {
		def productPrice = productPrice('Katalon Runtime Engine')
		WebUI.verifyElementVisible(productPrice)
		WebUI.verifyElementText(productPrice, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonRuntimeEngineQuality(String value) {
		def productQuality = productQuality('Katalon Runtime Engine')
		WebUI.verifyElementVisible(productQuality)
		WebUI.verifyElementText(productQuality, value)
		return this
	}

	public CheckoutDetailPage verifyKatalonRuntimeEngineBillingCycle(String value) {
		def productBillingCycle = productBillingCycle('Katalon Runtime Engine')
		WebUI.verifyElementVisible(productBillingCycle)
		WebUI.verifyElementText(productBillingCycle, value)
		return this
	}

	public CheckoutDetailPage verifyTestCloudName(String value) {
		def productName = productName('Test Cloud')
		WebUI.verifyElementVisible(productName)
		WebUI.verifyElementText(productName, value)
		return this
	}

	public CheckoutDetailPage verifyTestCloudPrice(String value) {
		def productPrice = productPrice('Test Cloud')
		WebUI.verifyElementVisible(productPrice)
		WebUI.verifyElementText(productPrice, value)
		return this
	}

	public CheckoutDetailPage verifyTestCloudQuality(String value) {
		def productQuality = productQuality('Test Cloud')
		WebUI.verifyElementVisible(productQuality)
		WebUI.verifyElementText(productQuality, value)
		return this
	}

	public CheckoutDetailPage verifyTestCloudBillingCycle(String value) {
		def productBillingCycle = productBillingCycle('Test Cloud')
		WebUI.verifyElementVisible(productBillingCycle)
		WebUI.verifyElementText(productBillingCycle, value)
		return this
	}

	public CheckoutDetailPage verifyVisualTestingProfessionalName(String value) {
		def productName = productName('Visual Testing Professional')
		WebUI.verifyElementVisible(productName)
		WebUI.verifyElementText(productName, value)
		return this
	}

	public CheckoutDetailPage verifyVisualTestingProfessionalPrice(String value) {
		def productPrice = productPrice('Visual Testing Professional')
		WebUI.verifyElementVisible(productPrice)
		WebUI.verifyElementText(productPrice, value)
		return this
	}

	public CheckoutDetailPage verifyVisualTestingProfessionalQuality(String value) {
		def productQuality = productQuality('Visual Testing Professional')
		WebUI.verifyElementVisible(productQuality)
		WebUI.verifyElementText(productQuality, value)
		return this
	}

	public CheckoutDetailPage verifyVisualTestingProfessionalBillingCycle(String value) {
		def productBillingCycle = productBillingCycle('Visual Testing Professional')
		WebUI.verifyElementVisible(productBillingCycle)
		WebUI.verifyElementText(productBillingCycle, value)
		return this
	}

	public CheckoutDetailPage verifyTax(String value) {
		def tax = pricingInfo('Tax')
		WebUI.verifyElementVisible(tax)
		WebUI.verifyElementText(tax, value)
		return this
	}

	public CheckoutDetailPage verifyDiscount(String value) {
		def discount = pricingInfo('Discount')
		WebUI.verifyElementVisible(discount)
		WebUI.verifyElementText(discount, value)
		return this
	}

	public CheckoutDetailPage verifyTotal(String value) {
		def total = pricingInfo('Total')
		WebUI.verifyElementVisible(total)
		WebUI.verifyElementText(total, value)
		return this
	}

	public CheckoutDetailPage verifyNetPrice(String value) {
		def net = pricingInfo('Total (Net price)')
		WebUI.verifyElementVisible(net)
		WebUI.verifyElementText(net, value)
		return this
	}

	public CheckoutDetailPage clickCheckoutButton() {
		WebUI.click(btn('Checkout'))
		return this
	}
}
