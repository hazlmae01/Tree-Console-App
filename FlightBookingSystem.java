import java.util.ArrayList;
import java.util.Scanner;

// Tree Node Class for Destinations
class DestinationNode {
    String destination;
    ArrayList<DestinationNode> subRoutes;

    public DestinationNode(String destination) {
        this.destination = destination;
        this.subRoutes = new ArrayList<>();
    }

    public void addSubRoute(DestinationNode node) {
        subRoutes.add(node);
    }

    public DestinationNode find(String name) {
        if (this.destination.equalsIgnoreCase(name)) {
            return this;
        }
        for (DestinationNode subRoute : subRoutes) {
            DestinationNode found = subRoute.find(name);
            if (found != null) return found;
        }
        return null;
    }

    public void printRoutes(String prefix) {
        System.out.println(prefix + destination);
        for (DestinationNode subRoute : subRoutes) {
            subRoute.printRoutes(prefix + "--");
        }
    }
}

public class FlightBookingSystem {
    private static DestinationNode root = new DestinationNode("Main Hub");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Route\n2. View All Routes\n3. Book Flight\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter parent destination: ");
                    String parentDest = scanner.nextLine();
                    System.out.print("Enter new route destination: ");
                    String newRoute = scanner.nextLine();
                    addRoute(parentDest, newRoute);
                    break;

                case 2:
                    System.out.println("\nAvailable Flight Routes:");
                    root.printRoutes("");
                    break;

                case 3:
                    System.out.print("Enter destination to book: ");
                    String bookDest = scanner.nextLine();
                    bookFlight(bookDest);
                    break;

                case 4:
                    System.out.println("Thank you for using the Flight Booking System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRoute(String parentDest, String newRoute) {
        DestinationNode parent = root.find(parentDest);
        if (parent == null) {
            System.out.println("Parent destination not found!");
        } else {
            parent.addSubRoute(new DestinationNode(newRoute));
            System.out.println("New route added successfully.");
        }
    }

    private static void bookFlight(String bookDest) {
        DestinationNode found = root.find(bookDest);
        if (found == null) {
            System.out.println("Destination not found!");
        } else {
            System.out.println("Flight booked successfully to: " + found.destination);
        }
    }
}
