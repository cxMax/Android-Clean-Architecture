//package com.cxmax.android_clean_architecture.component.rx;
//
//import com.trello.rxlifecycle.LifecycleTransformer;
//import com.trello.rxlifecycle.RxLifecycle;
//import com.trello.rxlifecycle.android.ActivityEvent;
//import com.trello.rxlifecycle.android.FragmentEvent;
//
//import java.util.concurrent.Callable;
//import java.util.concurrent.TimeUnit;
//
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.functions.Action1;
//import rx.functions.Func1;
//import rx.schedulers.Schedulers;
//import rx.subscriptions.CompositeSubscription;
//
///**
// * @describe :
// * @usage :
// * <p>
// * <p>
// * Created by cxmax on 2017/6/15.
// */
//
//public class UiOptimizer {
//    private CompositeSubscription cs = new CompositeSubscription();
//    private LifecycleTransformer dieWithUi;
//
//    public UiOptimizer(BaseActivity baseActivity) {
//        dieWithUi = RxLifecycle.
//                bindUntilEvent(baseActivity.lifecycle(), ActivityEvent.DESTROY);
//    }
//
//    public UiOptimizer(BaseFragment baseFragment) {
//        dieWithUi = RxLifecycle.<Integer, FragmentEvent>
//                bindUntilEvent(baseFragment.lifecycle(), FragmentEvent.DETACH);
//    }
//
//    public void quitNow() {
//        if (!cs.isUnsubscribed() ) {
//            cs.unsubscribe();
//        }
//    }
//
//    public void runWhenUiIdle(final Runnable runnable) {
//        cs.add(BaseActivityUiLinker.uiIdleObservable()
//                .compose(dieWithUi)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        runnable.run();
//                    }
//                }));
//    }
//
//    public void runOnUi(final Runnable runnable) {
//        cs.add(Observable.just(1)
//                .compose(dieWithUi)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        runnable.run();
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//                        //do nothing
//                    }
//                }));
//    }
//
//    public void postDelay(final Runnable runnable, long milliseconds) {
//        cs.add(Observable.timer(milliseconds, TimeUnit.MILLISECONDS)
//                .map(new Func1<Long, Integer>() {
//                    @Override
//                    public Integer call(Long aLong) {
//                        return 1;
//                    }
//                }).subscribeOn(Schedulers.immediate())
//                .compose(dieWithUi)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        runnable.run();
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) {
//
//                    }
//                }));
//    }
//
//    public Observable<Integer> async(final Runnable runnable) {
//        return Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                cs.add(subscriber);
//                runnable.run();
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io())
//                .compose(dieWithUi)
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//
//    public <V> Observable<V> async(final Callable<V> callable) {
//        return Observable.create(new Observable.OnSubscribe<V>() {
//            @Override
//            public void call(Subscriber<? super V> subscriber) {
//                cs.add(subscriber);
//                try {
//                    subscriber.onNext(callable.call() );
//                    subscriber.onCompleted();
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                }
//            }
//        }).subscribeOn(Schedulers.io())
//                .compose(dieWithUi)
//                .observeOn(AndroidSchedulers.mainThread());
//    }
//}
