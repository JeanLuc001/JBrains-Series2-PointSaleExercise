package pointOfSale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Checkout
{
	private final Display disp;
	private final Inventory inventory;
	private final List<BigDecimal> purchaseAsBigDecimals;
	private final List<Price> purchase;

	public Checkout(Display disp)
	{
		this(disp, null);
	}

	public Checkout(Display disp, Inventory inventory)
	{
		this.disp = disp;
		this.inventory = inventory;
		purchaseAsBigDecimals = new ArrayList<>();
		purchase = new ArrayList<>();
	}

	public void onBarcode(String code)
	{
		Optional<BigDecimal> oPriceAsBigDecimal = inventory.getProductPriceFromCodeAsBigDecimal(code);
		Optional<Price> oPrice = inventory.getProductPriceFromCode(code);
		if (!oPriceAsBigDecimal.isPresent())
		{
			disp.show("ERROR: " + code + " is an invalid bar code");
		}
		else
		{
			BigDecimal priceAsBigDecimal = oPriceAsBigDecimal.get();
			purchaseAsBigDecimals.add(priceAsBigDecimal);
//			disp.show("$ " + priceAsBigDecimal.toString());
			
			Price price = oPrice.get();
			purchase.add(price);
			disp.show(price.toString());
		}
	}

	public BigDecimal getTotal()
	{
		BigDecimal totalAsBigDecimal = BigDecimal.ZERO;
		for (BigDecimal itemPrice : purchaseAsBigDecimals)
		{
			totalAsBigDecimal = totalAsBigDecimal.add(itemPrice);
		}
		
//		Price total = new Price("0.00");
//		for (Price price : purchase)
//		{
//			total = total.add(other);
//		}
		return totalAsBigDecimal;
	}
}