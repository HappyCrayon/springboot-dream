package com.springboot.admin.controller;

import com.springboot.api.entity.Employee;
import com.springboot.api.facade.ProductApi;
import com.springboot.common.exceptions.InternalApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(description = "测试HelloController")
public class HelloController {

    @Autowired
    private ProductApi productApi;

    @ApiOperation(value = "hello请求", notes = "hello请求")
    @ApiImplicitParam(name = "name", value = "名字", paramType = "path", required = true, dataType = "String")
    @RequestMapping(path="/hello_admin/{name}", method = RequestMethod.GET)
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }

    @ApiOperation(value = "hello请求", notes = "hello请求")
    @PostMapping(path="/post_hello")
    public String postHello(@RequestBody Employee employee) {
        return "yes";
    }


    @ApiOperation(value = "跨服务调用product接口", notes = "跨服务调用product接口")
    @ApiImplicitParam(name = "name", value = "名字", paramType = "path", required = true, dataType = "String")
    @RequestMapping(path="/hello_product/{name}", method = RequestMethod.GET)
    public String invokeProductHello(@PathVariable String name) throws InternalApiException {
        return productApi.invokeProdHello(name);
    }
}
