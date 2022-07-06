package dev.megashopper.controllers;

import dev.megashopper.common.dtos.*;
import dev.megashopper.common.utils.exceptions.InvalidRequestException;
import dev.megashopper.common.service.UserResponse;
import dev.megashopper.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

// Rest controller combines @Controller and @ResponseBody(changes return value to HTTP response)
//@RestController
//@RequestMapping("/users")
public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//
/* TODO: This code will not work until it has been implemented into UserRepository
*   */
//    @GetMapping(produces = "application/json")
//    public <UserResponse> List<UserResponse> getAllUsers() {
//        return (List<UserResponse>) userService.fetchAllUsers();
//    }

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // TODO: Need Help, I don't think it should return 'null'
//    @GetMapping(produces = "application/json")
//    public List<UserResponsePayload> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token) {
//        return userService.fetchAllUsers();
//    }

    @GetMapping("/search")
    public List<UserResponsePayload> findBy(@RequestParam Map<String, String> params) {
        return userService.search(params);
    }
    @GetMapping("/availability")
    public ResponseEntity<Void> checkAvailability(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        if (username != null) {
            return userService.isUsernameAvailability(new UsernameRequest(username))
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (email != null ) {
            return userService.isEmailAvailability(new EmailRequest(email))
                    ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        throw new InvalidRequestException("No email or username provided");
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResourceCreationResponse postNewUser(@RequestBody UserRequestPayload newUserInfo) {
        return userService.createUser(newUserInfo);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(consumes = "application/json")
    public void updateUserInfo(@RequestBody UserRequestPayload updatedUserInfo) {
        userService.updateUser(updatedUserInfo);
    }



}
