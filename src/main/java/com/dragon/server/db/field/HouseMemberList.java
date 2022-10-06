package com.dragon.server.db.field;

import java.util.List;

import com.dragon.server.model.HouseMemberModel;
import lombok.Data;

/**
 * @author henry
 * @date 2022/10/6 14:39
 */
@Data
public class HouseMemberList {
    private List<HouseMemberModel> memberInfos;
}
