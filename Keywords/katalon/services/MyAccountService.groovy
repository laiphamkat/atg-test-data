package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class MyAccountService extends BaseService<MyAccountService> {
	static String accountUrl = GlobalVariable.myApi+"v1/accounts/"
	static String accountUserUrl = GlobalVariable.myApi+"v1/account-users/"
	public String accountId

	private MyAccountService() {
		createUrl = GlobalVariable.myApi+"v1/accounts/"
	}

	public MyAccountService createNewAccount(String accountName) {
		setUrl(accountUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad('{"name":"'+accountName+'"}')
				.sendPostRequest()
				.verifyStatusCode(200)
		this.accountId = parseResponseBodyToJsonObject().data.id
		println this.accountId
		return this
	}

	public MyAccountService getAccountId(String accountName) {
		setUrl(accountUserUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad('{"filters":[{"field":"account.name","operator":"EQ","value":"'+accountName+'"}],"sorts":[],"fetches":[]}')
				.sendGetRequest()
		if(parseResponseBodyToJsonObject().data) {
			this.accountId = parseResponseBodyToJsonObject().data[0].account.id
		}
		return this
	}

	public MyAccountService setDeleteURL(String accountId = this.accountId) {
		deleteUrl = accountUrl + accountId
		return this
	}

	public MyAccountService setUrlWithAccountId(String accountId = this.accountId) {
		setUrl(accountUrl + accountId)
		return this
	}
}
