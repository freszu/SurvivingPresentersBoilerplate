package pl.naniewicz.boilerplate.feature.counter;

import pl.naniewicz.boilerplate.feature.base.MvpView;

interface CounterView extends MvpView {

    void showNumber(long number);
}
