package com.jianfei.d.common.vo.es.hit;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hits {
    
    private List<Hit> hits;
    
    private int total;
    
}
