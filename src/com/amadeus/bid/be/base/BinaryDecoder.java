package com.amadeus.bid.be.base;

public abstract interface BinaryDecoder extends Decoder {
  public abstract byte[] decode(byte[] paramArrayOfByte)
    throws DecoderException;
}