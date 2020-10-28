package space.banka.jiffy.webapi.dto;

import lombok.*;

import java.time.Instant;

@Getter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // for @Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for deserialization
public class Document {

    String name;

    long sizeBytes;

    Instant latestModificationTime;
}
