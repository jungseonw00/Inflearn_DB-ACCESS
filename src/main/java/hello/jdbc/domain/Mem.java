package hello.jdbc.domain;

import lombok.Data;

@Data
public class Mem {

    private String memberId;
    private int money;

    public Mem() {
    }

    public Mem(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}