package com.dinhtc.logistics.model;

import androidx.annotation.NonNull;

import com.dinhtc.logistics.common.widgets.tagview.interfaces.TagInterface;

import java.util.Locale;

public class SuggestionModel implements TagInterface {

    private final String avatar;
    private final int id;
    private final String name;

    private final String email;

    public SuggestionModel(String avatar, int id, @NonNull String name, String email) {
        this.avatar = avatar;
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @Override
    public String getTag() {
        return String.format(Locale.getDefault(), "@{{%d}}", id);
    }

    @Override
    public String getLabel() {
        return String.format(Locale.getDefault(), "@%s", name);
    }
}
