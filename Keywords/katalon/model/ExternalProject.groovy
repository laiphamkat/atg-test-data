package katalon.model

import internal.GlobalVariable

public class ExternalProject {
	Number externalProjectId
	String externalProjectKey
	String name
	Number value
	String label

	public ExternalProject() {
		this.externalProjectId = GlobalVariable.externalProjectId
		this.externalProjectKey = GlobalVariable.externalProjectKey
		this.name = GlobalVariable.externalProjectKey
		this.value = GlobalVariable.externalProjectId
		this.label = GlobalVariable.externalProjectKey
	}

	public ExternalProject(Number externalProjectId, String externalProjectKey, String name, Number value,String label) {
		this.externalProjectId = externalProjectId
		this.externalProjectKey = externalProjectKey
		this.name = name
		this.value = value
		this.label = label
	}
}
