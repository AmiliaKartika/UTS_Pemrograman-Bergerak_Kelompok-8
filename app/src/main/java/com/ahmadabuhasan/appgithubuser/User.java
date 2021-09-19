package com.ahmadabuhasan.appgithubuser;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    int avatar;
    String username;
    String name;
    String location;
    String repository;
    String company;
    int followers;
    int following;

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    User() {
    }

    protected User(Parcel in) {
        avatar = in.readInt();
        username = in.readString();
        name = in.readString();
        location = in.readString();
        repository = in.readString();
        company = in.readString();
        followers = in.readInt();
        following = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(avatar);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(repository);
        dest.writeString(company);
        dest.writeInt(followers);
        dest.writeInt(following);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
