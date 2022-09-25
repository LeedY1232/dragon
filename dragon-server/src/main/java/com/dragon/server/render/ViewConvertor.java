package com.dragon.server.render;

import com.dragon.common.base.db.entity.PunishCard;
import com.dragon.server.web.view.PunishCardView;

/**
 * @author Li Dongyang
 * @date 2022/9/25 20:27
 */
public class ViewConvertor {
    public static PunishCardView convertToPunishCardView(PunishCard item){
        if(item!=null){
            PunishCardView view = new PunishCardView();
            view.setId(item.getId());
            view.setCategory(item.getCategory());
            view.setGender(item.getGender());
            view.setContent(item.getContent());
            view.setLevel(item.getLevel());
            return view;
        }
        return null;
    }
}
