package pointOfSale;

import java.math.BigDecimal;
import java.util.Optional;

public interface Inventory
{
	Optional<BigDecimal> getPriceFromCode(String validBarcode);
}