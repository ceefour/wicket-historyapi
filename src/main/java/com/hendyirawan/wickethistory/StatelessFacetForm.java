package com.hendyirawan.wickethistory;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.model.AbstractWrapModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wicketstuff.stateless.StatelessAjaxFallbackLink;
import org.wicketstuff.stateless.StatelessAjaxFormComponentUpdatingBehavior;

/**
 *
 * @author ceefour
 */
public class StatelessFacetForm extends StatelessForm<Facet> {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(StatelessFacetForm.class);

	public StatelessFacetForm(String id, IModel<Facet> model) {
		super(id, model);
		add(new StatelessAjaxFallbackLink<Void>("dressesLink") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				facet.setCategoryId("dresses");
				log.info("Clicked Dresses link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
		});
		add(new StatelessAjaxFallbackLink<Void>("shoesLink") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				facet.setCategoryId("shoes");
				log.info("Clicked Shoes link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
		});
		add(new StatelessAjaxFallbackLink<Void>("bagsLink") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				facet.setCategoryId("bags");
				log.info("Clicked Bags link, facet is now: {}, new PageParams: {}",
						facet, facet.toPageParameters());
				onFacetChanged(target);
			}
		});
		
		final CheckBox sSize = new CheckBox("sSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return StatelessFacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:s_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:s_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:s_size");
				}
			}
		});
		sSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return StatelessFacetForm.this.getPage().getPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				log.info("Checkbox S clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), StatelessFacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(sSize);
		final CheckBox mSize = new CheckBox("mSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return StatelessFacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:m_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:m_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:m_size");
				}
			}
		});
		mSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return StatelessFacetForm.this.getPage().getPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				log.info("Checkbox M clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), StatelessFacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(mSize);
		final CheckBox lSize = new CheckBox("lSize", new AbstractWrapModel<Boolean>() {
			@Override
			public IModel<Facet> getWrappedModel() {
				return StatelessFacetForm.this.getModel();
			}
			
			@Override
			public Boolean getObject() {
				return getWrappedModel().getObject().getSizeQNames().contains("base:l_size");
			}
			
			@Override
			public void setObject(Boolean state) {
				if (state) {
					getWrappedModel().getObject().getSizeQNames().add("base:l_size");
				} else {
					getWrappedModel().getObject().getSizeQNames().remove("base:l_size");
				}
			}
		});
		lSize.add(new StatelessAjaxFormComponentUpdatingBehavior("click") {
			@Override
			protected PageParameters getPageParameters() {
				return StatelessFacetForm.this.getPage().getPageParameters();
			}
			
			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				final Facet facet = StatelessFacetForm.this.getModelObject();
				log.info("Checkbox L clicked, PageParams was: {}, facet is now: {}",
						getPageParameters(), StatelessFacetForm.this.getModelObject());
				onFacetChanged(target);
			}
		});
		add(lSize);
	}
	
	protected void onFacetChanged(AjaxRequestTarget target) {
		
	}

}
