import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pageobject.CareersPage;
import pageobject.HomePage;

public class JuniorQAPositionTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"Junior Quality Assurance"})
//    , "Quality Assurance", "QA", "Account Director"})
    void assertForSearchingJobTitle(String jobTitle) {
        HomePage homePage = new HomePage(driver);
        CareersPage careersPage = new CareersPage(driver);

        homePage.goToCareersPage();

        boolean isThereJuniorQAPosition = careersPage.hasAJobTitle(jobTitle);
        Assertions.assertTrue(isThereJuniorQAPosition, "So sad, there are no junior QA positions at the moment");
    }
}
