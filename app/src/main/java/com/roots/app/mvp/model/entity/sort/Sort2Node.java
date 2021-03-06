package com.roots.app.mvp.model.entity.sort;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Sort2Node.java
 * @Description TODO
 * @createTime 2020年08月29日 13:08:00
 */
@Setter
@Getter
public class Sort2Node extends BaseExpandNode {

    private List<BaseNode> childNode;
    private String title;
    private int id;


    public Sort2Node(List<BaseNode> childNode, String title, int id) {
        this.childNode = childNode;
        this.title = title;
        this.id = id;
        setExpanded(false);
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }
}
