package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "classpath:features",
        dryRun = false,
        snippets = SnippetType.CAMELCASE,
        monochrome = true,
        glue = { "steps"},
        plugin =  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"


)
public class Runner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = true)
    public Object[][] scenarios(){
        return super.scenarios();
    }


}
