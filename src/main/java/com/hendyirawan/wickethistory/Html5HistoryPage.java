package com.hendyirawan.wickethistory;

import java.net.URI;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author ceefour
 */
@MountPath("html5/${categoryId}")
public class Html5HistoryPage extends WebPage {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory
			.getLogger(Html5HistoryPage.class);
	
	private final WebMarkupContainer data;

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(JqueryHistoryJavaScriptReference.INSTANCE));
	}
	
	/**
	 * @param parameters
	 */
	public Html5HistoryPage(PageParameters parameters) {
		super(parameters);
		final Model<Facet> facetModel = new Model<>(new Facet(getPageParameters()));
		
		data = new WebMarkupContainer("data");
		data.setMarkupId("data"); // ensure stateless AJAX render works
		data.setOutputMarkupId(true);
		data.add(new Label("categoryId", new PropertyModel<>(facetModel, "categoryId")));
		data.add(new Label("sizeQNames", new PropertyModel<>(facetModel, "sizeQNames")));
		add(data);
		
		final Html5FacetForm facetForm = new Html5FacetForm("facetForm", facetModel) {
			@Override
			protected void onFacetChanged(AjaxRequestTarget target) {
				super.onFacetChanged(target);
				final PageParameters newParams = facetModel.getObject().toPageParameters();
				// RequestUtils.toAbsolutePath() is not working properly because not taking @MountPath into account
				final URI newUriParts = URI.create(getRequestCycle().getUrlRenderer().renderFullUrl(Url.parse(urlFor(getPageClass(), newParams))));
				final String newUri = newUriParts.getPath() + (newUriParts.getQuery() != null ? "?" + newUriParts.getQuery() : "");
				log.info("New URI: {}", newUri);
				if (target != null) {
					// http://wicketinaction.com/2013/02/replace-components-with-animation/
					data.add(new AttributeAppender("style", new Model<>("display: none"), ";") {
						@Override
						public boolean isTemporary(org.apache.wicket.Component component) {
							return true;
						};
					});
					target.prependJavaScript("doReplace|$('#" + data.getMarkupId() + "').fadeOut(250, doReplace);");
					target.appendJavaScript("History.pushState({newUri: '" + newUri + "'}, '" + newUri + "', '" + newUri + "');");
					target.add(data, this);
					target.appendJavaScript("$('#" + data.getMarkupId() + "').fadeIn(400);");
				} else {
					setResponsePage(getPageClass(), newParams);
				}
			}
		};
		facetForm.setMarkupId("facetForm");
		facetForm.setOutputMarkupId(true);
		add(facetForm);
	}
	
}
