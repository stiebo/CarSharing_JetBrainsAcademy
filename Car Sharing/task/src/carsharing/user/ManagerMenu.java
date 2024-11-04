package carsharing.user;

import carsharing.business.CarService;

public class ManagerMenu extends Menu {
    public ManagerMenu(CarService carService) {
        super("");
        menuEntries.add(new MenuEntry(1, "Company list", carService::listCompany));
        menuEntries.add(new MenuEntry(2, "Create a company", carService::createCompany));
        menuEntries.add(new MenuEntry(0, "Back", this::exit));
    }
}
