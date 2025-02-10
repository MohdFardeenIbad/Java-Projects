import java.util.*;

public class ChatApplication {

    // Message class to represent a chat message
    static class Message {
        String username;
        String message;
        String timestamp;

        public Message(String username, String message, String timestamp) {
            this.username = username;
            this.message = message;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] " + username + ": " + message;
        }
    }

    // ChatRoom class to manage users and messages in the chat room
    static class ChatRoom {
        private Set<String> users = new HashSet<>();
        private List<Message> messages = new ArrayList<>();
        private String roomName;

        public ChatRoom(String roomName) {
            this.roomName = roomName;
        }

        public void addUser(String username) {
            users.add(username);
            System.out.println(username + " has joined the chat room.");
        }

        public void removeUser(String username) {
            users.remove(username);
            System.out.println(username + " has left the chat room.");
        }

        public void sendMessage(String username, String message) {
            String timestamp = new Date().toString();
            messages.add(new Message(username, message, timestamp));
        }

        public void viewMessages() {
            if (messages.isEmpty()) {
                System.out.println("No messages yet.");
            } else {
                for (Message msg : messages) {
                    System.out.println(msg);
                }
            }
        }

        public Set<String> getUsers() {
            return users;
        }

        public String getRoomName() {
            return roomName;
        }
    }

    // Main method to interact with users
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatRoom chatRoom = new ChatRoom("Java Chat Room");

        String username = "";

        while (true) {
            System.out.println("\nWelcome to " + chatRoom.getRoomName());
            System.out.println("1. Join Chat Room");
            System.out.println("2. Send Message");
            System.out.println("3. View Messages");
            System.out.println("4. Exit Chat Room");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your username: ");
                    username = scanner.nextLine();
                    chatRoom.addUser(username);
                    break;

                case 2:
                    if (username.isEmpty()) {
                        System.out.println("Please join the chat room first.");
                    } else {
                        System.out.print("Enter your message: ");
                        String message = scanner.nextLine();
                        chatRoom.sendMessage(username, message);
                    }
                    break;

                case 3:
                    chatRoom.viewMessages();
                    break;

                case 4:
                    if (username.isEmpty()) {
                        System.out.println("You need to join the chat room first to exit.");
                    } else {
                        chatRoom.removeUser(username);
                        System.out.println("You have left the chat room.");
                        return;
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
