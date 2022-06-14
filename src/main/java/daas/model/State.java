package daas.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum State {
    @JsonEnumDefaultValue
    IDLE,
    LOADING,
    LOADED,
    DELIVERING,
    DELIVERED,
    RETURNING
}
