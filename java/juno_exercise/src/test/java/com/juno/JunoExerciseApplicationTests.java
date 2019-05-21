package com.juno;

import static org.junit.Assert.assertNull;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
// @SpringBootTest(properties = { "-Dfrom='2019-01-05'" })
@SpringBootTest
public class JunoExerciseApplicationTests {

	@Autowired
	XrefManager xrefManager;

	@Test
	public void contextLoads() {
		try {
			xrefManager.exportXrefsFromDate("11/01/2019","C:/temp");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			assertNull(e);
		}
	}
}
