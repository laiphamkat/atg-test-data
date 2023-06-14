package katalon.my.productutilization

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage


public class KatalonStudioUtilizationPage extends BasePage<KatalonStudioUtilizationPage> {

	public KatalonStudioUtilizationPage clickKatalonStudioTab() {
		WebUI.click(text("Katalon Studio"))
		return this
	}
	
	public KatalonStudioUtilizationPage verifyUIKatalonStudioUtilizationPage() {
		WebUI.verifyElementPresent(text("License Utilization"), 5)
		WebUI.verifyElementPresent(text("License Usage"), 5)
		WebUI.verifyElementPresent(btn("Export"), 5)
		
		WebUI.verifyElementPresent(id("user-filter"), 5)
		WebUI.verifyElementPresent(id("machine-filter"), 5)
		WebUI.verifyElementPresent(id("license-filter"), 5)
		
		//field datetime picker
		WebUI.verifyElementPresent(xpath("(//*[text()='License']//following::span)[3]"), 5)
		WebUI.verifyElementClickable(xpath("(//*[text()='License']//following::span)[3]"))
		
		WebUI.verifyElementPresent(link("Linear"), 5)
		WebUI.verifyElementPresent(link("Stacked"), 5)
		
		WebUI.verifyElementPresent(text("KRE Floating"), 5)
		WebUI.verifyElementPresent(text("KRE Node-locked"), 5)
		WebUI.verifyElementPresent(text("KSE Floating"), 5)
		WebUI.verifyElementPresent(text("KSE Node-locked"), 5)
		WebUI.verifyElementPresent(text("KSE per-User"), 5)
		WebUI.verifyElementPresent(text("Total"), 5)
		
		WebUI.verifyElementPresent(xpath("//th[text()='User']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Session Id']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Machine Id']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='License']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Started']"), 5)
		WebUI.verifyElementPresent(xpath("//th[text()='Duration']"), 5)
		return this
	}
}