package com.book.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.book.constants.Cache.SelectType;
import com.book.constants.Cache.TableType;
import com.book.dao.RoleMapper;
import com.book.entity.Role;
import com.book.util.LmsUtils;


@Service
public class RoleService {
    
    /** ロールマッパー */
    @Autowired
    private RoleMapper roleMapper;
    
    /** ロールキャッシュ */
    @Autowired
    private RedisTemplate<String, Role> roleTemplete;

    public List<Role> searchByIds(List<Integer> roleIds) {
        
        // キャッシュキー取得
        String cacheKey = LmsUtils.getCasheKey(Arrays.asList(TableType.ROLE.name(), roleIds.stream()
                .map(roleId -> roleId.toString())
                .collect(Collectors.joining("-"))));
        
        Optional<List<Role>> roles = Optional.ofNullable(roleTemplete.opsForList().range(cacheKey, 0, -1));
        
        if (!roles.get().isEmpty()) {
            return roles.get();
        }
        
        roles = Optional.ofNullable(roleMapper.searchByIds(roleIds));
        if (roles.isPresent()) {
            roles = Optional.of(roles.get());
            roleTemplete.opsForList().rightPushAll(cacheKey, roles.get());
            roleTemplete.expire(cacheKey, TimeUnit.HOURS.toHours(1), TimeUnit.MINUTES);
        }
        return roles.orElseThrow(() -> new RuntimeException("ユーザーロールが存在しません."));
    }
    
    public Optional<List<Role>> searchAll() {
        
        // キャッシュキー取得
        String cacheKey = LmsUtils.getCasheKey(Arrays.asList(TableType.ROLE.name(), SelectType.SEARCH_ALL.name()));
        
        Optional<List<Role>> roles = Optional.ofNullable(roleTemplete.opsForList().range(cacheKey, 0, -1));
        
        if (!roles.get().isEmpty()) {
            return Optional.of(roles.get());
        }

        roles = Optional.ofNullable(roleMapper.searchAll());
        if (roles.isPresent()) {
            roles = Optional.of(roles.get());
            roleTemplete.opsForList().rightPushAll(cacheKey, roles.get());
            roleTemplete.expire(cacheKey, TimeUnit.HOURS.toHours(1), TimeUnit.MINUTES);
        }
        return roles;
    }
}
