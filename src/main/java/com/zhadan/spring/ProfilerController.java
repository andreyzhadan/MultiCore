package com.zhadan.spring;

/**
 * Created by azhadan on 8/2/14.
 */
public class ProfilerController implements ProfilerControllerMBean {
    private boolean enabled = true;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
