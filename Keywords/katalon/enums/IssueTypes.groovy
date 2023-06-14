package katalon.enums

public enum IssueTypes {
	BUG(10007),
	TASK(10005),
	STORY(10004),
	EPIC(10000)

	Number value

	IssueTypes(Number value) {
		this.value = value
	}


	public Number getValue() {
		return value;
	}
}