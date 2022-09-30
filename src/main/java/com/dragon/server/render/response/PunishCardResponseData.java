package com.dragon.server.render.response;

import java.util.List;

import com.dragon.server.render.view.PunishCardView;
import lombok.Data;

/**
 * @author henry
 * @date 2022/9/30 10:00
 */
@Data
public class PunishCardResponseData {
    private List<PunishCardView> truthPenalty;
    private List<PunishCardView> challengePenalty;
    public static class Builder{
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
            data.setTruthPenalty(truthPenalty);
            data.setChallengePenalty(challengePenalty);
            return data;
        }
    }
}
