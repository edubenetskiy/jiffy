package space.banka.jiffy.webapi.dto;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // for @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for deserialization
public class QueryRequest {

    String documentTitle;

    String queryExpression;
}
