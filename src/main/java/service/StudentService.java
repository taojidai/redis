package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import utils.JedisSample;
import model.Student;

public class StudentService {
	public static final String key_id = "id";
	
	public void addStudent(Student student){
		Jedis jedis = JedisSample.getRedisConnection();
		this.addStudent(jedis,student);
		JedisSample.releaseRedis(jedis);
	}

	private void addStudent(Jedis jedis, Student student) {
		String key = "Student:"+student.getId();
		jedis.hset(key, "name", student.getName());
		jedis.hset(key, "birthday", student.getBirthday().toString());
		jedis.hset(key, "description", student.getDescription());
		jedis.hset(key, "avgscore", Integer.toString(student.getAvgscore()));
		jedis.sadd(key_id,student.getId());
		//jedis.zadd(key,Double.parseDouble(jedis.hget(key, "avgscore")),student.getId());
	}
	
	public List<Student> findStudents(){
		Jedis jedis = JedisSample.getRedisConnection();
		Set<String> ids = jedis.smembers(key_id);
		List<Student> students = new ArrayList<Student>();
		
		for(String id : ids){
			String key = "Student:"+ id;
			Student student = new Student();
			student.setId(id);
			student.setName(jedis.hget(key, "name"));
			student.setBirthday(java.sql.Date.valueOf(jedis.hget(key, "birthday")));
			student.setDescription(jedis.hget(key, "description"));
			student.setAvgscore(Integer.parseInt(jedis.hget(key, "avgscore")));
			jedis.zadd("student",Double.parseDouble(jedis.hget(key, "avgscore")),student.getId());
			
			
			students.add(student);
		}
		return students;
	}
	
	public List<Student> sortStudent(){
		findStudents();
		Jedis jedis = JedisSample.getRedisConnection();
		Set<String> ids = jedis.zrevrange("student", 0, 9); 
		List<Student> students = new ArrayList<Student>();
		for(String id : ids){
			String key = "Student:"+ id;
			Student student = new Student();
			student.setId(id);
			student.setName(jedis.hget(key, "name"));
			student.setBirthday(java.sql.Date.valueOf(jedis.hget(key, "birthday")));
			student.setDescription(jedis.hget(key, "description"));
			student.setAvgscore(Integer.parseInt(jedis.hget(key, "avgscore")));
			students.add(student);
		}
		return students;
	}
	
	public List<Student> page(int page){
		findStudents();
		Jedis jedis = JedisSample.getRedisConnection();
		
		 int  start = 10 * (page -  1 );
		 int  end = start + 10 -  1 ;
		Set<String> ids = jedis.zrevrange("student", start, end); 
		List<Student> students = new ArrayList<Student>();
		for(String id : ids){
			String key = "Student:"+ id;
			Student student = new Student();
			student.setId(id);
			student.setName(jedis.hget(key, "name"));
			student.setBirthday(java.sql.Date.valueOf(jedis.hget(key, "birthday")));
			student.setDescription(jedis.hget(key, "description"));
			student.setAvgscore(Integer.parseInt(jedis.hget(key, "avgscore")));
			students.add(student);
		}
		return students;
	}
	
	public Student findStudent(String id){
		Jedis jedis = JedisSample.getRedisConnection();
		String key = "Student:"+id;
		Student student = new Student();
		student.setId(id);
		student.setName(jedis.hget(key, "name"));
		student.setBirthday(java.sql.Date.valueOf(jedis.hget(key, "birthday")));
		student.setDescription(jedis.hget(key, "description"));
		student.setAvgscore(Integer.parseInt(jedis.hget(key, "avgscore")));
		return student;
	}
	
	public void deleteStudent( String id){
		Jedis jedis = JedisSample.getRedisConnection();
		this.deleteStudent(jedis , id);
		JedisSample.releaseRedis(jedis);
	}

	private void deleteStudent(Jedis jedis, String id) {
		String key = "Student:"+id;
		jedis.srem(key_id, id);
	}
	
	public void updateStudent(Student student){
		Jedis jedis = JedisSample.getRedisConnection();
		String key = "Student:"+student.getId();
		jedis.hset(key, "name", student.getName());
		jedis.hset(key, "birthday", student.getBirthday().toString());
		jedis.hset(key, "description", student.getDescription());
		jedis.hset(key, "avgscore", Integer.toString(student.getAvgscore()));
		
	}
}
