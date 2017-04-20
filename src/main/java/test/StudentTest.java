package test;

import org.junit.Test;

import model.Student;
import redis.clients.jedis.Jedis;
import service.StudentService;
import utils.JedisSample;

public class StudentTest {
	 StudentService studentService = new StudentService();
	 
	 @Test
	 public void add(){
		 Student student = new Student("12","1",java.sql.Date.valueOf("2001-01-02"),"1",99);
		 studentService.addStudent(student);
		 System.out.println(studentService.sortStudent());
	 }
	 @Test
	 public void list(){
		 
		 System.out.println(studentService.sortStudent());
	 }
	 
	 @Test
	 public void update(){
		 Student student = new Student("1","1",java.sql.Date.valueOf("2001-01-03"),"1",99);
		studentService.updateStudent(student);
		System.out.println(studentService.sortStudent());
	 }
	 
	 @Test
	 public void del(){
		 
		studentService.deleteStudent("1");
		System.out.println(studentService.sortStudent());
	 }
}
