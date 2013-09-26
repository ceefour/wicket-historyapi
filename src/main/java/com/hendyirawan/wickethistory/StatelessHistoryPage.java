package com.hendyirawan.wickethistory;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.wicketstuff.annotation.mount.MountPath;

/**
 *
 * @author ceefour
 */
@MountPath("stateless/${categoryId}")
public class StatelessHistoryPage extends WebPage {

	private static final long serialVersionUID = 1L;
	
	private final WebMarkupContainer data;

	/**
	 * @param parameters
	 */
	public StatelessHistoryPage(PageParameters parameters) {
		super(parameters);
		final Model<Facet> facetModel = new Model<>(new Facet(getPageParameters()));
		
		data = new WebMarkupContainer("data");
		data.setMarkupId("data"); // ensure stateless AJAX render works
		data.setOutputMarkupId(true);
		data.add(new Label("categoryId", new PropertyModel<>(facetModel, "categoryId")));
		data.add(new Label("sizeQNames", new PropertyModel<>(facetModel, "sizeQNames")));
		add(data);
		
		add(new StatelessFacetForm("facetForm", facetModel) {
			@Override
			protected void onFacetChanged(AjaxRequestTarget target) {
				super.onFacetChanged(target);
				target.add(data);
			}
		});
	}
	
}
