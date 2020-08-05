package com.example.shop.controller;


import com.example.shop.domain.dto.UserDto;
import com.example.shop.mapper.UserMapper;
import com.example.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public void addUser(@RequestBody UserDto userDto) {
        userService.save(userMapper.userDtoToUser(userDto));
    }

    @PutMapping("{id}")
    public void updateUser(@RequestBody UserDto userDto, @PathVariable long id) {
        userService.updateUser(userMapper.userDtoToUser(userDto), id);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @GetMapping
    public Page<UserDto> getPageUsers(@RequestParam int page, @RequestParam int size) {
        return userService.getPage(PageRequest.of(page, size))
                .map(userMapper::userToUserDto);
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userMapper.userToUserDto(userService.getUserById(id));
    }
}
