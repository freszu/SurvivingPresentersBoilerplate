package pl.naniewicz.boilerplate.feature.counter.fragment;

import pl.naniewicz.boilerplate.feature.base.MvpView;

interface CounterFragmentView extends MvpView {

    void showNumber(long number);
}
