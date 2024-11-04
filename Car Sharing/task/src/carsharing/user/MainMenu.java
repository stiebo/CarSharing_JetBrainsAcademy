package carsharing.user;

import carsharing.business.CarService;

public class MainMenu extends Menu {
    public MainMenu(CarService carService) {
        super("");
        menuEntries.add(new MenuEntry(1, "Log in as a manager", carService::managerLogin));
        menuEntries.add(new MenuEntry(0, "Exit", this::exit));
    }
}
