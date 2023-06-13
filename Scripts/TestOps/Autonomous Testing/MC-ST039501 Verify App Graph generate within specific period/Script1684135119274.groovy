import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed('This test case should be executed manually')

'''Pre-condition:
Test Case are gen 1 times
Loading component disappeared

Step:
1. At first time, Observes userjourney status
│       -> Status: Finished
2. Click generate New user journey Map
│       -> Select time period popup display
3. At Select time period popup, select 7 days -> default option
│       -> Generated process popup display, Click Got it! button
│       -> New App graph row display with status Generating
│       │       -> Time period Match ( current date + 7 days)
│       │       -> number test case display
│       -> Generate new user journey map button grey out'''


