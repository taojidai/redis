package utils;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisSample {
	private static JedisPool pool;

	static{
		pool = new JedisPool(new JedisPoolConfig(),"127.0.0.1");
	}
	
	public static Jedis getRedisConnection(){
		return pool.getResource();
	}
	
	public static void releaseRedis(Jedis jedis){
		pool.returnBrokenResource(jedis);
	}
}
