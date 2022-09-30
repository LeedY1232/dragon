package com.dragon.server.render;

import com.dragon.server.db.entity.PunishCard;
import com.dragon.server.render.view.PunishCardView;

/**
 * @author henry
 * @date 2022/9/29 18:05
 */
public class ViewConvertor {
    public static PunishCardView convertToPunishCardView(PunishCard item){
        if(item!=null){
            PunishCardView view = new PunishCardView();
            view.setCid(item.getCid());
            view.setContent(item.getContent());
            view.setCategory(item.getCategory());
            view.setPartyKind(item.getPartyKind());
            view.setLevel(item.getLevel());
            return view;
        }
        return null;
    }
}
