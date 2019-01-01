package com.darian.darianSecurityDemo.controller;

import com.darian.darianSecurityDemo.dto.User;
import com.darian.darianSercurityCore.exception.Response;
import com.darian.darianSecurityDemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.darian.darianSercurityCore.exception.Response.*;

@Slf4j
@Api(description = "用户服务接口")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userServiceImpl;

    @PostMapping("/user")
    public User userCreate(@RequestBody @Validated User user/*, BindingResult errors*/){
/*        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(objectError -> {
                System.out.println(objectError.getDefaultMessage());
            });
        }*/

        user.setId(1L);
        return user;
    }

    @ApiOperation("得到所有的 User 的集合")
    @GetMapping("/users")
    public Response<List<User>> users() {
        return success(userServiceImpl.users());
    }

//    @JsonView(User.UserSimpleView.class)
    @ApiOperation("根据 id 获取对应的用户信息")
    @GetMapping("/user")
    @ApiImplicitParam(name = "id", dataType = "Long", value = "id")
    public List<User> user(@RequestParam(value = "id", required = false) Long id,
                           @PageableDefault(page = 2,
                                   size = 10,
                                   sort = "user, asc") Pageable pageable) {


        return Arrays.asList(new User[]{
                new User(12L, "darian", "sss", new Date()),
                new User(),
                new User()});
//        return userServiceImpl.find(id);
    }

//    @JsonView(User.UserDetailView.class)
    @GetMapping("/user/{id:\\d+}")
    public User getInfo(@PathVariable Long id) {
        return new User(id, "darian", "123456", new Date());
    }

    @ApiOperation(value = "添加一个用户",
            notes = "如果没有用户传进来，则后台随机生成一个用户信息")
    @PostMapping("/user/add")
    public boolean add(@RequestBody User user) {
        return userServiceImpl.add(user);
    }


    @GetMapping("/meAuthentication")
    @ApiOperation("获取当前的用户的登陆信息Authentication")
    public Authentication getCurrentUser(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/mePricipal")
    @ApiOperation("获取当前用户的登陆的 Pricipal 信息")
    public UserDetails getUserPricipal(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
