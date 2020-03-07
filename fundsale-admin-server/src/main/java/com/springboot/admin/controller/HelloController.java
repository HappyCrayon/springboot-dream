package com.springboot.admin.controller;

import com.springboot.api.entity.Employee;
import com.springboot.api.entity.Product;
import com.springboot.api.facade.ProductApi;
import com.springboot.common.exceptions.InternalApiException;
import com.springboot.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Api(description = "测试HelloController")
public class HelloController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

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

    @ApiOperation(value = "跨服务调用product接口", notes = "跨服务调用product接口")
    @RequestMapping(path="/testGeneri", method = RequestMethod.POST)
    public Result testGeneri() throws InternalApiException {
        Result<Product> result = productApi.test();
        System.out.println(result.getData() instanceof Product);
        log.info("{}", result);
        return result;
    }
}
