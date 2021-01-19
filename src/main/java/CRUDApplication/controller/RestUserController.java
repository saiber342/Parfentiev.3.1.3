package CRUDApplication.controller;

import CRUDApplication.models.Role;
import CRUDApplication.models.User;
import CRUDApplication.service.RoleService;
import CRUDApplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RestUserController {

    private final UserService userService;
    private final RoleService roleService;

    public RestUserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getUser() {
        userService.getUserByName("username");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> allUsers() {
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/users/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/users/update/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        System.out.println("getUser" + id);
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/users/edit/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        user.setId(id);
        userService.editUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @GetMapping("/users/roles")
    public List<Role> getRoles() {
        return roleService.getRoles();
    }
}
