package com.example.hongu.btl.data;

import com.example.hongu.btl.model.Effect;

import java.util.List;

public interface TransactionDatabase {
    void addEffect(Effect effect);
    List<Effect> getAllEffect();
    int updateEffect(Effect effect);
}
