import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class HotelManagementSystem {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Guest> guests = new ArrayList<>();
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Management System");

        // Create buttons for booking rooms, managing guests, and viewing room status
        JButton bookRoomButton = new JButton("Book Room");
        JButton manageGuestsButton = new JButton("Manage Guests");
        JButton roomStatusButton = new JButton("Room Status");

        // Set button bounds
        bookRoomButton.setBounds(50, 100, 200, 30);
        manageGuestsButton.setBounds(50, 150, 200, 30);
        roomStatusButton.setBounds(50, 200, 200, 30);

        // Add action listeners
        bookRoomButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bookRoom();
            }
        });

        manageGuestsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manageGuests();
            }
        });

        roomStatusButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showRoomStatus();
            }
        });

        // Add buttons to frame
        frame.add(bookRoomButton);
        frame.add(manageGuestsButton);
        frame.add(roomStatusButton);

        // Frame settings
        frame.setSize(300, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        // Initialize rooms
        for (int i = 1; i <= 10; i++) {
            rooms.add(new Room(i, "Single", true));
        }
    }

    // Book a room for a guest
    private static void bookRoom() {
        String guestName = JOptionPane.showInputDialog("Enter Guest Name:");
        String guestAge = JOptionPane.showInputDialog("Enter Guest Age:");
        Guest guest = new Guest(guestName, Integer.parseInt(guestAge));
        guests.add(guest);

        Room availableRoom = null;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRoom = room;
                break;
            }
        }

        if (availableRoom != null) {
            availableRoom.setAvailable(false);
            availableRoom.setGuest(guest);
            JOptionPane.showMessageDialog(null, "Room booked successfully for " + guestName);
        } else {
            JOptionPane.showMessageDialog(null, "No rooms available!");
        }
    }

    // Manage guest information (simple listing here)
    private static void manageGuests() {
        StringBuilder guestList = new StringBuilder("Guest List:\n");
        for (Guest guest : guests) {
            guestList.append(guest.getName()).append(", Age: ").append(guest.getAge()).append("\n");
        }
        JOptionPane.showMessageDialog(null, guestList.toString());
    }

    // Show room status
    private static void showRoomStatus() {
        StringBuilder status = new StringBuilder("Room Status:\n");
        for (Room room : rooms) {
            status.append("Room ").append(room.getRoomNumber()).append(" - ");
            if (room.isAvailable()) {
                status.append("Available\n");
            } else {
                status.append("Occupied by ").append(room.getGuest().getName()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, status.toString());
    }
}
