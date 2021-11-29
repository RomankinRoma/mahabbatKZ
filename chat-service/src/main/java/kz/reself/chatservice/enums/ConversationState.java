package kz.reself.chatservice.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConversationState {
    New("New"),
    Old("Old");

    private final String name;

    public String getName() {
        return name;
    }
}
