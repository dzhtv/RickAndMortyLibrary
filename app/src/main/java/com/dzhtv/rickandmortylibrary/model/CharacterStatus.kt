package com.dzhtv.rickandmortylibrary.model

enum class CharacterStatus(serializeStatus: String) {
    ALIVE("alive"), DEAD("dead"), UNKNOWN("unknown")
}

enum class CharacterGender(serializeGender: String) {
    FEMALE("female"), MALE("male"), GENDERLESS("genderless"), UNKNOWN("unknown")
}