package com.book.constants;

/**
 * キャッシュenum
 * 
 * @author doublecrop
 */
public class Cache {

    /**
     * テーブルタイプ
     */
    public enum TableType {
        /** ユーザー */
        USER,
        /** ユーザーロール */
        USER_ROLE,
        /** ロール */
        ROLE,
        ;
    }
    
    /**
     * セレクトタイプ
     */
    public enum SelectType {
        /** 全検索 */
        SEARCH_ALL
        ;
    }
}