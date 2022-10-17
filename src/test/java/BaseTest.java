package test.java;

import main.java.common.DriverManager;
import main.java.common.InitManager;
import main.java.common.PageManager;
import main.java.core.pages.HomePage;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class BaseTest {
	HomePage homePage = new HomePage();
	
	protected PageManager startTest = PageManager.getPageManager();
	private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        //driverManager.getDriver().get(TestPropManager.getTestPropManager().getProperty(BASE_URL));
       //временно
    	driverManager.getDriver().get("https://www.dns-shop.ru/");;

    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
