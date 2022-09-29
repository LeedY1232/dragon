package com.dragon.server.service;

import com.dragon.server.model.Counter;

import java.util.Optional;

public interface CounterService {

  Optional<Counter> getCounter(Integer id);

  void upsertCount(Counter counter);

  void clearCount(Integer id);
}
