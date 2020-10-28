package space.banka.jiffy.impl.jsonsurfer;

import org.jsfr.json.JacksonParser;
import org.jsfr.json.JsonSurfer;
import org.jsfr.json.compiler.JsonPathCompiler;
import org.jsfr.json.path.JsonPath;
import org.jsfr.json.provider.JacksonProvider;
import space.banka.jiffy.api.AnswerRepository;
import space.banka.jiffy.api.Query;
import space.banka.jiffy.api.QueryFactory;
import space.banka.jiffy.api.exceptions.InvalidQueryException;

public class JsonSurferQueryFactory implements QueryFactory {

    private static final JsonSurfer JSON_SURFER =
            new JsonSurfer(JacksonParser.INSTANCE, JacksonProvider.INSTANCE);

    private final AnswerRepository answerRepository;

    public JsonSurferQueryFactory(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @Override
    public Query createQuery(String queryExpression) {
        try {
            JsonPath compiledQuery = JsonPathCompiler.compile(queryExpression);
            return new JsonSurferQuery(answerRepository, compiledQuery, JSON_SURFER);
        } catch (Exception exception) {
            throw new InvalidQueryException(queryExpression, exception);
        }
    }
}
