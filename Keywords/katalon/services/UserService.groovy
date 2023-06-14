package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class UserService extends BaseService<UserService> {
	
	private UserService() {
		createUrl = "${GlobalVariable.myApi}${GlobalVariable.version}/users"
	}
	
	public UserManagementServices createNewUser(String email, String password) {
		def body = ["email": email, "password": password]
		initRequestObject()
				.setUrl(createUrl)
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}
}
