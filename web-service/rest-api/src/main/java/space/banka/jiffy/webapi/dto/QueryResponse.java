package space.banka.jiffy.webapi.dto;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // for @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for deserialization
public class QueryResponse {

    String filePath;
}
