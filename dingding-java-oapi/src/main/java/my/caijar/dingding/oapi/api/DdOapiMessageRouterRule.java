package my.caijar.dingding.oapi.api;

import my.caijar.dingding.common.api.DdErrorExceptionHandler;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.impl.DdOapiMessageHandler;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/27 23:42
 * @Version 1.0
 */
public class DdOapiMessageRouterRule {
    private final DdOapiMessageRouter routerBuilder;
    private boolean async = true;
    private String eventType;
    private String msgType;
	private String event;
	private boolean reEnter = false;	//用于日志记录handler添加判断
    private List<DdOapiMessageHandler> handlers = new ArrayList();
    public DdOapiMessageRouterRule(DdOapiMessageRouter routerBuilder) {
        this.routerBuilder = routerBuilder;
    }
    public DdOapiMessageRouterRule async(boolean async) {
        this.async = async;
        return this;
    }

    public DdOapiMessageRouterRule msgType(String msgType) {
        this.msgType = msgType;
        return this;
    }

    public DdOapiMessageRouterRule eventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

	public DdOapiMessageRouterRule event(String event) {
		this.event = event;
		return this;
	}


    public DdOapiMessageRouterRule handler(DdOapiMessageHandler handler) {
        return this.handler(handler, (DdOapiMessageHandler[])null);
    }

    public DdOapiMessageRouterRule handler(DdOapiMessageHandler handler, DdOapiMessageHandler... otherHandlers) {
        this.handlers.add(handler);
        if (otherHandlers != null && otherHandlers.length > 0) {
            DdOapiMessageHandler[] var3 = otherHandlers;
            int var4 = otherHandlers.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                DdOapiMessageHandler i = var3[var5];
                this.handlers.add(i);
            }
        }

        return this;
    }

    public DdOapiMessageRouter end() {
        this.routerBuilder.getRules().add(this);
        return this.routerBuilder;
    }

    protected DdOapiOutMessage service(DdOapiMessage ddOapiMessage, Map<String, Object> context, DdOapiService ddOapiService,  DdErrorExceptionHandler exceptionHandler) {
        if (context == null) {
            context = new HashMap();
        }

        try {
            DdOapiOutMessage res = null;
            Iterator var11 = this.handlers.iterator();

            while(var11.hasNext()) {
                DdOapiMessageHandler handler = (DdOapiMessageHandler)var11.next();
                if (handler != null) {
                    res = handler.handle(ddOapiMessage, (Map)context, ddOapiService);
                }
            }
            return res;
        } catch (DdErrorException var9) {
            exceptionHandler.handle(var9);
            return null;
        }
    }

	public DdOapiMessageRouter next() {
		this.reEnter = true;
		return this.end();
	}

    protected boolean test(DdOapiMessage ddMessage) {
        return (this.eventType == null || this.eventType.equalsIgnoreCase(ddMessage.getEventType())) && (this.msgType == null || this.msgType.equalsIgnoreCase(ddMessage.getMsgType()));
    }

    public DdOapiMessageRouter getRouterBuilder() {
        return routerBuilder;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<DdOapiMessageHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<DdOapiMessageHandler> handlers) {
        this.handlers = handlers;
    }
}
