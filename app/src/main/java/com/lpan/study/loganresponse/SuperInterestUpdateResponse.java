package com.lpan.study.loganresponse;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.lpan.study.model.SuperInnteresInfo;

/**
 * Created by lpan on 2017/11/17.
 */
@JsonObject
public class SuperInterestUpdateResponse {

    @JsonField
    BaseListResponse<SuperInnteresInfo> superInterest;

    public BaseListResponse<SuperInnteresInfo> getSuperInterest() {
        return superInterest;
    }

    public void setSuperInterest(BaseListResponse<SuperInnteresInfo> superInterest) {
        this.superInterest = superInterest;
    }

    @Override
    public String toString() {
        return "SuperInterestUpdateResponse{" +
                "superInterest=" + superInterest +
                '}';
    }
}
