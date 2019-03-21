package io.corbs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("todos.api")
public class TodosTaskProperties {

    /**
     * full http endpoint
     * --todos.api.endpoint=http://some.todos.api
     */
    private String endpoint;

    /**
     * limit number of objects generated
     * --todos.api.limit=1000
     */
    private Integer limit;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
