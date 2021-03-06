package com.uiqun.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

    @Component
    public class RedisUtils {
        @Resource   //注入redisTemplate进行操作redis
        private RedisTemplate<String, Object> redisTemplate;

        /**
         * 保存字符数据类型到redis
         *
         * @param key
         * @param value
         * @return
         */
        public boolean set(String key, String value) {
            //设置健值使用序列化 某个对象很大时， 这个字符串会很冗长，所以要设置
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.setValueSerializer(new StringRedisSerializer());

            //获取操作对象、有如下几个
            /**HashOperations
             *HyperLogOperations
             *ListOperations
             *SetOperations
             *ValueOperations
             *zSetOperations
             */
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            //保存
            vo.set(key, value);
            return true;
        }

        /**
         * 保存字符数据类型到redis
         *
         * @param key
         * @param value
         * @param seconds 秒
         * @return
         */
        public boolean set(String key, String value, long seconds) {
            set(key, value);
            expire(key, seconds);
            return true;
        }

        /**
         * 更新指定key的value， 剩余过期时间不变
         *
         * @param key
         * @Param value
         * @Return
         */
        public boolean update(String key, String value) {
            //获取当前key的过期时间
            Long expireTime = getExpire(key);
            //重新设置过去时间
            if (expireTime == null)
                return false;
            if (expireTime == -2 || expireTime == 0)
                return false;
            set(key, value);
            if (expireTime > 0)
                expire(key, expireTime);
            return true;
        }

        /**
         * 判断某个key是否存在
         *
         * @param key
         * @return
         */
        public boolean exist(String key) {
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //设置序列化value的实例化对象
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            Object value = vo.get(key);
            return value == null ? false : true;
        }

        public Long getExpire(String key){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //设置序列化value的实例化对象
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String, Object> vo = redisTemplate.opsForValue();
            //获取当前key的过期时间
            return redisTemplate.getExpire(key);
        }

        /**
         *
         * @param key
         * @return
         */
        public Object get(String key){
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //设置序列化value的实例化对象
            redisTemplate.setValueSerializer(new StringRedisSerializer());
            ValueOperations<String,Object> vo = redisTemplate.opsForValue();
            return vo.get(key);
        }

        /**
         * 删除指定的key
         * @param key
         */
        public void delete(String key){
            try{
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                //设置序列化value的实例化对象
                redisTemplate.setValueSerializer(new StringRedisSerializer());
                redisTemplate.delete(key);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        /**
         * 实现命令：expire 设置过期时间，单位秒
         *
         * @param key
         * @return
         */
        public void expire(String key, long timeout) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }

        /**
         * 存储排行榜数据
         * @param key 排行榜名称
         * @param value 需要存的对象
         * @param score 分数
         */
        public void setSortedSet(String key,Object value,Double score){
            ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
            zSet.add(key,value,score);
        }

        /**
         * 获取对应排行榜前50名的数据 Set 集合
         * @param key
         * @return
         */
        private Set<Object> getSortedSet(String key){
            ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
            return zSet.reverseRange(key, 0, zSet.size(key)<50?-1:50);
        }

        /**
         * 获取对应分数
         * @param key
         * @param value
         * @return
         */
        public Double getSortScore(String key,String value){
            ZSetOperations<String, Object> zSet = redisTemplate.opsForZSet();
            Double score = zSet.score(key, value);
            return score==null?0d:score;
        }

        /**
         * 获取对应排行榜前50名的数据 Map 集合
         * @param key
         * @return
         */
        public LinkedHashMap<String,Integer> getSortInfo(String key){
            Set<Object> sortedSet = getSortedSet(key);
            LinkedHashMap<String,Integer> sortInfo = new LinkedHashMap<>();
            for (Object o : sortedSet) {
                sortInfo.put((String)o, getSortScore(key,(String)o).intValue() );
            }
            return sortInfo;
        }
    }
