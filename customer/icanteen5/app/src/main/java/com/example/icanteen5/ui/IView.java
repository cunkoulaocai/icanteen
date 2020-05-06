package com.sx.takeaway.ui;

/*
 * @Description 所有界面都需要实现该接口
 */

public interface IView {
    void success(Object o);
    void failed(String msg);
}
