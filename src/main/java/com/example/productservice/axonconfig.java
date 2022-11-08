package com.example.productservice;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class axonconfig {
    @Bean
    public XStream xStream(){
        XStream xStream = new XStream();
        xStream.allowTypesByWildcard(new String[] {"com.example.**"});
        xStream.addPermission(AnyTypePermission.ANY);
        return xStream;
    }
}
