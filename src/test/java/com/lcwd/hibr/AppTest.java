package com.lcwd.hibr;

import com.lcwd.hibr.entities.Student;
import com.lcwd.hibr.service.StudentService;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
	StudentService service = new StudentService();

	public void getStudentTest() {
		Student byId = service.getById(1);
		System.out.println(byId.getName());
		System.out.println(byId.getCertificates().size());
		byId.getCertificates().forEach(cer -> {
			System.out.println(cer.getTitle());
		});

		Student byId2 = service.getById(7);
		System.out.println(byId2.getName());
		System.out.println(byId2.getCertificates().size());
		byId2.getCertificates().forEach(cer -> {
			System.out.println(cer.getTitle());
		});

	}
}
