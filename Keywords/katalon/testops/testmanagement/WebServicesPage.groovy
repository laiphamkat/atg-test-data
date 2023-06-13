package katalon.testops.testmanagement

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class WebServicesPage  extends BasePage<WebServicesPage> {

	public WebServicesPage clickWebServicesTab() {
		WebUI.click(title('Web Services'))
		return this
	}

	public WebServicesPage verifyWebServicesCardVisible() {
		WebUI.verifyElementVisible(xpath('//*[(@class="card-header-title")]'))
		WebUI.verifyElementVisible(text('This Week'))
		return this
	}

	public WebServicesPage verifyWebServicesTableVisible() {
		WebUI.click(text('Name'))
		WebUI.click(text('Average Duration'))
		WebUI.click(text('Max Duration'))
		WebUI.click(text('Min Duration'))
		return this
	}
}
