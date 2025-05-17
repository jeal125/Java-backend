package conversormoneda.ui;

import conversormoneda.model.*;
import conversormoneda.service.CurrencyConverterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverterMenu {
    private final List<Currency> availableCurrencies;
    private final CurrencyConverterService converterService;
    private final Scanner scanner;

    public CurrencyConverterMenu(CurrencyConverterService converterService) {
        this.converterService = converterService;
        this.scanner = new Scanner(System.in);
        this.availableCurrencies = initializeCurrencies();
    }

    private List<Currency> initializeCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new USD());
        currencies.add(new ARS());
        currencies.add(new BOB());
        currencies.add(new BRL());
        currencies.add(new CLP());
        currencies.add(new COP());
        return currencies;
    }

    public void start() {
        while (true) {
            showMenu();
            int option = getOption();

            if (option == availableCurrencies.size() + 1) {
                System.out.println("¡Gracias por usar el conversor de monedas!");
                break;
            }

            if (option >= 1 && option <= availableCurrencies.size()) {
                performConversion(availableCurrencies.get(option - 1));
            } else {
                System.out.println("Opción inválida. Por favor, intente nuevamente.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== Conversor de Monedas ===");
        for (int i = 0; i < availableCurrencies.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableCurrencies.get(i));
        }
        System.out.println((availableCurrencies.size() + 1) + ". Salir");
        System.out.print("Seleccione la moneda base: ");
    }

    private int getOption() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void performConversion(Currency baseCurrency) {
        System.out.print("Ingrese el monto a convertir: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido");
            return;
        }

        System.out.println("\nSeleccione la moneda destino:");
        List<Currency> targetCurrencies = new ArrayList<>();
        for (Currency c : availableCurrencies) {
            if (!c.getCode().equals(baseCurrency.getCode())) {
                targetCurrencies.add(c);
            }
        }

        for (int i = 0; i < targetCurrencies.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, targetCurrencies.get(i));
        }

        int targetOption = getOption();
        if (targetOption < 1 || targetOption > targetCurrencies.size()) {
            System.out.println("Opción inválida");
            return;
        }

        Currency targetCurrency = targetCurrencies.get(targetOption - 1);

        try {
            double result = converterService.convert(baseCurrency, targetCurrency, amount);
            System.out.printf("%.2f %s = %.2f %s%n",
                    amount, baseCurrency.getCode(), result, targetCurrency.getCode());
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
