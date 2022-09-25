package com.dragon.server.manager.model;

import java.util.Objects;

import com.dragon.common.base.db.field.Gender;
import com.dragon.common.base.db.field.PunishCardCategory;
import com.dragon.common.base.db.field.PunishCardLevel;

/**
 * @author Li Dongyang
 * @date 2022/9/25 18:14
 */
public class CardIndexKey {
    private PunishCardLevel level;
    private PunishCardCategory category;
    private Gender gender;

    public CardIndexKey() {
    }

    public CardIndexKey(PunishCardLevel level, PunishCardCategory category, Gender gender) {
        this.level = level;
        this.category = category;
        this.gender = gender;
    }

    public PunishCardLevel getLevel() {
        return level;
    }

    public void setLevel(PunishCardLevel level) {
        this.level = level;
    }

    public PunishCardCategory getCategory() {
        return category;
    }

    public void setCategory(PunishCardCategory category) {
        this.category = category;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "CardIndexKey{" +
                "level=" + level +
                ", category=" + category +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardIndexKey indexKey = (CardIndexKey) o;

        if (level != indexKey.level) return false;
        if (category != indexKey.category) return false;
        return gender == indexKey.gender;
    }

    @Override
    public int hashCode() {
        int result = level != null ? level.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }
}
