package it.polimi.telcowebconsumer.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public enum Message {

    REG_OK("User registered successfully", 1, false),
    REG_FAIL("There was an error during the registration", 2),
    LOGIN_FAIL("The username or password is wrong", 3),
    USER_NOT_FOUND("User not found", 4),
    FORBIDDEN("User not allowed", 5),
    USER_EXIST("The username or email is already used", 6);

    private final String message;
    private final int code;
    private final boolean error;

    Message(String message, int code, boolean error){
        this.message = message;
        this.code = code;
        this.error = error;
    }

    Message(String message, int code){
        this(message, code, true);
    }

    public String getMessage() {
        return message;
    }

    public boolean isError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public static Message fromCode(int messageCode) {
        for (Message message : Message.values()) {
            if(message.code == messageCode) return message;
        }
        return null;
    }

    public static Optional<Message> fromRequest(HttpServletRequest req, String paramName){
        try{
            int messageCode = Integer.parseInt(req.getParameter(paramName));
            return Optional.ofNullable(Message.fromCode(messageCode));
        }
        catch (NumberFormatException e){
            return Optional.empty();
        }
    }

    public static Optional<Message> fromRequest(HttpServletRequest req){
        return fromRequest(req, "m");
    }
}
