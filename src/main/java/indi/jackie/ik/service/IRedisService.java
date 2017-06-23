package indi.jackie.ik.service;

import java.util.List;

/**
 * @author jackie chen
 * @create 2017-06-05
 * @description RedisService 接口
 */
public interface IRedisService {
    boolean set(String key, String value);

    String get(String key);

    <T> T get(final String key, Class<T> clz);

    boolean expire(String key, long expire);

    <T> boolean setList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    <T> long lpush(String key, Object obj);

    long rpush(String key, Object obj);

    String lpop(String key);

    long decr(final String key);

    long del(final String key);
}
