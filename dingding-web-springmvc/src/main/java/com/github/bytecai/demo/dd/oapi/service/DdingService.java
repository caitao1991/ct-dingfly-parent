package com.github.bytecai.demo.dd.oapi.service;

import com.github.bytecai.demo.dd.oapi.config.DdOapiConfig;
import com.github.bytecai.demo.dd.oapi.handler.AttendanceHandler;
import com.github.bytecai.demo.dd.oapi.handler.CheckUrlHandler;
import com.github.bytecai.demo.dd.oapi.handler.LogHandler;
import com.github.bytecai.demo.dd.oapi.thread.Comsumer;
import com.github.bytecai.demo.dd.oapi.thread.Producer;
import com.github.bytecai.demo.dd.oapi.thread.ResourceBean;
import com.google.common.collect.ImmutableMap;
import my.caijar.dingding.oapi.api.DdOapiMessageRouter;
import my.caijar.dingding.oapi.api.impl.DdOapiServiceImpl;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import my.caijar.dingding.oapi.config.impl.DdOapiDefaultConfigImpl;
import my.caijar.dingding.oapi.constant.DdOapiEventConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static my.caijar.dingding.common.api.DdConsts.DdMsgType.EVENT;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/5/6 17:22
 * @Version 1.0
 */
@Service
public class DdingService extends DdOapiServiceImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected LogHandler logHandler;

	@Autowired
	protected AttendanceHandler attendanceHandler;

	@Autowired
	protected CheckUrlHandler checkUrlHandler;

	@Autowired
	private DdOapiConfig ddConfig;

	private DdOapiMessageRouter router;

	@PostConstruct
	public void init() {
		logger.info(">>>" + this.ddConfig.toString() + "<<<");
		final DdOapiDefaultConfigImpl config = new DdOapiDefaultConfigImpl();
		// 设置钉钉微应用的appid
		config.setAppKey(this.ddConfig.getApp_key());
		// 设置钉钉微应用配置的 corpSecret
		config.setSecret(this.ddConfig.getApp_secret());
		// 设置钉钉配置的token
		config.setToken(this.ddConfig.getEncryp_token());
		// 设置钉钉加解密密钥
		config.setAesKey(this.ddConfig.getEncoding_aes_key());
		// 设置钉钉微应用的应用AgentId
		config.setAgentId(this.ddConfig.getAgent_id());
		// 设置钉钉企业CorpId
		config.setCorpId(this.ddConfig.getCorp_id());
		//设置钉钉回调地址
		config.setUrl(this.ddConfig.getUrl());

		super.setMultiConfigStorages(ImmutableMap.of(config.getAppKey(), config), config.getAppKey());

		this.refreshRouter();

		//注册回调事件
		this.registerEvent();
	}

	private void refreshRouter() {

		final DdOapiMessageRouter newRouter = new DdOapiMessageRouter(this);

		// 记录所有事件的日志
		newRouter.rule().handler(this.logHandler).next();

		// 验证回调url的handler
		newRouter.rule().async(false).msgType(EVENT).eventType(DdOapiEventConstants.CHECK_URL).handler(this.checkUrlHandler).end();

		//考勤打卡回调事件
		newRouter.rule().async(false).msgType(EVENT).eventType(DdOapiEventConstants.Attendance.ATTENDANCE_CHECK_RECORD).handler(this.attendanceHandler).end();

		// 审批回调事件
//		newRouter.rule().async(false).msgType(EVENT).event(DdOapiEventConstants.Approval.BPMS_INSTANCE_CHANGE).handler(this.nullHandler).end();

		// 默认
//		newRouter.rule().async(false).handler(this.msgHandler).end();

		this.router = newRouter;
	}

	public DdOapiOutMessage route(DdOapiMessage message) {
		try {
			return this.router.route(message);
		} catch (Exception e) {
			this.logger.error(e.getMessage(), e);
		}
		return null;
	}

	/***
	 * 后期完善需要采用重试策略，一次最多五次。定时检查。
	 */
	public static final String[] CALLBACK_TAG = new String[]{DdOapiEventConstants.Attendance.ATTENDANCE_CHECK_RECORD};
	public void registerEvent() {
		super.switchover(ddConfig.getApp_key());
		new Thread(()-> {
			while (true) {
				try {
					if (this.getCbManagerHelper().registerCallBack(Arrays.asList(CALLBACK_TAG))) {
						System.out.println(">>> 事件注册成功了 <<<");
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}


	public static void main(String[] args) {
		ResourceBean bean = new ResourceBean(500, 20);
		System.out.println("CPU核数=" + Runtime.getRuntime().availableProcessors());

		int cpuCount = Runtime.getRuntime().availableProcessors() - 1;
		// 借助Executors
		ExecutorService service = Executors.newFixedThreadPool(cpuCount);
		// 启动线程
		List<Runnable> produceRunnables = new ArrayList<>();
		List<Runnable> consumerRunnables = new ArrayList<>();
		Producer producer = null;
		Comsumer comsumer = null;


		int eCount = 20;
		for (int i = 0; i < eCount; i++) {
			producer = new Producer(bean);
			produceRunnables.add(producer);
			service.execute(producer);
		}

		for (int i = 0; i < 5; i++) {
			comsumer = new Comsumer(bean);
			consumerRunnables.add(comsumer);
			service.execute(comsumer);
		}
		// 退出Executor
		service.shutdown();




	}
}
