// File: NotificationService.java
interface Notifier {
    void send(String message);
}

public class p7_2 {
    public void triggerAlert() {
        Notifier notifier = new Notifier() {
            public void send(String message) {
                System.out.println("ALERT: " + message);
            }
        };
        notifier.send("Server down!");
    }

    public static void main(String[] args) {
        new p7_2().triggerAlert();
    }
}
