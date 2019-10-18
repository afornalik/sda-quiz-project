package sda.quiz.service.implementation.exception;

public class MismatchIdException extends Exception {
    private  final  String s;
    public MismatchIdException(String s) {
        this.s = s ;
    }

    @Override
    public String getMessage() {
        return s;
    }
}
