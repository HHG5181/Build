package com.roots.app.mvp.ui.adapter.sort;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.roots.app.mvp.model.entity.sort.Sort1Node;
import com.roots.app.mvp.model.entity.sort.Sort2Node;
import com.roots.app.mvp.model.entity.sort.Sort3Node;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SortTreeAdapter.java
 * @Description TODO
 * @createTime 2020年08月29日 13:09:00
 */
public class SortTreeAdapter extends BaseNodeAdapter {

    public SortTreeAdapter() {
        super();
        addNodeProvider(new Sort1Provider());
        addNodeProvider(new Sort2Provider());
        addNodeProvider(new Sort3Provider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode node = list.get(i);
        if (node instanceof Sort1Node) {
            return 1;
        } else if (node instanceof Sort2Node) {
            return 2;
        } else if (node instanceof Sort3Node) {
            return 3;
        }
        return -1;
    }

    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;

}
