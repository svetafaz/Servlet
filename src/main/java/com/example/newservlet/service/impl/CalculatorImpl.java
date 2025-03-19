package com.example.newservlet.service.impl;

import com.example.newservlet.service.Calculator;

public class CalculatorImpl implements Calculator {
    @Override
    public int sum(int a, int b) {
        return a+b;
    }

    @Override
    public int multiply(int a, int b) {
        return a-b;
    }

    @Override
    public int sub(int a, int b) {
        return a*b;
    }
}
