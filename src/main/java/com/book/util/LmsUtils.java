package com.book.util;

import java.util.List;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LmsUtils {

    /**
     * キャッシュキーを取得します.
     * 
     * @param keys キー
     * @return キャッシュキー
     */
    public static String getCasheKey(List<String> keys) {
        return keys.stream()
                .collect(Collectors.joining("-"));
    }
}
