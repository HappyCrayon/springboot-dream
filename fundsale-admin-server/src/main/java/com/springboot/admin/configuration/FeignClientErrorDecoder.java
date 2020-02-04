//package com.springboot.admin.configuration;
//
//import com.google.gson.Gson;
//import feign.Response;
//import feign.Util;
//import feign.codec.ErrorDecoder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
//@Configuration
//public class FeignClientErrorDecoder implements ErrorDecoder {
//
//    private static Logger log = LoggerFactory.getLogger(FeignClientErrorDecoder.class);
//
//    private static final Gson gson = new Gson();
//
//    @Override
//    public Exception decode(String methodKey, Response response) {
//        try {
//            String errorContent = Util.toString(response.body().asReader());
////                    InternalApiException internalApiException = gson.fromJson(errorContent, InternalApiException.class);
//            return new RuntimeException();
//        }
//        catch (IOException e) {
//            log.error("handle error exception");
//            return new RuntimeException();
//        }
//    }
//}
