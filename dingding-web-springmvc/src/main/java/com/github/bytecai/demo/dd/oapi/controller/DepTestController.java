package com.github.bytecai.demo.dd.oapi.controller;

import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.taobao.api.ApiException;
import my.caijar.dingding.oapi.api.DdOapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/3 11:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/dep")
public class DepTestController {

	private DdOapiService ddOapiService;

	@Autowired
	public DepTestController(DdOapiService ddOapiService) {
		this.ddOapiService = ddOapiService;
	}


	@GetMapping(value = "/sayHello")
    public String test(String name) {
        System.out.println(name);
        return "测试成功" + name;
    }
    @GetMapping(value = "/list")
    public String listDep() {
		List<OapiDepartmentListResponse.Department> allDeps = null;
		try {
			allDeps = ddOapiService.getDepartmentHelper().getAllDeps("", true);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return allDeps.size() + "";
	}
}
