package com.example;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Price  {
    
    public long value;

    public Price setPrice(long v){
        value = v;
        return this;
    }
}
