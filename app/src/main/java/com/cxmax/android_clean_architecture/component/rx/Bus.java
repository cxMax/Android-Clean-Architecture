package com.cxmax.android_clean_architecture.component.rx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/6/15.
 */

public class Bus {
    private static final Bus INSTANCE = new Bus();

    private final Subject<Object, Object> busSubject = new SerializedSubject<>(PublishSubject.create());

    public static Bus get() {
        return INSTANCE;
    }

    public void post(Object event) {
        busSubject.onNext(event);
    }

    public <T> Observable<T> on(Class<T> eventClass) {
        return busSubject.ofType(eventClass);
    }

    public <T> Observable<T> onMainThread(Class<T> eventClass) {
        return on(eventClass)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Subject<Object, Object> getBusSubject() {
        return busSubject;
    }
}
