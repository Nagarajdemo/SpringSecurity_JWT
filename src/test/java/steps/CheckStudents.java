package steps;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.guice.ScenarioScoped;
import io.restassured.response.Response;
import org.testng.Assert;
import stepdefinitions.StudentStepClass;

import java.io.IOException;

@ScenarioScoped
public class CheckStudents {

    StudentStepClass studentStepClass;

    @Inject
    public CheckStudents(StudentStepClass studentStepClass) {
        this.studentStepClass = studentStepClass;
    }

    @Given("make a call to the students endpoint with different user credentials {string} and {string}")
    public void makeACallToTheStudentsEndpoint(String un,String pwd) throws IOException {
        Response rs= studentStepClass.callStundent(un,pwd);
        Assert.assertEquals(rs.statusCode(),200);
    }

    @When("get the count of the Students list")
    public void getTheCountOfTheStudentsList() {
        Assert.assertTrue((studentStepClass.getTheCountOfStudents()>0));
    }

    @Then("validate each studet by {int} for user credentials {string} and {string}")
    public void validateEachStudetBy(Integer id, String un, String pwd) throws IOException {
       Assert.assertNotNull(studentStepClass.checkForStudentId(id, un, pwd));


    }
}
