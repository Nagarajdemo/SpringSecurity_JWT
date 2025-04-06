package utils;

import io.cucumber.guice.ScenarioScoped;
import jodd.props.Props;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;

@ScenarioScoped
public class CommonUtils {

    static Props props;

    static {
        loadProperties();
    }

    public static String getPropertyValue(String propertyName){
        String value=System.getProperty(propertyName);
        if(StringUtils.isBlank(value)){
            value= props.getValue(propertyName);
        }
        return value;
    }

    public static void loadProperties(){
        props=new Props();
        try {
        final InputStream inputStream=
                CommonUtils.class.getClassLoader().getResourceAsStream("automation.properties");
            props.load(inputStream,"UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
