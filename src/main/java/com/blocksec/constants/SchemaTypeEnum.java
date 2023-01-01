package com.blocksec.constants;


public enum SchemaTypeEnum {
    OBJECT("object"),
    ARRAY("array"),
    BOOLEAN("boolean"),
    NUMBER("number"),
    STRING("string"),
    INTEGER("integer");

    private final String value;

    SchemaTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
