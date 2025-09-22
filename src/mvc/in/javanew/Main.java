package mvc.in.javanew;
import controllers.HomeController;
import core.Controller;

public class Main {
    public static void main(String[] args) {
        Controller c = new HomeController();
        c.run();
    }  
}


