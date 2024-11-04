package carsharing.user;

import carsharing.business.CarService;
import carsharing.domain.Company;

public class CompanyMenu extends Menu {
    public CompanyMenu(CarService carService, Company company) {
        super("'%s' company:".formatted(company.getName()));
        menuEntries.add(new MenuEntry(1, "Car list", () -> {carService.showCompanyCars(company);} ));
        menuEntries.add(new MenuEntry(2, "Create a car", () -> {carService.createCompanyCar(company);}));
        menuEntries.add(new MenuEntry(0, "Back", this::exit));
    }
}
