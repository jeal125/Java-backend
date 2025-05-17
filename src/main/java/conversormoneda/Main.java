package conversormoneda;

import conversormoneda.service.ExchangeRateClient;
import conversormoneda.ui.CurrencyConverterMenu;

public class Main {
    public static void main(String[] args) {
        ExchangeRateClient converterService = new ExchangeRateClient();

        if (!converterService.isAvailable()) {
            System.out.println("Error: No se puede conectar al servicio de conversión de monedas.");
            return;
        }

        CurrencyConverterMenu menu = new CurrencyConverterMenu(converterService);
        menu.start();
    }
}
