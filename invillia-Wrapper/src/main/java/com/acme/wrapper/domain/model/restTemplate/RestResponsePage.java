package com.acme.wrapper.domain.model.restTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author jose.diegues
 * @param <T>
 */
public class RestResponsePage<T> implements Serializable {

    private List<T> content;
    private long total;

    public RestResponsePage() {
    }

    public RestResponsePage(List<T> content, Pageable pageable, long total) {
        this.content = content;
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.content);
        hash = 97 * hash + (int) (this.total ^ (this.total >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RestResponsePage<?> other = (RestResponsePage<?>) obj;
        if (this.total != other.total) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "RestResponsePage{" + "content=" + content + ", total=" + total + '}';
    }

}
