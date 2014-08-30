package com.zhadan.golovach.lesson12.batcher;

import java.util.List;

/**
 * Created by andrewzhadan on 8/19/14.
 */
public interface BatchProcessor<ARG, RES> {
    List<RES> onBatch(List<ARG> argList);
}
