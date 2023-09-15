package com.slowly.lookup.parser;

import com.slowly.lookup.model.Condition;
import org.json.JSONException;
import org.json.JSONObject;

public class ConditionParser {
    public static Condition parseConditionFromJSONObject(JSONObject jsonObject) throws JSONException {
        Condition condition = new Condition();

        condition.setText(jsonObject.getString("text"));
        condition.setIcon(jsonObject.getString("icon"));
        condition.setCode(jsonObject.getInt("code"));

        return condition;
    }
}
