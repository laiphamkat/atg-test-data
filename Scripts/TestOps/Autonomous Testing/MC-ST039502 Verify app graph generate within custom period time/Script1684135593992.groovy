import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This test case should be executed manually')

'''Pre-condition:
Test Case are gen 1 times
Loading component disappeared
There is no user journey generated at current
Step:
1. Click generate new user journey map
│       -> Select time period popup display
2. At Select time period popup, select custom days
3. Select time period, current day + next day have data
│       -> Generated process popup display, Click Got it! button
│       -> New App graph row display with status Generating
│       │       -> Time period Match ( current date + next day have data)
│       │       -> number test case display
│       -> Generate new user journey map button grey out'''