package katalon.testops.testexecution

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class TestCloudLicenseBanner extends BasePage<TestCloudLicenseBanner> {

	public TestCloudLicenseBanner verifyBannerPresent() {
		WebUI.verifyElementPresent(css('.warning-alert-banner'),0)
		return this
	}

	public TestCloudLicenseBanner verifyBannerNotPresent() {
		WebUI.refresh()
		WebUI.verifyElementNotPresent(css('.warning-alert-banner'),0)
		return this
	}
	
	public TestCloudLicenseBanner clickLearnMoreTCLink() {
		WebUI.click(xpath("//a[normalize-space() = 'Learn more about TestCloud']"))
		return this
	}

	public TestCloudLicenseBanner clickContactAnExpertLink() {
		WebUI.click(xpath("//a[normalize-space() = 'Contact an expert.']"))
		return this
	}

	public TestCloudLicenseBanner clickTestCloudLicenseLink() {
		WebUI.click(xpath("//a[normalize-space() = 'Buy TestCloud License']"))
		return this
	}

	public TestCloudLicenseBanner clickContactSaleLink() {
		WebUI.click(xpath("//a[normalize-space() ='Contact Sales.']"))
		return this
	}

	public TestCloudLicenseBanner clickCloseBanner() {
		WebUI.click(xpath("//*[@data-testid = 'ClearIcon']"))
		return this
	}
}
