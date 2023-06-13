import com.kms.katalon.core.util.KeywordUtil

KeywordUtil.markFailed("This test should be performed by manual testing")

'''
I. Verify preview price display correctly when user expand products
Pre-condition:
- Subscription Management opening
- Org have active products: KSE Per-user, KRE floating, Testcloud and VTP

Steps:
1. Increase number of each active products
2. Click on Checkout button
3. From Checkout Detail page, click on Checkout

Expected result:
Discount = (discount price of total price -  discount price of original price)
Proprate = (datetime used license/1 year)
Preview Price include discount = ((total price - original price) - Discount) * Prorate)

1. Return Preview Price include discount
2. Verify Order Confirmation displays correctly:
- Each Product Unit Price
- Each Product Checkout price:  Unit Price * quatity * Proprate 
- Discount
- Tax (Base on Nation)
- Total Price
3. Verify each purchased product displays completely
- Price =  Product checkout price * avg discount percent 
'''