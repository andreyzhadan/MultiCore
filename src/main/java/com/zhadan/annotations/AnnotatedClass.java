package com.zhadan.annotations;

/**
 * Created by andrewzhadan on 5/1/14.
 */
@History(versions = {
    @Version(version = 1, date = @Date(day = 2, month = 3, year = 2013)),
    @Version(version = 2, date = @Date(day = 3, month = 4, year = 2013),
            author = @Author(name = "petr", title = JobTitle.UNKNOWN),
            helpers = {@Author(name = "vova", title = JobTitle.MIDDLE), @Author()}),
    @Version(version = 3, date = @Date(day = 4, month = 5, year = 2013),
            author = @Author(name = "vova", title = JobTitle.MIDDLE))
})
public class AnnotatedClass {
}
