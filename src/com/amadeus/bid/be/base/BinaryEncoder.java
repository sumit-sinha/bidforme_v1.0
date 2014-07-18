package com.amadeus.bid.be.base;

public abstract interface BinaryEncoder extends Encoder {
  public abstract byte[] encode(byte[] paramArrayOfByte)
    throws EncoderException;
}