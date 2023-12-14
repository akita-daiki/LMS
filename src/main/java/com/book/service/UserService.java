package com.book.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.book.constants.Cache.TableType;
import com.book.dao.UserInfoMapper;
import com.book.entity.UserInfo;
import com.book.util.LmsUtils;




/**
 * ユーザーサービス
 * 
 * @author doublecrop
 */
@Service
public class UserService {
    
    /** ユーザーマッパー */
    @Autowired
    private UserInfoMapper userInfoMapper;
    
    /** ユーザーキャッシュ */
    @Autowired
    private RedisTemplate<String, UserInfo> userTemplete;

    /**
     * 検索します.
     * 
     * @param loginId ログインID
     * @return 検索結果
     */
    public UserInfo searchByLoginId(String loginId) {

        // キャッシュキー取得
        String cacheKey = LmsUtils.getCasheKey(Arrays.asList(TableType.USER.name(), loginId));
        // ユーザー情報取得
        Optional<UserInfo> user = Optional.ofNullable(userTemplete.opsForValue().get(cacheKey));
        if (user.isPresent()) {
            return user.get();
        }
        
        user = Optional.ofNullable(userInfoMapper.searchByLoginId(loginId));
        if (user.isPresent()) {
            userTemplete.opsForValue().set(cacheKey, user.get());
            userTemplete.expire(loginId, TimeUnit.HOURS.toHours(1), TimeUnit.DAYS); 
        }
        return user.orElseThrow(() -> new RuntimeException("ユーザーが存在しません."));
    }
}