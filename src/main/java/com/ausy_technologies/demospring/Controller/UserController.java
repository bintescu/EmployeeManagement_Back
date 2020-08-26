package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/addRole")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        Role roleAdded = this.userService.saveRole(role);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response ","saveRole");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(roleAdded);
    }




    @PostMapping("/addUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User userAdded = this.userService.saveUser(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","saveUser");
        return ResponseEntity.status(HttpStatus.ACCEPTED).headers(httpHeaders).body(userAdded);
    }

    @PostMapping("/addUser2/{idRole}")
    public ResponseEntity<User> saveUser2(@RequestBody User user, @PathVariable int idRole)
    {
        User usedAdded = userService.saveUser2(user,idRole);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","saveUser2");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(usedAdded);

    }

    @PostMapping("/addUser3/{roleList}")
    public ResponseEntity<User> saveUser3(@RequestBody User user , @PathVariable List<Role> roleList)
    {
        User userAdded = userService.saveUser3(user,roleList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","saveUser3");
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(userAdded);
    }

    @GetMapping("/findRoleBy/{id}")
    public ResponseEntity<Role> findRoleById(@PathVariable int id)
    {
        Role roleSearched = this.userService.findRoleById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findRoleById");
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(roleSearched);
    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>> findAllRoles()
    {
        List<Role> allRoles =  userService.findAllRoles();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findAllRoles");
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(allRoles);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers()
    {
        List<User> allUsers = this.userService.findAllUsers();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findAllUsers");
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(allUsers);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);

    }

    @PostMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestParam int id, @RequestBody User user){
        User updatedUser = userService.updateUser(id,user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateUser");
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedUser);
    }

    @PostMapping("/updateUser2/{roleList}")
    public ResponseEntity<User> updateUser2(@RequestParam int id , @PathVariable List<Role> roleList){
        User updatedUser = userService.updateUser2(id,roleList);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateUser");
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedUser);
    }

    @PostMapping("/updateRole/{name}")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestParam int id){
        Role updatedRole =  userService.updateRole(id,name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateRole");
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedRole);
    }

    @PostMapping("deleteRole/{id}")
    public void deleteRolebyId(@PathVariable int id){
        userService.deleteRolebyId(id);
    }
}
