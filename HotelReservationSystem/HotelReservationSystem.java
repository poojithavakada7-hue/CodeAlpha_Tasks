import java.io.*;
import java.util.*;

public class HotelReservationSystem {

    // Room class
    static class Room {
        int roomNumber;
        String type;
        double price;
        boolean available;

        Room(int roomNumber, String type, double price) {
            this.roomNumber = roomNumber;
            this.type = type;
            this.price = price;
            this.available = true;
        }
    }

    // Booking class
    static class Booking {
        int bookingId;
        String customerName;
        int roomNumber;
        String roomType;
        double amount;

        Booking(int bookingId, String customerName,
                int roomNumber, String roomType, double amount) {

            this.bookingId = bookingId;
            this.customerName = customerName;
            this.roomNumber = roomNumber;
            this.roomType = roomType;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return bookingId + "," + customerName + ","
                    + roomNumber + "," + roomType + "," + amount;
        }
    }

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static int nextBookingId = 1001;

    static final String FILE_NAME = "bookings.txt";

    public static void main(String[] args) {

        createRooms();
        loadBookings();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("     HOTEL RESERVATION SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. View Booking Details");
            System.out.println("5. View All Rooms");
            System.out.println("6. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelBooking();
                    break;

                case 4:
                    viewBooking();
                    break;

                case 5:
                    viewAllRooms();
                    break;

                case 6:
                    System.out.println("\nThank you for using the Hotel Reservation System!");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    // Create hotel rooms
    static void createRooms() {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(103, "Standard", 1500));

        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(203, "Deluxe", 2500));

        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
    }

    // Search available rooms
    static void searchRooms() {

        System.out.println("\n----- SEARCH ROOMS -----");

        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");
        System.out.println("4. All Types");

        System.out.print("Choose room type: ");
        int choice = sc.nextInt();

        String type = "";

        if (choice == 1) {
            type = "Standard";
        } else if (choice == 2) {
            type = "Deluxe";
        } else if (choice == 3) {
            type = "Suite";
        }

        boolean found = false;

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            if (room.available &&
                    (choice == 4 || room.type.equals(type))) {

                System.out.println(
                        "Room: " + room.roomNumber +
                        " | Type: " + room.type +
                        " | Price: Rs." + room.price
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    // Book a room
    static void bookRoom() {

        System.out.println("\n----- BOOK ROOM -----");

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        searchRooms();

        System.out.print("\nEnter room number to book: ");
        int roomNumber = sc.nextInt();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.roomNumber == roomNumber &&
                    room.available) {

                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room is not available.");
            return;
        }

        System.out.println("\nRoom Details");
        System.out.println("Room Number : " + selectedRoom.roomNumber);
        System.out.println("Room Type   : " + selectedRoom.type);
        System.out.println("Price       : Rs." + selectedRoom.price);

        System.out.print("\nConfirm booking? (yes/no): ");
        sc.nextLine();
        String confirmation = sc.nextLine();

        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Booking cancelled.");
            return;
        }

        // Payment simulation
        System.out.println("\n----- PAYMENT -----");
        System.out.println("Amount: Rs." + selectedRoom.price);

        System.out.print("Enter payment amount: ");
        double payment = sc.nextDouble();

        if (payment < selectedRoom.price) {
            System.out.println("Payment failed. Insufficient amount.");
            return;
        }

        selectedRoom.available = false;

        Booking booking = new Booking(
                nextBookingId,
                name,
                selectedRoom.roomNumber,
                selectedRoom.type,
                selectedRoom.price
        );

        bookings.add(booking);

        saveBookings();

        System.out.println("\n=================================");
        System.out.println("       BOOKING SUCCESSFUL");
        System.out.println("=================================");
        System.out.println("Booking ID  : " + booking.bookingId);
        System.out.println("Customer    : " + booking.customerName);
        System.out.println("Room Number : " + booking.roomNumber);
        System.out.println("Room Type   : " + booking.roomType);
        System.out.println("Amount Paid : Rs." + booking.amount);
        System.out.println("=================================");

        nextBookingId++;
    }

    // Cancel booking
    static void cancelBooking() {

        System.out.println("\n----- CANCEL BOOKING -----");

        System.out.print("Enter Booking ID: ");
        int bookingId = sc.nextInt();

        Booking bookingToCancel = null;

        for (Booking booking : bookings) {

            if (booking.bookingId == bookingId) {
                bookingToCancel = booking;
                break;
            }
        }

        if (bookingToCancel == null) {
            System.out.println("Booking not found.");
            return;
        }

        for (Room room : rooms) {

            if (room.roomNumber == bookingToCancel.roomNumber) {
                room.available = true;
                break;
            }
        }

        bookings.remove(bookingToCancel);

        saveBookings();

        System.out.println("\nBooking cancelled successfully.");
        System.out.println("Refund Amount: Rs." + bookingToCancel.amount);
    }

    // View booking details
    static void viewBooking() {

        System.out.println("\n----- BOOKING DETAILS -----");

        System.out.print("Enter Booking ID: ");
        int bookingId = sc.nextInt();

        for (Booking booking : bookings) {

            if (booking.bookingId == bookingId) {

                System.out.println("\nBooking ID  : " + booking.bookingId);
                System.out.println("Customer    : " + booking.customerName);
                System.out.println("Room Number : " + booking.roomNumber);
                System.out.println("Room Type   : " + booking.roomType);
                System.out.println("Amount      : Rs." + booking.amount);

                return;
            }
        }

        System.out.println("Booking not found.");
    }

    // View all rooms
    static void viewAllRooms() {

        System.out.println("\n----- ALL ROOMS -----");

        for (Room room : rooms) {

            String status;

            if (room.available) {
                status = "Available";
            } else {
                status = "Booked";
            }

            System.out.println(
                    "Room: " + room.roomNumber +
                    " | Type: " + room.type +
                    " | Price: Rs." + room.price +
                    " | Status: " + status
            );
        }
    }

    // Save bookings to file
    static void saveBookings() {

        try {

            FileWriter writer = new FileWriter(FILE_NAME);

            for (Booking booking : bookings) {
                writer.write(booking.toString());
                writer.write("\n");
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving booking data.");
        }
    }

    // Load bookings from file
    static void loadBookings() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                String[] data = line.split(",");

                if (data.length == 5) {

                    int bookingId = Integer.parseInt(data[0]);
                    String customerName = data[1];
                    int roomNumber = Integer.parseInt(data[2]);
                    String roomType = data[3];
                    double amount = Double.parseDouble(data[4]);

                    Booking booking = new Booking(
                            bookingId,
                            customerName,
                            roomNumber,
                            roomType,
                            amount
                    );

                    bookings.add(booking);

                    for (Room room : rooms) {

                        if (room.roomNumber == roomNumber) {
                            room.available = false;
                        }
                    }

                    if (bookingId >= nextBookingId) {
                        nextBookingId = bookingId + 1;
                    }
                }
            }

            fileScanner.close();

        } catch (Exception e) {

            System.out.println("Error loading booking data.");
        }
    }
}