package com.roots.app.mvp.model.entity.index;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlBean implements Serializable {

    private String title;
    private String token;
    private String value;
}
