package io.github.kst.client;

import lombok.Data;

@Data
public class Client {
    final public Integer id;
    final public String name;
    final public String surname;
    final public String email;
    final public Double latitude;
    final public Double longitude;
}
