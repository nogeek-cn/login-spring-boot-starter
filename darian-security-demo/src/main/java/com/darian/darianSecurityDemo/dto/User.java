package com.darian.darianSecurityDemo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
//    public interface UserSimpleView {
//    }

//    public interface UserDetailView extends UserSimpleView {
//    }

//    @JsonView(UserSimpleView.class)
    @ApiModelProperty(value = "id")
    private Long id;
    @NotBlank
//    @JsonView(UserSimpleView.class)
    @ApiModelProperty(value = "用户名")
    private String username;
//    @JsonView(UserDetailView.class)
    @ApiModelProperty(value = "密码")
    private String password;

    private Date birthday;

//    private Collection<? extends GrantedAuthority> authorities;
//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    private boolean credentialsNonExpired;
//    private boolean enabled;
}
