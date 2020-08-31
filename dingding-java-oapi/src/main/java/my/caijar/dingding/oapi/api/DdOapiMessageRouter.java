package my.caijar.dingding.oapi.api;

import my.caijar.dingding.common.api.DdErrorExceptionHandler;
import my.caijar.dingding.common.utils.LogExceptionHandler;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/27 23:40
 * @Version 1.0
 */

public class DdOapiMessageRouter  {

    private static final int DEFAULT_THREAD_POOL_SIZE = 100;
    protected final Logger log = LoggerFactory.getLogger(DdOapiMessageRouter.class);
    private final List<DdOapiMessageRouterRule> rules = new ArrayList();
    private final DdOapiService ddOapiService;
    private ExecutorService executorService;
    private DdErrorExceptionHandler exceptionHandler;

    public DdOapiMessageRouter(DdOapiService ddOapiService) {
        this.ddOapiService = ddOapiService;
        this.executorService = Executors.newFixedThreadPool(10);
        this.exceptionHandler = new LogExceptionHandler();
    }

    public DdOapiMessageRouter(DdOapiService ddOapiService, ExecutorService executorService) {
        this.ddOapiService = ddOapiService;
        this.executorService = executorService;
        this.exceptionHandler = new LogExceptionHandler();
    }

    public void shutDownExecutorService() {
        this.executorService.shutdown();
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public void setExceptionHandler(DdErrorExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
    List<DdOapiMessageRouterRule> getRules() {
        return this.rules;
    }

    public DdOapiMessageRouterRule rule() {
        return new DdOapiMessageRouterRule(this);
    }

    public DdOapiOutMessage route(DdOapiMessage ddMessage, Map<String, Object> context) {
        return this.route(ddMessage, context, (DdOapiService)null);
    }

    public DdOapiOutMessage route(final DdOapiMessage ddMessage, final Map<String, Object> context, DdOapiService ddOapiService) {
        if (ddOapiService == null) {
            ddOapiService = this.ddOapiService;
        }

        final DdOapiService mpService = ddOapiService;
        List<DdOapiMessageRouterRule> matchRules = new ArrayList();
        Iterator var6 = this.rules.iterator();

        while(var6.hasNext()) {
            DdOapiMessageRouterRule rule = (DdOapiMessageRouterRule)var6.next();
            if (rule.test(ddMessage)) {
                matchRules.add(rule);
            }
        }

        if (matchRules.size() == 0) {
            return null;
        } else {
            DdOapiOutMessage res = null;
            final List<Future<?>> futures = new ArrayList();
            Iterator var8 = matchRules.iterator();

            while(var8.hasNext()) {
                final DdOapiMessageRouterRule rule = (DdOapiMessageRouterRule)var8.next();
                if (rule.isAsync()) {
                    futures.add(this.executorService.submit(new Runnable() {
                        public void run() {
                            rule.service(ddMessage, context, mpService, DdOapiMessageRouter.this.exceptionHandler);
                        }
                    }));
                } else {
                    res = rule.service(ddMessage, context, mpService, this.exceptionHandler);
                }
            }

            if (futures.size() > 0) {
                this.executorService.submit(new Runnable() {
                    public void run() {
                        Iterator var1 = futures.iterator();

                        while(var1.hasNext()) {
                            Future future = (Future)var1.next();

                            try {
                                future.get();
                                DdOapiMessageRouter.this.log.debug("End session access: async=true, sessionId={}", ddMessage.getMsgType());
                            } catch (InterruptedException var4) {
                                DdOapiMessageRouter.this.log.error("Error happened when wait task finish", var4);
                                Thread.currentThread().interrupt();
                            } catch (ExecutionException var5) {
                                DdOapiMessageRouter.this.log.error("Error happened when wait task finish", var5);
                            }
                        }

                    }
                });
            }

            return res;
        }
    }

    public DdOapiOutMessage route(DdOapiMessage ddMessage) {
        return this.route(ddMessage, new HashMap(2));
    }
}
