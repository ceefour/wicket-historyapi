package com.hendyirawan.wickethistory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

/**
 *
 * @author ceefour
 */
public class Facet implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String categoryId;
	private final Set<String> sizeQNames = new TreeSet<>();

	public Facet(PageParameters params) {
		super();
		this.categoryId = params.get("categoryId").toOptionalString();
		this.sizeQNames.addAll( ImmutableSet.copyOf(Splitter.on(',').omitEmptyStrings().split(params.get("sizes").toString(""))) );
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	public Set<String> getSizeQNames() {
		return sizeQNames;
	}
	
	public PageParameters toPageParameters() {
		return new PageParameters().set("categoryId", categoryId)
				.set("sizes", Strings.emptyToNull(Joiner.on(",").join(sizeQNames)));
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Facet ["
				+ (categoryId != null ? "categoryId=" + categoryId + ", " : "")
				+ (sizeQNames != null ? "sizeQNames="
						+ toString(sizeQNames, maxLen) : "") + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext()
				&& i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

}
