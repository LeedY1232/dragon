package com.dragon.server.web.response;

import java.util.List;

import com.dragon.common.base.db.entity.PunishCard;
import com.dragon.server.web.view.PunishCardView;

/**
 * @author Li Dongyang
 * @date 2022/9/25 20:25
 */
public class PunishCardResponseData {
    private List<PunishCardView> truthPenalty;
    private List<PunishCardView> challengePenalty;

    public List<PunishCardView> getTruthPenalty() {
        return truthPenalty;
    }

    public void setTruthPenalty(List<PunishCardView> truthPenalty) {
        this.truthPenalty = truthPenalty;
    }

    public List<PunishCardView> getChallengePenalty() {
        return challengePenalty;
    }

    public void setChallengePenalty(List<PunishCardView> challengePenalty) {
        this.challengePenalty = challengePenalty;
    }

    public static final class Builder{
        private List<PunishCardView> truthPenalty;
        private List<PunishCardView> challengePenalty;
        private Builder(){}
        public static Builder aPunishCardResponseData(){
            return new Builder();
        }

        public Builder setTruthPenalty(List<PunishCardView> truthPenalty) {
            this.truthPenalty = truthPenalty;
            return this;
        }

        public Builder setChallengePenalty(List<PunishCardView> challengePenalty) {
            this.challengePenalty = challengePenalty;
            return this;
        }

        public PunishCardResponseData build(){
            PunishCardResponseData data = new PunishCardResponseData();
            data.setChallengePenalty(challengePenalty);
            data.setTruthPenalty(truthPenalty);
            return data;
        }
    }
}
