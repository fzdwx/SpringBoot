package lk.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author likeLove
 * @time 2020-08-04  15:21
 */
@Data
@Component
@ConfigurationProperties (prefix = "user")
public class User {
    private String myName;
    private int age;

}