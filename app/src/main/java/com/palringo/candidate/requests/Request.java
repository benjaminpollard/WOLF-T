package com.palringo.candidate.requests;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;


abstract public class Request {

    private static final String KEY_BODY = "body";
    private static final String KEY_NAME = "name";

    /**
     * Returns the name/command of this request.
     * @return Name/command of the request.
     */
    abstract public String getName();


    /**
     * Returns the content of this request.
     * @return {@link JSONObject} content of the request.
     * @throws JSONException if {@link #getRequestBody()} throws
     */
    public final JSONObject asJson() throws JSONException {
        JSONObject requestContent = new JSONObject();
        requestContent.putOpt(KEY_NAME, getName());
        requestContent.putOpt(KEY_BODY, getRequestBody());
        return requestContent;
    }

    /**
     * Returns the body of this request.
     * @return {@link JSONObject} body of the request.
     * @throws JSONException to be handled on upper level
     */
    abstract protected JSONObject getRequestBody() throws JSONException;

    /**
     * Made abstract to make sure child classes implement it given we use them as keys in maps.
     */
    public abstract boolean equals(Object o);

    /**
     * Made abstract to make sure child classes implement it given we use them as keys in maps.
     */
    public abstract int hashCode();

    @NonNull
    @Override
    public String toString() {
        return "Request(" + getName() + ")";
    }
}

