package springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
//@Component
//@ConfigurationProperties(prefix = "mycar")
public class Car {
    private String brand;
    private Integer price;
}
