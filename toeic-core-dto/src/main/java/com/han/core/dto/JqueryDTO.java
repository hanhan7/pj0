package com.han.core.dto;

import java.io.Serializable;

/**
 * Created by VO VAN NHAN on 6/21/2018.
 */
public class JqueryDTO implements Serializable  {
    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
