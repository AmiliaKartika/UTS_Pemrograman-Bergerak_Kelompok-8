package com.ahmadabuhasan.appgithubuser;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private Integer avatar;
    private String name;
    private String username;
    private String location;
    private String repository;
    private String company;
    private String followers;

    public Integer getAvatar() {
        return avatar;
    }

    public void setAvatar(Integer avatar) {
        this.avatar = avatar;
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

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    private String following;

    public User() {

    }

    protected User(Parcel in) {
        if (in.readByte() == 0) {
            avatar = null;
        } else {
            avatar = in.readInt();
        }
        name = in.readString();
        username = in.readString();
        location = in.readString();
        repository = in.readString();
        company = in.readString();
        followers = in.readString();
        following = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (avatar == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(avatar);
        }
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(location);
        dest.writeString(repository);
        dest.writeString(company);
        dest.writeString(followers);
        dest.writeString(following);
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