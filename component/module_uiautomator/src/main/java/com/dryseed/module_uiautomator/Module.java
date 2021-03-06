package com.dryseed.module_uiautomator;

import android.os.Message;
import com.easy.moduler.lib.Constants;
import com.easy.moduler.lib.okbus.BaseModule;
import com.easy.moduler.lib.okbus.CallBack;
import com.easy.moduler.lib.okbus.IModule;
import com.easy.moduler.lib.okbus.ServiceBus;
import com.google.auto.service.AutoService;

@AutoService(IModule.class)
public class Module extends BaseModule {

    @Override
    public void afterConnected() {
        ServiceBus.getInstance().registerService(Constants.SERVICE_UIAUTOMATOR_UID, new CallBack<String>() {
            @Override
            public String onCall(Message msg) {
                return "MODULE_VIEW";
            }
        });
    }

    @Override
    public int getModuleId() {
        return Constants.MODULE_UIAUTOMATOR;
    }

}
