package katalon.common

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class LeftNavBar extends BasePage<LeftNavBar> {
	public LeftNavBar clickIntegrations() {
		WebUI.click(title('Integrations'))
	}
}
