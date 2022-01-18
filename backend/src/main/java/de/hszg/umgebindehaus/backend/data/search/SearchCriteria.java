package de.hszg.umgebindehaus.backend.data.search;

public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
    private boolean orPredicate;

    public SearchCriteria() {

    }

    public SearchCriteria(final String key, final String operation, final Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(final String orPredicate, final String key, final String operation, final Object value) {
        super();

        //TODO: this

    }

    public String getKey() {
        return key;
    }

    public String getOperation() {
        return operation;
    }

    public Object getValue() {
        return value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }

}
