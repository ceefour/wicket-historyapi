package com.hendyirawan.wickethistory;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import com.google.common.collect.ImmutableList;

/**
 * https://github.com/browserstate/history.js
 * @author ceefour
 */
public class JqueryHistoryJavaScriptReference extends
		JavaScriptResourceReference {
	
	private static final long serialVersionUID = 1L;
	
	public static JqueryHistoryJavaScriptReference INSTANCE =
			new JqueryHistoryJavaScriptReference();

	public JqueryHistoryJavaScriptReference() {
		super(JqueryHistoryJavaScriptReference.class, "jquery.history.js");
	}
	
	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		return ImmutableList.of(JavaScriptHeaderItem.forReference(JQueryResourceReference.get()));
	}

}
