package bo.zhao.practice.proxy.service.impl;

import bo.zhao.practice.proxy.service.CountService;

public class CountServiceImpl implements CountService {

    private int count;

    @Override
    public int count() {
        return count++;
    }
}
