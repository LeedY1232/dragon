package com.dragon.common.base.config;

/**
 * @author Li Dongyang
 * @date 2022/9/24 21:55
 */

public class BaseResourceProperties {
    public static final String PREFIX = "dragon.resources";

    protected Database dbMaster;

    public void setDbMaster(Database dbMaster) {
        this.dbMaster = dbMaster;
    }

    public static class Database {
        private String url;
        private String name;
        private String username;
        private String password;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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
}
