package katalon.my.usermanagement

import katalon.fw.lib.BasePage

public class ChangeRolePopup extends BasePage<ChangeRolePopup>{

	public ChangeRolePopup selectRole(String role) {
		click(xpath("(//div[@role='dialog']//div[@role='button'])[2]"))
		click(xpath("//div[@id='menu-']//ul/li[text()='$role']"))
		return this
	}

	public ChangeRolePopup clickChangeRole() {
		click(xpath("//div[@role='dialog']//button[text()='Change Role']"))
		return this
	}
}
