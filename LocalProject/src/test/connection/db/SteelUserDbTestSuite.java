package test.connection.db;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	DBUtilTestCase.class,
	LogIntegrationTestCase.class
})
public class SteelUserDbTestSuite {

}
