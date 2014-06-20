package com.zhadan.guidelines;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by andrewzhadan on 6/8/14.
 */
// Immutable Helper
public final class ImmutableObj {
    private final int n;

    public ImmutableObj(int n) {
        this.n = n;
    }
}

//Mutable Foo || immutable
final class Foo {
    //mutable helper
    public ImmutableObj getImmutableObj() {
        return immutableObjRef.get();
    }

    public void setImmutableObj(int num) {
        immutableObjRef.set(new ImmutableObj(num));
    }

    private final AtomicReference<ImmutableObj> immutableObjRef = new AtomicReference<>();

}
