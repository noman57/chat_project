package com.project.config;

import com.project.event.StompConnectEvent;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by abdullah.alnoman on 07.08.17.
 */
@Component
public class CommonConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }




    @Bean
    public StompConnectEvent webSocketConnectHandler() {
        return new StompConnectEvent();
    }


}
