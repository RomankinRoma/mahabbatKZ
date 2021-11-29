package kz.reself.chatservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageState {
    Read("Read"),
    ReadAll("ReadAll"),
    Send("Send"),
    Destroy("Destroy");

    private final String name;

    public String getName() {
        return name;
    }
}
