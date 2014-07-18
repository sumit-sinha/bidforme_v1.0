package com.amadeus.bid.be.base;

public abstract interface Decoder {
  public abstract Object decode(Object paramObject)
    throws DecoderException;
}