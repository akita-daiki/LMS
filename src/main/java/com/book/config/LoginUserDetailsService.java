package com.book.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.book.entity.LoginUser;
import com.book.entity.Role;
import com.book.entity.UserInfo;
import com.book.entity.UserRole;
import com.book.service.RoleService;
import com.book.service.UserRoleService;
import com.book.service.UserService;



@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        
    	UserInfo user =  userService.searchByLoginId(loginId);
        
        List<UserRole> userRoles = userRoleService.searchByUserId(user.getUserId());
        
        List<Integer> roleIds = userRoles.stream()
                .map(userRole -> userRole.getRoleId())
                .collect(Collectors.toList());
        
        List<Role> roles = roleService.searchByIds(roleIds);
        
        List<String> roleNames = roles.stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        LoginUser loginUser = new LoginUser(
                user.getUserId(), // ユーザーIDをセット
                loginId,
                user.getUserName(),
                user.getPassword(),
                roleNames);
        
        return new LoginUserDetails(loginUser);
    }
}
