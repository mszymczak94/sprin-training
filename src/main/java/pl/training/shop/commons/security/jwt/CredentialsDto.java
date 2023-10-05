package pl.training.shop.commons.security.jwt;

import lombok.Data;

@Data
public class CredentialsDto {

    private String login;
    private String password;

}
