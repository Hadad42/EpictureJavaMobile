package com.java.demers_j.epicture.Entity;

class User {

    private String userName;
    private String password;
    private boolean connected;

    User(String userName, String password, Boolean connected) {
        this.userName = userName;
        this.password = password;
        this.connected = true;
    }

    String getUserName() {
        return userName;
    }

    String getPassword() {
        return password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}
