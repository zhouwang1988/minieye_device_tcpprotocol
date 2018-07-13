package com.cictec.middleware.minieye.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cictec.middleware.minieye.entity.dto.RabbitMqClientDTO;


/**
 * camel rabbitmq dsl generate
 */
public class CamelRabbitMqDslUtils {

    public static String getCamelUrl(String host,String port,String exchangename,String username,String password,String queuename)  {

        StringBuffer rabbitmqUrl = new StringBuffer();
        rabbitmqUrl.append("rabbitmq://").append(host).append(":").append(port).append("/").append(exchangename).append("?").append("username=").append(username)
                .append("&password=").append(password).append("&queue=").append(queuename);

        return rabbitmqUrl.toString();
    }

    public static String getCamelUrl(RabbitMqClientDTO rabbitMqClientDTO){
        return CamelRabbitMqDslUtils.getCamelUrl(
                rabbitMqClientDTO.getHost(),
                rabbitMqClientDTO.getPort(),
                rabbitMqClientDTO.getExchangename(),
                rabbitMqClientDTO.getUsername(),
                rabbitMqClientDTO.getPassword(),
                rabbitMqClientDTO.getQueuename()
                );
    }
}
