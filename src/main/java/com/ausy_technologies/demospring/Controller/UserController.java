package com.ausy_technologies.demospring.Controller;

import com.ausy_technologies.demospring.Exceptions.ErrorResponse;
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
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findRoleById");
        Role roleSearched = null;
        try {
            roleSearched = this.userService.findRoleById(id);
        }catch (ErrorResponse e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        }

        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(roleSearched);
    }

    @GetMapping("/findAllRoles")
    public ResponseEntity<List<Role>> findAllRoles()
    {
        List<Role> allRoles =  userService.findAllRoles();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findAllRoles");
        if(allRoles.size() == 0 ){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(allRoles);
    }


    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> findAllUsers()
    {
        List<User> allUsers = this.userService.findAllUsers();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","findAllUsers");
        if(allUsers.size() == 0 ){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(httpHeaders).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(allUsers);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable int id)
    {
        this.userService.deleteUserById(id);

    }

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestParam int id, @RequestBody User user){
        User updatedUser = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateUser");
        try{
            updatedUser = userService.updateUser(id,user);

        }catch (ErrorResponse errorResponse){
            errorResponse.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        }

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedUser);
    }

    @PutMapping("/updateUser2/{roleList}")
    public ResponseEntity<User> updateUser2(@RequestParam int id , @PathVariable List<Role> roleList){
        User updatedUser = null ;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateUser");
        try{
            updatedUser = userService.updateUser2(id,roleList);
        }catch (ErrorResponse e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        }

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedUser);
    }

    @PutMapping("/updateRole/{name}")
    public ResponseEntity<Role> updateRole(@PathVariable String name, @RequestParam int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","updateRole");
        Role updatedRole =  null;
        try {
            updatedRole = userService.updateRole(id,name);
        }catch (ErrorResponse e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        }

        return ResponseEntity.status(HttpStatus.RESET_CONTENT).headers(httpHeaders).body(updatedRole);
    }

    @DeleteMapping("deleteRole/{id}")
    public void deleteRolebyId(@PathVariable int id){
        userService.deleteRolebyId(id);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> findUserById(@PathVariable int id){
        User searchedUser = null ;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Response","getuser");
        try{
            searchedUser = userService.findUserById(id);
        }catch (ErrorResponse e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null);
        }
        searchedUser = userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.FOUND).headers(httpHeaders).body(searchedUser);
    }

}
