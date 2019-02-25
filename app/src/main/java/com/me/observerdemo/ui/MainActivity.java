package com.me.observerdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.me.observerdemo.R;

import entity.Boy;
import entity.Girl;
import entity.Npc;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private static final String TAG ="MainActivity";

	private Npc npc;
	private Boy boy;
	private Girl girl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		findViewById(R.id.button).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
		initOriginObserver();
	}

	private void initOriginObserver() {
		npc = new Npc("npc");
		girl = new Girl("girl");
		boy = new Boy("boy");
		npc.addObserver(boy);
		npc.addObserver(girl);
	}

	private void notifyObserver() {
		System.out.println("-=onclick=-");
		System.out.println("npc has changed "+npc.hasChanged());
		npc.nativeNotify();
		npc.notifyObservers("msg");
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.button:
				notifyObserver();
				break;
			case R.id.button2:
//				logRx();
//				rx();
				performAction();
//				performSubscribe();
				break;
			case R.id.button3:
//				goKtActivity();
				break;
				default:break;
		}
	}

	private void goKtActivity() {
		KtActivity.startActivity(this);
	}

	private void performSubscribe() {

//		Subscriber subscriber =

	}

	private void performAction() {
		Observable.create((emitter)->{
			emitter.onNext("start");
			emitter.onNext("step1");
			emitter.onComplete();
			emitter.onNext("step2");
//			emitter.onError(new Throwable());
		})
				.subscribeOn(Schedulers.io())//指定 subscribe()发生在io线程 订阅
				.observeOn(AndroidSchedulers.mainThread())//指定回调在主线程 观察
				.subscribe(new Observer<Object>() {
					@Override
					public void onSubscribe(Disposable d) {}
					@Override
					public void onNext(Object o) {
						Log.d("output => ",o.toString());
					}
					@Override
					public void onError(Throwable e) {
						Log.d("has receive a throwable","");
					}
					@Override
					public void onComplete() {
						Log.d("output +","=> completed");
					}
				});
	}

	private void rx() {
		Log.d(TAG,"NM$L");
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				Log.d(TAG,String.format("onSubscribe disposable %s",d.isDisposed()));
			}

			@Override
			public void onNext(String s) {
				Log.d(TAG,String.format("onNext s ==> %s",s));
			}

			@Override
			public void onError(Throwable e) {
				Log.d(TAG,String.format("onError e => %s",e.getMessage()));
			}

			@Override
			public void onComplete() {
				Log.d(TAG,"onComplete");
			}
		};

		Observable observable = Observable.create(new ObservableOnSubscribe(){
			@Override
			public void subscribe(ObservableEmitter emitter) throws Exception {
				emitter.onNext("hello");
				emitter.onNext("world");
				emitter.onComplete();
			}
		});




		observable.subscribe(observer);

	}

	private void logRx() {
		Flowable.just("hello world").subscribe(System.out::println);
		Flowable.just("boy's name").subscribe(Boy::setBoy);
		// 无序
		Flowable.range(1,10)
				.flatMap(v->
					Flowable.just(v).subscribeOn(Schedulers.computation()).map(w -> w*w)
				).blockingSubscribe(System.out::println);
		System.out.println("--------------------------");
		// 无序
		Flowable.range(1,10)
				.parallel()
				.runOn(Schedulers.computation())
				.map(v->v*v)
				.sequential()
				.blockingSubscribe(System.out::println);


	}
}
