package TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/Features/F01-Loginpage.feature"},  glue={"TestStepdef"},
monochrome=true,
plugin={"pretty","junit:target/JunitReports/report.xml",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
tags="@RegressionTest or @SmokeTest"

)
public class Runner {

}


