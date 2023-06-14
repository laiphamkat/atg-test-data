import com.kms.katalon.core.util.KeywordUtil

'''
	Step1 : Access to org > click setting icon > select autonomous
	Step2 : Click add AUT project 
	Step3 : input whitelist domain with invalid format
	Expected result: error message display 
	Step4 : input valid whitelist domain + name + description 
	Expected result: AUT project with corresponding name + description is added.

note: check CDN micro front end app is correct or not
'''


KeywordUtil.markFailed('This test case should be executed manually')