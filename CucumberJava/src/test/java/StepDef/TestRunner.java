package StepDef;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue= {"StepDef"},
monochrome = true, tags = "@Test2" //change tags to either @Test1, @Test2 or depending on tags written in feature file
		)
public class TestRunner {

}
