package kh.com.sbilhbank.notification.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NotificationController {

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody String message) {
        // Implement your notification logic here
        // For simplicity, just returning the received message
        return "Notification sent: " + message;
    }
}