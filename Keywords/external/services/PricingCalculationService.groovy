package external.services

import java.text.DecimalFormat

import com.recurly.v3.resources.Subscription

import katalon.enums.Subscriptions
import katalon.fw.lib.BaseService
import katalon.fw.lib.Page

public class PricingCalculationService extends BaseService<PricingCalculationService> {

	public static final Map<String,Number> productMap = new HashMap();
	private static DecimalFormat formatter = new DecimalFormat("#,###.##")

	PricingCalculationService(){
		productMap.put("kse", 0)
		productMap.put("kre", 0)
		productMap.put("testops", 0)
		productMap.put("testcloud", 0)
		productMap.put("visual", 0)
	}

	public static String getPreviewPrice(String accountId, Subscriptions sub, Number quantity){
		def listedPrice = new BigDecimal(getListedPrice(sub).replaceAll(",", ""))

		BigDecimal previewPrice = listedPrice * quantity
		Subscription subscription = Page.nav(RecurlyService).getAccountSubscriptions(accountId).find {it.getPlan().getCode() == sub.getCode()}
		if (subscription) {
			Page.nav(RecurlyService).createPreviewSubscriptionChange(subscription.getUuid(), sub, subscription.getQuantity()+quantity)
			previewPrice = Page.nav(RecurlyService).parseResponseBodyToJsonObject().invoice_collection.charge_invoice.subtotal
		}

		this.productMap.put(sub.getCode().replaceFirst(/_.*$/, ""),previewPrice)
		return formatter.format(previewPrice)
	}

	public static getNetPrice() {
		def total = productMap.values().sum()
		return formatter.format(total)
	}

	public static getDiscountAmount(Number discountPercentage = 0) {
		BigDecimal discountAmount = new BigDecimal(getNetPrice().replaceAll(",", "")) * discountPercentage
		if(discountAmount > 1500) {
			discountAmount = 1500
		}
		return formatter.format(discountAmount);
	}

	public static getTotalPrice(){
		def total = new BigDecimal(getNetPrice().replaceAll(",", "")) - new BigDecimal(getDiscountAmount().replaceAll(",", ""))
		return formatter.format(total);
	}

	public static getListedPrice(Subscriptions sub) {
		return formatter.format(Page.nav(RecurlyService).getPlanPrice(sub))
	}
}