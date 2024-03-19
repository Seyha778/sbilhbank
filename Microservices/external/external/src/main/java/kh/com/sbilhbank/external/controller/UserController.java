package kh.com.sbilhbank.external.controller;

import kh.com.sbilhbank.external.model.User;
import kh.com.sbilhbank.external.service.UserService;
import kh.com.sbilhbank.external.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @PostMapping("/decrypted")
    public String decryptedData(@RequestBody String decrypted) {
        try {
            String decryptedData = EncryptionUtil.decrypt(decrypted);
            return decryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during decryption";
        }
    }

    @PostMapping("/encrypt")
    public String cryptedData(@RequestBody String encrypted) {
        try {
            String ecryptedData = EncryptionUtil.encrypt(encrypted);
            return ecryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during encrypt";
        }
    }
}
