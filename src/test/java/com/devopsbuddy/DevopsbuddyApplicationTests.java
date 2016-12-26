package com.devopsbuddy;

import com.devopsbuddy.backend.service.I18nService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopsbuddyApplicationTests {

	@Autowired
	private I18nService i18nService;

	@Test
	public void testMessageByLocaleService() {
	    String expectedResult = "Bootstrap starter template";
	    String messageId = "index.main.callout";
	    String actual = i18nService.getMessage(messageId);
        Assert.assertEquals("The Actual and expected Strings don't match",expectedResult, actual);
    }


}
