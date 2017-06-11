package pl.naniewicz.boilerplate.feature.counter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import pl.naniewicz.boilerplate.commons.RxTransformers;
import pl.naniewicz.boilerplate.commons.injection.scope.ConfigPersistent;
import pl.naniewicz.boilerplate.feature.base.BasePresenter;
import pl.naniewicz.boilerplate.feature.base.DataState;
import timber.log.Timber;

@ConfigPersistent
public class CounterPresenter extends BasePresenter<CounterView> {

    private final DataState<Long> numberDataState;
    private Disposable counterDisposable;

    @Inject
    CounterPresenter(DataState<Long> numberDataState) {
        this.numberDataState = numberDataState;
    }

    @Override
    public void onPermanentDetach() {
        super.onPermanentDetach();
        if (counterDisposable != null) {
            counterDisposable.dispose();
        }
    }

    public void initializeCounting() {
        if (numberDataState.hasData()) {
            getMvpView().showNumber(numberDataState.getData());
        }
        if (counterDisposable == null || counterDisposable.isDisposed()) {
            counterDisposable = Flowable.intervalRange(0, Long.MAX_VALUE, 0, 1, TimeUnit.MILLISECONDS)
                    .onBackpressureDrop()
                    .compose(RxTransformers.applyIoSchedulers())
                    .subscribe(
                            this::handleNumber,
                            Timber::e
                    );
        }
    }

    private void handleNumber(long number) {
        numberDataState.setData(number);
        if (getMvpView() != null) {
            getMvpView().showNumber(number);
        }
    }
}
