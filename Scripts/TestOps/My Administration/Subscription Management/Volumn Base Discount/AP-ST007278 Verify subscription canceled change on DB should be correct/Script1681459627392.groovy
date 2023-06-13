import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify that subscription canceled change on DB should be run correctly
Pre-condition:
 - The 'Subscription Management' page is opening.
 - Had subscription active in Account

Steps:
1. Click on the Turn off Auto-Renewal button on Ellipsis menu [...]
2. On DB observe the data

Expected result:
Subscription expired: change status to Inactive
+ Sync Recurly Subscription status on Commerce db: commerce > commerce_objs > recurly_susbcriptions
+ Sync Recurly Subscription status on KatOne db: katone > billing > recurly_susbcriptions
+ Sync Recurly Subscription status on KIT db: kit > katone_objs > recurly_susbcriptions
'''