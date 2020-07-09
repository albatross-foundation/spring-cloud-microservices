package com.instagram.news.adapter;

import java.util.List;
import java.util.Map;

public interface AuthAdapter {
    public String getAccessToken();
    public Map<String, String> usersProfilePic(String token, List<String> usernames);
}
