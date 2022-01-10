
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
public class GradleTestRunner {

    @RunWith(Cucumber.class)
    @CucumberOptions (
            glue = {"ru.alfabank.steps", "ru.netology.steps"},
            format = {"pretty", "json:build/cucumber/cucumber1.json"},
            features = {"C:\\Users\\nikol\\IdeaProjects\\QAJavaLessons\\Gradle\\BDDAkita\\build\\resources\\test\\features\\MoneyTransfer.feature"},
            monochrome = false
    )
    public static class GradleTestRunner1 { }

}
