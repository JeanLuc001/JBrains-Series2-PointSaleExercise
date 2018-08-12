package pointOfSale;

import java.math.BigDecimal;

public interface Warehouse
{
	BigDecimal getPriceFromCode(String validBarcode);
}