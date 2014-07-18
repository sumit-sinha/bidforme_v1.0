package com.amadeus.bid.be.base;

public abstract interface Encoder {
  public abstract Object encode(Object paramObject)
    throws EncoderException;
}