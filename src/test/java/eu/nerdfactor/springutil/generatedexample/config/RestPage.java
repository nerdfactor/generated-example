package eu.nerdfactor.springutil.generatedexample.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable"})
public class RestPage<T> extends PageImpl<T> {

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public RestPage(@JsonProperty("content") List<T> content,
	                @JsonProperty("number") int page,
	                @JsonProperty("size") int size,
	                @JsonProperty("totalElements") long total) {
		super(content, Pageable.ofSize(Math.max(1, size)).withPage(page), total);
	}

	public RestPage(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

	public RestPage(List<T> content) {
		super(content);
	}

	public RestPage() {
		super(new ArrayList());
	}
}