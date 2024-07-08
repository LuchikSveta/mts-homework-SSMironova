package ru.mts.siebel.springsecurity.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.mts.siebel.springsecurity.enumerated.Role;

@Data
public class UserCreateForm {

    @NotEmpty
    private String email = "";
    @NotEmpty
    private String password = "";
    @NotEmpty
    private String passwordRepeated = "";
    @NotNull
    private Role role = Role.USER;

    @Override
    public String toString() {
        return "UserCreateForm{" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                ", role=" + role +
                '}';
    }

}
