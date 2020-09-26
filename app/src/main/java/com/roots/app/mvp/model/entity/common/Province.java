package com.roots.app.mvp.model.entity.common;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Province implements Serializable {

    private int value;
    private String text;
}
