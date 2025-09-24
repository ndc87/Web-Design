package vn.edu.hcmute._9.graphql_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import vn.edu.hcmute._9.graphql_api.dto.UserInput;
import vn.edu.hcmute._9.graphql_api.model.User;
import vn.edu.hcmute._9.graphql_api.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @MutationMapping
    public User createUser(@Argument UserInput userInput) {
        User user = new User();
        user.setFullname(userInput.getFullname());
        user.setEmail(userInput.getEmail());
        user.setPassword(userInput.getPassword()); // Trong thực tế cần mã hóa password
        user.setPhone(userInput.getPhone());
        return userRepository.save(user);
    }

    @MutationMapping
    public User updateUser(@Argument Long id, @Argument UserInput userInput) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setFullname(userInput.getFullname());
        existingUser.setEmail(userInput.getEmail());
        // Thường không cập nhật password ở đây trừ khi có chức năng đổi password riêng
        existingUser.setPhone(userInput.getPhone());
        return userRepository.save(existingUser);
    }

    @MutationMapping
    public boolean deleteUser(@Argument Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}