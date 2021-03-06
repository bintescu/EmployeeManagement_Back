package com.ausy_technologies.demospring.Service;

import com.ausy_technologies.demospring.Exceptions.ErrorResponse;
import com.ausy_technologies.demospring.Model.DAO.Role;
import com.ausy_technologies.demospring.Model.DAO.User;
import com.ausy_technologies.demospring.Model.DTO.UserDto;
import com.ausy_technologies.demospring.Repository.RoleRepository;
import com.ausy_technologies.demospring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public Role saveRole(Role role) {


        return this.roleRepository.save(role);
    }


    public User saveUser(User user) {


        return this.userRepository.save(user);
    }

    public User saveUser2(User user ,int idRole)
    {

       Role role= this.roleRepository.findById(idRole).get();

       List<Role> roleList =new ArrayList<>();
       roleList.add(role);

       if(role!=null) {

           user.setRoleList(roleList);
           return this.userRepository.save(user);
       }
       else
       {
           throw new ErrorResponse("User not found",404);
       }


    }


    public User saveUser3(  User user ,List<Role> roleList)
    {
        user.setRoleList(roleList);
        return this.userRepository.save(user);

    }



    public Role findRoleById(int id)
    {

        if (!roleRepository.findById(id).isPresent()) {
            throw new ErrorResponse("Role not found !",404);
        }
        else {
            return roleRepository.findById(id).get();
        }

    }

    public List<Role> findAllRoles()
    {
        return this.roleRepository.findAll();
    }

    public User findUserById(int id){
        User user = this.userRepository.findById(id);
        if(user == null){
            throw new ErrorResponse("User not found !",404);

        }
        return user;
    }

    public List<User> findAllUsers()
    {
        return this.userRepository.findAll();
    }


    public void deleteUserById(int id) { this.userRepository.deleteById(id); }

    public User updateUser(int id, User user){
        User modifiedUser = userRepository.findById(id);

        if(modifiedUser != null){

            modifiedUser.setRoleList(user.getRoleList());
            modifiedUser.setFirstName(user.getFirstName());
            modifiedUser.setLastName(user.getLastName());
            modifiedUser.setEmail(user.getEmail());
            modifiedUser.setUsername(user.getUsername());
            modifiedUser.setPassword(user.getPassword());
            userRepository.save(modifiedUser);
        }
        else {
            throw new ErrorResponse("User not found !",404);
        }
        return modifiedUser;
    }

    public User updateUser2(int id, List<Role> roleList){
        User modifiedUser = userRepository.findById(id);

        if(modifiedUser != null){

            modifiedUser.setRoleList(roleList);
            userRepository.save(modifiedUser);
        }
        else {
            throw new ErrorResponse("User not found !",404);
        }
        return modifiedUser;
    }

    public Role updateRole(int id, String name){
        Role modifiedRole = null;
        try{
            modifiedRole = roleRepository.findById(id).get();
            modifiedRole.setName(name);
            roleRepository.save(modifiedRole);
        }catch (RuntimeException e){
            throw new ErrorResponse(e,"Role not found !",404);
        }

        return modifiedRole;
    }

    public void deleteRolebyId(int id){
        roleRepository.deleteById(id);
    }


    public UserDto findUserDTObyId(int id){
        User user = this.userRepository.findById(id);
        if(user == null){
            throw new ErrorResponse("User not found !",404);

        }
        else {

            return UserMapping(user);
        }
    }

    public List<UserDto> findAllUsersDTO(){
        List<User> userList = userRepository.findAll();
        if(userList != null) {

            List<UserDto> userDtos = new ArrayList<>();
            for (User user : userList) {
                userDtos.add(UserMapping(user));
            }
            return userDtos;
        }
        else {
            throw new ErrorResponse("User table is empty !",204);
        }
    }

    private UserDto UserMapping(User user ){
        if(user != null) {
            UserDto userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            List<String> stringRoleList = new ArrayList<>();
            for (Role role : user.getRoleList()) {
                stringRoleList.add(role.getName());
            }
            userDto.setRoleList(stringRoleList);
            return userDto;
        }
        else {
            throw new ErrorResponse("User in Mapping is null",204);
        }
    }

}
