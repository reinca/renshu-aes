package com.practice.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.practice.aes.Application;
import com.practice.aes.domain.model.UserData;
import com.practice.aes.domain.repository.UserDataRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DataJpaTest
// Database CRUD Test
public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Autowired private TestEntityManager entityManager;
	@Autowired private UserDataRepository udr;

	@Test
	public void dbTester() {
		UserData tester = new UserData("tester-API");
		// Test data register
        this.entityManager.persist(tester);

		// read Test & well-saved test
		UserData result = this.udr.findOneByUid(tester.getUid());
		String resultKey = result.getEncryptedKey();
		logger.info("Search result	: " + result.toString());
		assertNotNull(result);
		
		// update test
		tester.setEncryptedKey("dummy");
		this.udr.save(tester);
		logger.info("Updated result	: " + tester.toString());
		assertThat(resultKey).isNotEqualTo(tester.getEncryptedKey());
		
		// delete used data
		this.udr.delete(tester);
	}
}
