package katalon.testops.user

import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import katalon.fw.lib.BasePage

public class KatalonAPIKeyPage extends BasePage<KatalonAPIKeyPage> {
	def copyBtn = { String apiKeyName -> return xpath("//tbody//tr[.//td[. = '${apiKeyName}']]//button[@data-trackid='copy-api-key']") }
	
	KatalonAPIKeyPage clickCopy(String apiKeyName) {
		WebUI.click(copyBtn(apiKeyName))
		return this
	}	
	
	String getAPIKeyFromClipboard() {
		return (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor)
	}
}
