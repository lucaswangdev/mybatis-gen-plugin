package com.lucaswangdev.params.ext;

import com.lucaswangdev.params.UserParam;
import org.apache.ibatis.type.Alias;

@Alias("UserParamExt")
public class UserParamExt extends UserParam {

    private static final long serialVersionUID = 1L;

    private String extendField;


    public String getExtendField() {
        return extendField;
    }

    public void setExtendField(String extendField) {
        this.extendField = extendField;
    }
}