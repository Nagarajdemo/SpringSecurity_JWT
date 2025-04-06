package stepdefinitions;

import com.google.inject.Inject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.guice.ScenarioScoped;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Student;
import model.UserLogin;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import utils.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ScenarioScoped
public class
StudentStepClass {

    UserLogin userLogin;
    CommonUtils commonUtils;
    Student student;
    String endpoint="/students";
    String loginEndPoint="/login";
    @Inject
    public StudentStepClass(UserLogin userLogin, CommonUtils commonUtils,Student student) {
        this.userLogin = userLogin;
        this.commonUtils=commonUtils;
        this.student=student;
    }
    List<Student> ls=new ArrayList<>();

    public  Student checkForStudentId(Integer id, String un, String pwd) throws IOException {
      Response rs=   RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + callLoginByReferringjson(un, pwd))
                . get(CommonUtils.getPropertyValue("baseurl")+endpoint+"/"+id);
        student=rs.as(Student.class);
        return student;

    }

    public Integer getTheCountOfStudents() {
        return ls.size();

    }

    public Response callStundent(String un,String pwd) throws IOException {
        Response rs= RestAssured.given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + callLoginByReferringjson(un, pwd))
                . get(CommonUtils.getPropertyValue("baseurl")+endpoint);
        ls=Arrays.asList(rs.as(Student[].class));
        return rs;
    }

    private String callLogin(String un, String pwd) throws JSONException {
        JSONObject requestParams = new JSONObject();
        requestParams.put("password", un);
        requestParams.put("userName", pwd);
        return RestAssured.given()
                .contentType("application/json")
                .body(requestParams.toString())
                .post(CommonUtils.getPropertyValue("baseurl")+loginEndPoint).getBody().asString();
    }
    private String callLoginUsingPojos(String un, String pwd) {
        userLogin.setUserName(un);
        userLogin.setPassword(pwd);
        return RestAssured.given()
                .contentType("application/json")
                .body(userLogin)
                .post(CommonUtils.getPropertyValue("baseurl")+loginEndPoint).getBody().asString();
    }

    private String callLoginByReferringjson(String un, String pwd) throws IOException {
        File jsonData=new File("src/test/resources/payloads/login.json");
        DocumentContext documentContext = JsonPath.parse(FileUtils.readFileToString(new File("src/test/resources/payloads/login.json"), StandardCharsets.UTF_8));
        documentContext.set("userName",un);
        documentContext.set("password",pwd);
        return RestAssured.given()
                .contentType("application/json")
                .body(documentContext.jsonString())
                .post(CommonUtils.getPropertyValue("baseurl")+loginEndPoint).getBody().asString();
    }

}
