package katalon.my.paymentmethod
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import katalon.fw.lib.BasePage



public class PaymentMethodPage extends BasePage<PaymentMethodPage>{

	public PaymentMethodPage verifyPaymentMethodElementsPresent() {
		WebUI.verifyElementPresent(text("Payment Method"), 5)
		WebUI.verifyElementPresent(text("Card Number"), 5)
		WebUI.verifyElementPresent(text("Expiration"), 5)
		WebUI.verifyElementPresent(text("CVC"), 5)
		WebUI.verifyElementPresent(text("Card Holder"), 5)
		WebUI.verifyElementPresent(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]/p[text()='Payment Method']/following-sibling::div//button[text()='Save']"), 5)
		WebUI.verifyElementPresent(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]/p[text()='Payment Method']/following-sibling::div//button[text()='Cancel']"), 5)
		WebUI.verifyElementClickable(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]/p[text()='Payment Method']/following-sibling::div//button[text()='Cancel']"))
		return this
	}

	public PaymentMethodPage verifyBillingInfoElementsPresent(){
		WebUI.verifyElementPresent(text("Billing Information"), 5)
		WebUI.verifyElementPresent(text("CC Email"), 5)
		WebUI.verifyElementPresent(text("Address Line 1*"), 5)
		WebUI.verifyElementPresent(text("Address Line 2"), 5)
		WebUI.verifyElementPresent(text("City*"), 5)
		WebUI.verifyElementPresent(text("State*"), 5)
		WebUI.verifyElementPresent(text("Country*"), 5)
		WebUI.verifyElementPresent(text("Postal Code*"), 5)
		WebUI.verifyElementPresent(text("VAT/GSC ID"), 5)
		WebUI.verifyElementPresent(text("Full Business Name*"), 5)
		WebUI.verifyElementPresent(btn("Save billing information"), 5)
		WebUI.verifyElementClickable(btn("Save billing information"))
		WebUI.verifyElementPresent(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]//p[text()='Billing Information']/following::div//button[text()='Cancel']"), 5)
		WebUI.verifyElementClickable(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]//p[text()='Billing Information']/following::div//button[text()='Cancel']"))

		return this
	}

	public PaymentMethodPage inputCardNumber(String value) {
		WebUI.sendKeys(xpath("//*[text()='Card Number']/following::input"), value)
		return this
	}

	public PaymentMethodPage inputExpiration(String value) {
		WebUI.sendKeys(xpath("//*[text()='Expiration']/following::input"), value)
		return this
	}

	public PaymentMethodPage inputCVC(String value) {
		WebUI.sendKeys(xpath("//*[text()='CVC']/following::input"), value)
		return this
	}

	public PaymentMethodPage inputCardHolder(String value) {
		WebUI.sendKeys(xpath("//*[text()='Card Holder']/following::input"), value)
		return this
	}

	public PaymentMethodPage clickSaveBtn() {
		WebUI.click(btn("Save"))
		return this
	}

	public PaymentMethodPage clickDeletePaymentMethod() {
		WebUI.click(btn("Delete"))
		return this
	}

	public PaymentMethodPage clickUpdatePaymentMethod() {
		WebUI.click(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]/p[text()='Payment Method']/following-sibling::div/button[text()='Update']"))
		return this
	}

	public PaymentMethodPage clickUpdateBillingInfoBtn() {
		WebUI.click(xpath("//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]//p[text()='Billing Information']/following-sibling::button[text()='Update']"))
		return this
	}

	public PaymentMethodPage verifyAppearElementAfterClickUpdateBillingInfor() {
		WebUI.verifyElementPresent(btn("Save billing information"), 5)
		WebUI.verifyElementPresent(btn("Cancel"), 5)
	}

	public PaymentMethodPage clickCancelBillingInformation() {
		WebUI.click(btn("Cancel"))
		return this
	}

	public PaymentMethodPage verifyUpdatePayMentMethodBtnClickable() {
		String btnXpath = "//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]/p[text()='Payment Method']/following-sibling::div/button[text()='Update']"
		WebUI.verifyElementPresent(xpath(btnXpath), 5)
		WebUI.verifyElementClickable(xpath(btnXpath))
		return this
	}

	public PaymentMethodPage verifyDeletePayMentMethodBtnClickable() {
		WebUI.verifyElementPresent(btn("Delete"), 5)
		WebUI.verifyElementClickable(btn("Delete"))
		return this
	}

	public PaymentMethodPage verifyUpdateBillingInfoBtnClickable() {
		String btnXpath = "//div[contains(@class,'MuiBox-root')]/div[contains(@class,'MuiPaper-root')]//p[text()='Billing Information']/following-sibling::button[text()='Update']"
		WebUI.verifyElementPresent(xpath(btnXpath), 5)
		WebUI.verifyElementClickable(xpath(btnXpath))
		return this
	}
}
