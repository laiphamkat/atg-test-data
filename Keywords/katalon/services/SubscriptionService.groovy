package katalon.services

import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import external.services.RecurlyService
import internal.GlobalVariable
import katalon.fw.lib.BaseService

public class SubscriptionService extends BaseService<SubscriptionService> {

	static String subscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/checkout-testops-platform"
	static String cancelSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/cancel"
	static String reactivateSubscriptionUrl = "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/reactivate"
	static def getIsPaidAccountUrl = { String accountId -> return "$GlobalVariable.myApi$GlobalVariable.version/subscriptions/account/${accountId}/is-paid-account"}

	public SubscriptionService() {
		createUrl = subscriptionUrl;
	}

	//respond array list subscription
	public SubscriptionService createSubscription(String accountId, String planId, Number quantity) {
		def body = [
			[ "organizationId": accountId.toFloat(),"planId": planId, "number": quantity]
		]
		initRequestObject()
				.setBearerAuthorizationHeader()
				.create(body)
		return this
	}

	public SubscriptionService upgradeSubscription(String accountId,String planId,Number quantity, String recurlySubscriptionUuid) {
		def quantityUpgrade = quantity + 1
		def body = [
			[ "organizationId": accountId.toFloat(),"planId": planId, "number": quantityUpgrade, "recurlySubscriptionUuid": recurlySubscriptionUuid]
		]
		initRequestObject()
				.setBearerAuthorizationHeader()
				.create(parseObjectToString(body))
		return this
	}

	public SubscriptionService cancelSubscription(Object body) {
		WebUI.delay(GlobalVariable.smallSleepTime)
		initRequestObject()
				.setBearerAuthorizationHeader()
				.update(parseObjectToString(body), cancelSubscriptionUrl)
		return this
	}

	public SubscriptionService reactivateSubscription(Object body) {
		initRequestObject()
				.setBearerAuthorizationHeader()
				.update(parseObjectToString(body), reactivateSubscriptionUrl)
		return this
	}

	public SubscriptionService verifyEqualResult(String actualResult,String expectedResult) {
		WebUI.verifyEqual(actualResult, expectedResult)
		return this
	}

	public SubscriptionService getIsPaidAccount(String accountId) {
		WebUI.delay(GlobalVariable.smallSleepTime)
		initRequestObject()
				.setBearerAuthorizationHeader()
				.get("${getIsPaidAccountUrl(accountId)}")
		return this
	}
}
