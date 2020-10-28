package space.banka.jiffy.api;

public interface AnswerRepository {

    boolean hasAnswerById(String id);

    Answer findAnswerById(String id);

    WriteableAnswer create();

    Answer commit(WriteableAnswer answer);
}
