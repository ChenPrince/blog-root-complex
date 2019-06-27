package com.zerodg.vwentity.dto.DL01LoginInputDTO;

public class DL01LoginInputDTO {
    private String username;
    private String password;

    public DL01LoginInputDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
