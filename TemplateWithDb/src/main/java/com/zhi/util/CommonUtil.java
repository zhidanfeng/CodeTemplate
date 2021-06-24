package com.zhi.util;

import com.zhi.core.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonUtil {

    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        user.setUserName("zhi");
        userList.add(user);

        user = new User();
        user.setId(2L);
        user.setUserName("dan");
        userList.add(user);

        user = new User();
        user.setId(3L);
        user.setUserName("feng");
        userList.add(user);

        user = new User();
        user.setId(4L);
        user.setUserName("zhi");
        userList.add(user);

        List<String> nameList = mapField(userList, User::getUserName);
        System.out.println(nameList.size());
    }

    /**
     * @name: listToMap
     * @description: List转Map
     * @param list:
     * @param keyMapper:
     * @return:
     * @since: 2021/6/21 9:33 上午
     * @author: zhidanfeng
     */
    public static <T, K> Map<K, T> listToMap(List<T> list,
                                             Function<? super T, ? extends K> keyMapper) {
        return listToMap(list, keyMapper, item -> item);
    }

    /**
     * @name: listToMap
     * @description: List转Map
     * @param list:
     * @param keyMapper:
     * @param valueMapper:
     * @return:
     * @since: 2021/6/21 9:33 上午
     * @author: zhidanfeng
     */
    public static <T, K, V> Map<K, V> listToMap(List<T> list,
                                                Function<? super T, ? extends K> keyMapper,
                                                Function<? super T, ? extends V> valueMapper) {
        return listToMap(list, keyMapper, valueMapper, (v1, v2) -> v1);
    }

    /**
     * @name: listToMap
     * @description: List转Map
     * @param list:
     * @param keyMapper:
     * @param valueMapper:
     * @param mergeFunction:
     * @return:
     * @since: 2021/6/21 9:33 上午
     * @author: zhidanfeng
     */
    public static <T, K, V> Map<K, V> listToMap(List<T> list,
                                                Function<? super T, ? extends K> keyMapper,
                                                Function<? super T, ? extends V> valueMapper,
                                                BinaryOperator<V> mergeFunction) {
        if (null == list || list.size() == 0) {
            return new HashMap<>();
        }
        if (null == mergeFunction) {
            return list.stream().collect(Collectors.toMap(keyMapper, valueMapper));
        }
        return list.stream().collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction));
    }

    public static <T, R> List<R> mapField(List<T> list, Function<? super T, ? extends R> mapper) {
        return list.stream().map(mapper).collect(Collectors.toList());
    }
}
