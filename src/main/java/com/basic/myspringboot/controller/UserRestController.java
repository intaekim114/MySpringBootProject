package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@Controller + @ResponseBody
@RestController
@RequiredArgsConstructor  // final 인 변수를 초기화 하는 생성자를 자동으로 생성해주는 역할을 하는 롬복 어노테이션
@RequestMapping("/api/users")
public class UserRestController {
    private final UserRepository userRepository;

    //Constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        System.out.println(">>> UserController" + userRepository.getClass().getName());
//        this.userRepository = userRepository;
//    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user); //등록
    }

    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        ResponseEntity<User> responseEntity = optionalUser
                .map(user -> ResponseEntity.ok(user)) // User가 있는 경우 200 status
//                .orElse(ResponseEntity.notFound().build()); // User가 없는 경우 404 status
                .orElse(new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND));
        return responseEntity;

//        return optionalUser.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}/")
    public User getUserByEmail(@PathVariable String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        User existUser =
                optionalUser.orElseThrow(() -> new BusinessException("User Not Found", HttpStatus.NOT_FOUND));
        return  existUser;


    }

}
