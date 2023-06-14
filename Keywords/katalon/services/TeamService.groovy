package katalon.services

import internal.GlobalVariable
import katalon.fw.lib.BaseService
import katalon.model.BodySearch
import katalon.model.ConditionSearch

public class TeamService extends BaseService<TeamService> {
	static String addUserToTeamUrl = "$GlobalVariable.adminApiUrl$GlobalVariable.version/users/add"
	static String changeUserPermissionUrl = "$GlobalVariable.adminApiUrl$GlobalVariable.version/permission/team/user"

	private TeamService() {
		createUrl = GlobalVariable.myApi + 'v1/teams'
	}

	public TeamService setSearchUrl (String uri = '') {
		String endpoint = GlobalVariable.adminApiUrl + GlobalVariable.version +  "/search"
		request.setRestUrl(endpoint)
		return this
	}

	public String teamSearchBodyByOrg (String orgId =  GlobalVariable.defaultOrg) {
		ConditionSearch conditions = new ConditionSearch("Organization.id", "=", orgId)
		BodySearch body = new BodySearch("Team", [conditions])
		return parseObjectToString(body)
	}

	public TeamService addUserToTeam(long teamId, long userId) {
		String url = addUserToTeamUrl + "?teamId=$teamId&newUserIds=$userId"
		initRequestObject()
				.setUrl(url)
				.setBasicTokenAuthorizationHeader(GlobalVariable.basicToken)
				.setJsonContentTypeHeader()
				.sendPostRequest()
		return this
	}

	public TeamService changeUserPermission(long userId, long teamId, String role) {
		def body = ["userId":userId,"teamId":teamId,"role":role]
		setUrl(changeUserPermissionUrl)
				.setBearerAuthorizationHeader()
				.setJsonContentTypeHeader()
				.setPayLoad(parseObjectToString(body))
				.sendPostRequest()
		return this
	}
}
