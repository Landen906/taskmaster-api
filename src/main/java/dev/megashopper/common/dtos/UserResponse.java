package dev.megashopper.common.dtos;

import dev.megashopper.common.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse extends dev.megashopper.common.service.UserResponse {

    private String customerId;
    private String firstName;
    private String lastName;
   
    private String username;


    public UserResponse(User user) {
        this.customerId = user.getCustomerId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
    }

    public void createUser(User newUser){
    }

    public UserResponse getCustomerId() {
        return null;
    }
}
