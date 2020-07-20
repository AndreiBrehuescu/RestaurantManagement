package Run;

import Presentation.Controllers.RestaurantController;

public class MainClass {
	public static String serFile ;
    public static void main(String[] args) {
    	serFile = args[0];
        new RestaurantController(RestaurantController.rFrame);
    }
}
