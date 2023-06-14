package external.services

import internal.GlobalVariable
import katalon.enums.IssueTypes
import katalon.fw.lib.BaseService
import katalon.model.EpicIssueFields
import katalon.model.IssueFields

public class JiraService extends BaseService<JiraService> {
	static String createProjectUrl = "${GlobalVariable.jiraWorkspaceUrl}rest/simplified/latest/project"
	static String deleteProjectUrl = "${GlobalVariable.jiraWorkspaceUrl}rest/api/2/project/%s?enableUndo=false"
	static String createIssueUrl = "${GlobalVariable.jiraWorkspaceUrl}rest/api/3/issue"
	static String updateIssueSummaryUrl = "${GlobalVariable.jiraWorkspaceUrl}rest/api/3/issue/%s"
	static String deleteIssueUrl = "${GlobalVariable.jiraWorkspaceUrl}rest/api/3/issue/%s?deleteSubtasks=true"

	public JiraService createProject(String key, String name) {
		def body = ["key": "$key",
			"templateKey": "com.pyxis.greenhopper.jira:gh-simplified-scrum-classic",
			"name": "$name"]
		initRequestObject().create(createProjectUrl, [["cookie", GlobalVariable.jiraCookie]], parseObjectToString(body), 200)	

		return this
	}

	public JiraService deleteProject(Number projectId) {
		initRequestObject().delete(String.format(deleteProjectUrl, projectId), [["cookie", GlobalVariable.jiraCookie]], 204)
		return this
	}

	public JiraService createIssue(Number jiraProjectId, Number issuetypeId, String summary, String epic) {
		IssueFields issueFields = (issuetypeId == IssueTypes.EPIC.getValue())
				? new EpicIssueFields(["id": jiraProjectId], ["id": issuetypeId], summary, epic)
				: new IssueFields(["id": jiraProjectId], ["id": issuetypeId], summary)
		Object body = ["fields": issueFields]
		initRequestObject().create(body, createIssueUrl, [["cookie", GlobalVariable.jiraCookie]], 201)
				
		return this
	}

	public JiraService updateIssueSummary(String issueKey, String summary) {
		IssueFields issueFields = new IssueFields()
		issueFields.summary = summary
		Object body = ["fields": issueFields]
		initRequestObject()
		.update(body, String.format(updateIssueSummaryUrl, issueKey), [["cookie", GlobalVariable.jiraCookie]], 204)

		return this
	}

	public JiraService deleteIssue(String issueKey) {
		initRequestObject().delete(String.format(deleteIssueUrl, issueKey), [["cookie", GlobalVariable.jiraCookie]], 204)
		return this
	}
	
	public JiraService createIssues() {
		List<Object> jiraIssueList
		for (IssueTypes issuetype : IssueTypes.values()) {
			jiraIssueList.add(createIssue(GlobalVariable.externalProjectId, issuetype.getValue(), issuetype.name(), issuetype.name()))
		}
		return jiraIssueList
	}
}
