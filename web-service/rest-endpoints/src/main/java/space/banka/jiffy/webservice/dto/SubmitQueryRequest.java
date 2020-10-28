package space.banka.jiffy.webservice.dto;

import lombok.Value;

@Value
public class SubmitQueryRequest {

    String documentTitle;

    String query;
}
