package katalon.my.usermanagement

import katalon.fw.lib.BasePage

public class RemoveUserPopup extends BasePage<RemoveUserPopup> {
	
	public clickRemoveBtn() {
		click(xpath("//*[@id='form-dialog-title']/following-sibling::div//button[text()='Remove']"))
		return this
	}
}
