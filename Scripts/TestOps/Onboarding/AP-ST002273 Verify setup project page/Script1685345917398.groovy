import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailedAndStop('This test case should be executed manually')

"Pre-condition: The user created a new TO account"

"1.Valid login into TestOps."
"2.Completed the questionaries."
"3.Verify:"
"-Show as a single page with specific URL"
"-Including 3 options to set up default project:"
"+Explore our sample project"
"+Import existing project from Git repository"
"+Create new project from scratch"
"-Disable Get Started button"
"4.Click on Import existing project from Git repository/ Explore our sample project/ Create new project from scratch"
"5.Verify:"
"-Get Started button is enabled."
"6.Click on Get Started button"
"7.Verify:"
"-Redirect them to configuration page and show Git setup section when user click on Import existing project from Git repository.(auto expand config and redirect to project dashboard)"
"-Redirect them to the project dashboard with sample data when user click on Explore our sample project(Handle case when populating fail)"
"-Redirect them to project dashboard without sample data & download Katalon Studio when user click on Create new project from scratch.(Download Katalon Studio)"
"Note:"
"-Support uncheck actions"
"-Don't show inapp banners in Setup Project page"