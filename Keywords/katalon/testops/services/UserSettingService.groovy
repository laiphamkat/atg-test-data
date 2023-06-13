package katalon.testops.services

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import katalon.fw.lib.BaseService

public class UserSettingService extends BaseService<UserSettingService> {

	public UserSettingService setParamForGitCredential(String type){
		List<TestObjectProperty> parameters = new ArrayList<>()
		parameters.add(new TestObjectProperty('type', ConditionType.EQUALS, type))
		setParam(parameters)
		return this
	}

	public UserSettingService verifyUnauthorizedMessage(String message) {
		WS.verifyEqual(parseResponseBodyToJsonObject().message, message)
		return this
	}

	public UserSettingService verifyGetGitCredentialSuccess(Object object) {
		String expected = groovy.json.JsonOutput.toJson(object)
		String output = JsonOutput.toJson(parseResponseBodyToJsonObject())
		WS.verifyEqual(output, expected)
		return this
	}
}
