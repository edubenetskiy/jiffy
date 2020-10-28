package space.banka.jiffy.webservice.configuration;

import space.banka.jiffy.api.AnswerRepository;
import space.banka.jiffy.api.QueryFactory;
import space.banka.jiffy.impl.jsonsurfer.JsonSurferQueryFactory;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class QueryFactoryConfiguration {

    @Produces
    QueryFactory produceQueryFactory(AnswerRepository answerRepository) {
        return new JsonSurferQueryFactory(answerRepository);
    }
}
