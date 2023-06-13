package katalon.testops.configurations

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class ConfigurationPage extends BasePage<ConfigurationPage> {

	public ConfigurationPage selectIntegrationTab() {
		WebUI.click(text("Integrations"))
		return this
	}
	
	public ConfigurationPage selectCustomFieldsTab() {
		WebUI.click(text("Custom Fields"))
		return this
	}

	public ConfigurationPage verifyNoOptionLabelIsShown() {
		WebUI.verifyElementVisible(xpath("//div[text()='No options']"))
		return this
	}
}
