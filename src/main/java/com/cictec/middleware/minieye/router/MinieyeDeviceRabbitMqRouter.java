package com.cictec.middleware.minieye.router;
//package com.cictec.middleware.minieye.router;
//
//import com.cictec.middleware.minieye.processor.MinieyeRabbitMqProcessor;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MinieyeDeviceRabbitMqRouter extends RouteBuilder {
//    @Autowired
//    MinieyeRabbitMqProcessor minieyeRabbitMqProcessor;
//
//    @Value("${rabbitmq.minieye.host}")
//    private String host;
//    @Value("${rabbitmq.minieye.port}")
//    private String port;
//    @Value("${rabbitmq.minieye.exchangename}")
//    private String exchangename;
//    @Value("${rabbitmq.minieye.username}")
//    private String username;
//    @Value("${rabbitmq.minieye.password}")
//    private String password;
//    @Value("${rabbitmq.minieye.queuename}")
//    private String queuename;
//    @Value("${rabbitmq.minieye.skipqueuedeclare}")
//    private String skipqueuedeclare;
//
//
//    @Override
//    public void configure() throws Exception {
//
//        StringBuffer rabbitmqUrl = new StringBuffer();
//        rabbitmqUrl.append("rabbitmq://").append(host).append(":").append(port).append("/").append(exchangename).append("?").append("username=").append(username)
//                .append("&password=").append(password).append("&skipQueueDeclare=").append(skipqueuedeclare).append("&queue=").append(queuename);
//
//        from(rabbitmqUrl.toString()).process(minieyeRabbitMqProcessor);
//
//    }
//}
