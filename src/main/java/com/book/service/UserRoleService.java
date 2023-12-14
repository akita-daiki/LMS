package com.book.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.book.constants.Cache.TableType;
import com.book.dao.UserRoleMapper;
import com.book.entity.UserRole;
import com.book.util.LmsUtils;



/**
 * ユーザーロールサービス
 * 
 * @author doublecrop
 */
@Service
public class UserRoleService {
    
    /** ユーザーロールマッパー */
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    /** ユーザーロールキャッシュ */
    @Autowired
    private RedisTemplate<String, UserRole> userRoleTemplete;

    /**
     * 検索します.
     * 
     * @param userId ユーザーID
     * @return 検索結果
     */
    public List<UserRole> searchByUserId(Integer userId) {

        // キャッシュキー取得
        String cacheKey = LmsUtils.getCasheKey(Arrays.asList(TableType.USER_ROLE.name(), userId.toString()));
        // ユーザーロール情報取得
        Optional<List<UserRole>> userRoles = Optional.ofNullable(userRoleTemplete.opsForList().range(cacheKey, 0, -1));
        if (!userRoles.get().isEmpty()) {
            return userRoles.get();
        }
        
        userRoles = Optional.ofNullable(userRoleMapper.searchByUserId(userId));
        if (userRoles.isPresent()) {
            userRoles = Optional.of(userRoles.get());
            userRoleTemplete.opsForList().rightPushAll(cacheKey, userRoles.get());
            userRoleTemplete.expire(cacheKey, TimeUnit.HOURS.toHours(1), TimeUnit.MINUTES);
        }
        return userRoles.orElseThrow(() -> new RuntimeException("ユーザーロールが存在しません."));
    }
}
