# 🏨 Hotel Reservation System

## 📌 Project Description

The Hotel Reservation System is a console-based Java application developed as part of the CodeAlpha Java Programming Internship.

The system allows users to search for available hotel rooms, make reservations, cancel bookings, view booking details, and simulate payments.

The project demonstrates Object-Oriented Programming (OOP), ArrayList, File IO, and menu-driven programming in Java.

## ✨ Features

 🔍 Search available hotel rooms
 🛏️ Room categories

   Standard
   Deluxe
   Suite
 📋 View all rooms and their availability
 📝 Book a hotel room
 ❌ Cancel a reservation
 📄 View booking details
 💳 Simulate payment
 💾 Save booking information to a file
 🔄 Load previous booking information when the program starts
 🆔 Automatically generate booking IDs

## 🛠️ Technologies Used

 Java
 Object-Oriented Programming (OOP)
 ArrayList
 File Handling  File IO
 Scanner
 Exception Handling

## 🏗️ Main Classes

### `Room`

Stores hotel room information

 Room number
 Room type
 Price
 Availability status

### `Booking`

Stores reservation information

 Booking ID
 Customer name
 Room number
 Room type
 Amount paid

### `HotelReservationSystem`

Contains the main application logic, including

 Room creation
 Room searching
 Booking
 Cancellation
 Payment simulation
 Booking details
 File storage

## 📋 Room Categories

 Room Type   Price 
 ---------  ----- 
 Standard   ₹1,500 
 Deluxe     ₹2,500 
 Suite      ₹4,000 

## 🚀 How to Run

### 1. Compile the program

Open a terminal in the project folder and run

```bash
javac HotelReservationSystem.java
```

### 2. Run the program

```bash
java HotelReservationSystem
```

## 🖥️ Application Menu

```text
=================================
     HOTEL RESERVATION SYSTEM
=================================
1. Search Available Rooms
2. Book Room
3. Cancel Booking
4. View Booking Details
5. View All Rooms
6. Exit
=================================
```

## 💳 Payment Simulation

When booking a room, the system displays the room price and asks the user to enter the payment amount.

If the entered amount is less than the room price, the payment fails.

If the payment is sufficient, the reservation is confirmed.

## 💾 File Storage

The application stores booking information in

```text
bookings.txt
```

The file is automatically created after a successful booking.

Existing booking information is loaded when the application starts.

## 🔄 Booking Flow

```text
Search Room
     ↓
Select Room
     ↓
Enter Customer Details
     ↓
Confirm Booking
     ↓
Payment
     ↓
Booking Successful
     ↓
Booking Saved
```

## ❌ Cancellation Flow

```text
Enter Booking ID
        ↓
Find Booking
        ↓
Cancel Reservation
        ↓
Room Becomes Available
        ↓
Booking Removed
```

## 📸 Application Output

Add screenshots of the running application here.

Example

![Hotel Reservation System](Screenshot.png)

## 📁 Project Structure

```text
CodeAlpha_HotelReservationSystem
│
├── HotelReservationSystem.java
├── README.md
├── Screenshot.png
└── bookings.txt
```

 `bookings.txt` is generated automatically when a booking is made.

## 🎯 Internship Task

CodeAlpha Java Programming Internship – Task 4 Hotel Reservation System

The project implements room searching, room categorization, reservations, cancellation, payment simulation, booking details, OOP, and file-based data storage as required by the assigned task.

## 👩‍💻 Author

Vakada Poojitha

Java Programming Intern – CodeAlpha